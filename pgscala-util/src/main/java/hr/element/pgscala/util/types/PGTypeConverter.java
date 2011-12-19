package hr.element.pgscala.util.types;

import org.joda.convert.StringConverter;

public interface PGTypeConverter<T> extends StringConverter<T> {
  public String quoteLiteral();
  public T unquoteLiteral();

  public String quoteRecord();
  public T unquoteRecord();

  public String quoteArray();
  public T unquoteArray();
}
