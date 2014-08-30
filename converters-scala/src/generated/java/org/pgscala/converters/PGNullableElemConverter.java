package org.pgscala.converters;

import org.joda.convert.*;

import scala.io.Source;
import scala.xml.Elem;
import scala.xml.parsing.ConstructingParser;

/** Do not edit - generated in Builder / PGNullableElemConverterBuilder.scala */

public enum PGNullableElemConverter implements StringConverter<Elem> {
  INSTANCE;

  public static final String pgType = "xml";

  @ToString
  public static String elemToString(final Elem e) {
    return null == e ? null : e.toString();
  }

  @FromString
  public static Elem stringToElem(final String e) {
    return null == e ? null :
      (Elem) ConstructingParser
        .fromSource(Source.fromString(e), true)
        .document().docElem();
  }

// -----------------------------------------------------------------------------

  public String convertToString(final Elem e) {
    return elemToString(e);
  }

  public Elem convertFromString(final Class<? extends Elem> clazz, final String e) {
    return stringToElem(e);
  }
}
