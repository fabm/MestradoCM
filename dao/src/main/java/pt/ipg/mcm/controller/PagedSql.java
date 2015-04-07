package pt.ipg.mcm.controller;

public class PagedSql {

  public static String getPagedQueryString(String sql, int page, int quantity) {
    return String.format("SELECT * FROM\n" +
        "(\n" +
        "    SELECT paged.*, rownum rnum\n" +
        "    FROM\n" +
        "    (\n" +
        "      %1$s\n" +
        "    ) paged\n" +
        "    WHERE rownum < ((%2$d * %3$d) + 1)\n" +
        ")\n" +
        "WHERE rnum >= (((%2$d-1) * %3$d) + 1)", sql, page, quantity);
  }

}
