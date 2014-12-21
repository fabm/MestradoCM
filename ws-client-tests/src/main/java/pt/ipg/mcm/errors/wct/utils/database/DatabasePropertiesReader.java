package pt.ipg.mcm.errors.wct.utils.database;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class DatabasePropertiesReader {
  private Document document;
  private XPath xPath;
  private Properties localProperties;

  public DatabasePropertiesReader() {
    try {
      init();
    } catch (ParserConfigurationException e) {
      throw new IllegalStateException(e);
    } catch (IOException e) {
      throw new IllegalStateException(e);
    } catch (SAXException e) {
      throw new IllegalStateException(e);
    } catch (ClassNotFoundException e) {
      throw new IllegalStateException(e);
    } catch (XPathExpressionException e) {
      throw new IllegalStateException(e);
    }
  }

  private void init() throws ParserConfigurationException, IOException, SAXException, ClassNotFoundException, XPathExpressionException {
    DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
    document = db.parse(DatabasePropertiesReader.class.getResourceAsStream("/pt.ipg.mcm.wct.utils.database-properties.xml"));
    xPath = XPathFactory.newInstance().newXPath();

    loadLocalProperties();
  }

  private void loadLocalProperties() throws IOException, ClassNotFoundException{
    localProperties = new Properties();
    localProperties.load(DatabasePropertiesReader.class.getResourceAsStream("/config.properties"));
    String localPath = localProperties.getProperty("local.path");
    localProperties = new Properties();
    localProperties.load(new FileReader(localPath));
    String driverPath = localProperties.getProperty("oracle.driver");

    File file = new File(driverPath);
    URL[] urls = new URL[]{file.toURI().toURL()};
    ClassLoader classLoader = new URLClassLoader(urls);
    classLoader.loadClass("oracle.jdbc.driver.OracleDriver");
  }

  public Map<String, ConnectionProperties> getConnectionsMap() {
    NodeList nodeList;
    try {
      nodeList = (NodeList) xPath.compile("/database/connections/connection[@active = 'true']").evaluate(document, XPathConstants.NODESET);
    } catch (XPathExpressionException e) {
      throw new IllegalStateException(e);
    }
    Map<String, ConnectionProperties> connectionPropertiesMap = new HashMap<String, ConnectionProperties>();
    for (int i = 0; i < nodeList.getLength(); i++) {
      Element node = (Element) nodeList.item(i);
      String key = node.getAttributes().getNamedItem("key").getNodeValue();
      ConnectionProperties connectionProperties = new ConnectionProperties();

      connectionProperties.setUrl(node.getElementsByTagName("url").item(0).getFirstChild().getNodeValue());
      connectionProperties.setLogin(node.getElementsByTagName("login").item(0).getFirstChild().getNodeValue());
      String relativePass = node.getElementsByTagName("password").item(0).getFirstChild().getNodeValue();

      connectionProperties.setPassword(localProperties.getProperty(relativePass));

      connectionPropertiesMap.put(key, connectionProperties);
    }
    return connectionPropertiesMap;
  }

  public NamedSqlQuery getQuery(String group, String sqlName) {
    Node node;
    String strXpath = "/pt/ipg/mcm/wct/utils/database/queries/group[@name = '%s']/query[@name = '%s']";
    try {
      node = (Node) xPath.compile(String.format(strXpath,group,sqlName)).evaluate(document, XPathConstants.NODE);
      Node connectionNode = node.getAttributes().getNamedItem("connection");
      if(connectionNode == null){
          connectionNode = node.getParentNode().getAttributes().getNamedItem("default-connection");
      }
      return new NamedSqlQuery(connectionNode.getNodeValue(),node.getFirstChild().getNodeValue());
    } catch (XPathExpressionException e) {
      throw new IllegalStateException(e);
    }
  }
}