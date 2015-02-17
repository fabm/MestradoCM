package pt.ipg.mcm.controller;

import pt.ipg.mcm.controller.parameters.Procedure;
import pt.ipg.mcm.controller.parameters.ProcedureValue;
import pt.ipg.mcm.controller.ps.PsAddUtilizadorCliente;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.util.Date;

public class CallableStatementHelper {
  public static String createCallableStatement(Object object) throws IllegalAccessException {
    Class<? extends Object> classPs = object.getClass();
    Procedure procedureAnnotation = object.getClass().getAnnotation(Procedure.class);
    String psName = procedureAnnotation.name();
    int size = procedureAnnotation.fieldsNumber();
    Object[] values = new Object[size];
    String[] fieldNames = new String[size];
    Type[] types = new Type[size];

    for (Field field : classPs.getDeclaredFields()) {
      ProcedureValue pValue = field.getAnnotation(ProcedureValue.class);
      if (pValue != null) {
        field.setAccessible(true);
        values[pValue.index()] = field.get(object);
        fieldNames[pValue.index()] = field.getName();
        types[pValue.index()] = field.getType();
      }
    }
    String stringVar = Character.toLowerCase(classPs.getSimpleName().charAt(0)) +
        classPs.getSimpleName().substring(1);

    StringBuilder stringBuilderValues = new StringBuilder();
    StringBuilder stringBuilderParameters = new StringBuilder();
    stringBuilderParameters.append("CallableStatement call = ");
    stringBuilderParameters.append("connection.prepareCall(");
    stringBuilderParameters.append("\"{call ");
    stringBuilderParameters.append(psName);
    stringBuilderParameters.append("(");
    for (int i = 0; i < size; i++) {
      if (types[i] == String.class) {
        stringBuilderValues.append("call.setString(");
      } else if (types[i] == int.class) {
        stringBuilderValues.append("call.setInt(");
      } else if (types[i] == long.class) {
        stringBuilderValues.append("call.setLong(");
      } else if (types[i] == Date.class) {
        stringBuilderValues.append("call.setDate(");
      }
      stringBuilderValues.append(i);
      stringBuilderValues.append(", ");
      if(types[i] == Date.class){
        stringBuilderValues.append("new Date(");
      }
      stringBuilderValues.append(stringVar);
      stringBuilderValues.append(".get");
      stringBuilderValues.append(Character.toUpperCase(fieldNames[i].charAt(0)));
      stringBuilderValues.append(fieldNames[i].substring(1));
      stringBuilderValues.append("()");
      if (types[i] == Date.class){
        stringBuilderValues.append(".getTime())");
      }
      stringBuilderValues.append(");\n");
      stringBuilderParameters.append("?,");
    }
    stringBuilderParameters.deleteCharAt(stringBuilderParameters.length() - 1);
    stringBuilderParameters.append(")}\");\n");
    stringBuilderParameters.append(classPs.getSimpleName());
    stringBuilderParameters.append(' ');


    stringBuilderParameters.append(stringVar);
    stringBuilderParameters.append(" = new ");
    stringBuilderParameters.append(classPs.getSimpleName());
    stringBuilderParameters.append("();\n");
    stringBuilderParameters.append(stringBuilderValues);
    return stringBuilderParameters.toString();
  }


  public static void main(String args[]) throws IllegalAccessException {
    PsAddUtilizadorCliente psAddUtilizadorCliente = new PsAddUtilizadorCliente();
    System.out.println(CallableStatementHelper.createCallableStatement(psAddUtilizadorCliente));
  }
}
