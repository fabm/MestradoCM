package pt.ipg.mcm.controller.parameters;

import org.eclipse.persistence.platform.database.oracle.plsql.OraclePLSQLType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ProcedureValue {
  int index();
}
