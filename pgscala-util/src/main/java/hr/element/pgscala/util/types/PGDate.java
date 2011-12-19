package hr.element.pgscala.util.types;

import org.joda.time.*;

public class PGDate implements PGTypeConverter<LocalDate> {
  public static String toString(final LocalDate lD) {
    return lD.toString();
  }

  public static LocalDate fromString(final String value) {
    return new LocalDate(value);
  }
/*
  public static class Literal {
    public static String quote(final LocalDate lD) {
      return lD == null ? "NULL" : PGDate.toString(lD);
    }

    public static LocalDate unquote(final String value) {
      return value.equalsIgnoreCase("NULL") ? null : PGDate.fromString(value);
    }
  }

  public static class Record {
    public static String quote(final LocalDate lD) {
      return lD == null ? "" : PGDate.quote(lD);
    }

    public static LocalDate unquote(final String value) {
      return value.equalsIgnoreCase("NULL") ? null : PGDate.unquote(value);
    }
  }
*/

  @Override
  public String convertToString(LocalDate arg0) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public LocalDate convertFromString(Class<? extends LocalDate> arg0,
      String arg1) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public String quoteLiteral() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public LocalDate unquoteLiteral() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public String quoteRecord() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public LocalDate unquoteRecord() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public String quoteArray() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public LocalDate unquoteArray() {
    // TODO Auto-generated method stub
    return null;
  }
}
