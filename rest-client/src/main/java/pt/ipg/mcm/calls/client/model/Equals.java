package pt.ipg.mcm.calls.client.model;

public class Equals {
  public static boolean checkTypeEqualAndNotNull(Object obj1, Object obj2) {
    if (obj1 == null || obj2 == null) {
      throw new NullException();
    }
    if (obj1.getClass() != obj2.getClass()) {
      return false;
    }
    return true;
  }

  public static boolean checkTypeEqual(Object obj1, Object obj2) {
    if (obj1 == null && obj2 == null) {
      return true;
    }
    if (obj1 == null || obj2 == null) {
      return false;
    }
    if (obj1.getClass() != obj2.getClass()) {
      return false;
    }
    return true;
  }

  public static class NullException extends RuntimeException {
  }

}
