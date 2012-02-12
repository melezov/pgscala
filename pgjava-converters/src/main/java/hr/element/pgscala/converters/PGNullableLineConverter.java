package hr.element.pgscala.converters;

import org.joda.convert.*;

import java.awt.geom.Line2D;

public class PGNullableLineConverter {
  public static final String pgType = "line";

  @ToString
  public static String toPGString(final Line2D.Double l) {
    return null == l ? null : l.toString();
  }

  @FromString
  public static Line2D.Double fromPGString(final String l) {
    return null == l ? null : null;
  }
}
