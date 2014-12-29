
package pt.ipg.mcm.xmodel;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for type-enum-response.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="type-enum-response">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="OK"/>
 *     &lt;enumeration value="ERRO"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "Type-enum-response")
@XmlEnum
public enum TypeEnumResponse {

    OK,
    ERRO;

    public String value() {
        return name();
    }

    public static TypeEnumResponse fromValue(String v) {
        return valueOf(v);
    }

}
