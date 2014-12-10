
package pt.ipg.mcm.client;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for type-response.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="type-response">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="OK"/>
 *     &lt;enumeration value="ERRO"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "type-response")
@XmlEnum
public enum TypeResponse {

    OK,
    ERRO;

    public String value() {
        return name();
    }

    public static TypeResponse fromValue(String v) {
        return valueOf(v);
    }

}
