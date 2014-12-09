package pt.ipg.mcm.client.tests;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ClientRule implements TestRule {
  Properties properties = new Properties();

  public ClientRule() {
    InputStream is = getClass().getResourceAsStream("/profile.properties");
    try {
      properties.load(is);
    } catch (IOException e) {
      throw new IllegalStateException("problem to load properties");
    }
  }

  @Override
  public Statement apply(final Statement base, final Description description) {
    return new Statement() {
      @Override
      public void evaluate() throws Throwable {
        if (properties.getProperty("ws.client.enabled").equals("true")) {
          base.evaluate();
        } else {
          System.err.println("Ignoring test:"+description.getClassName()+"#"+description.getMethodName());
        }
      }
    };
  }
}
