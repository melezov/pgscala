package hr.element.pgscala.converters;

import org.joda.convert.*;

import java.awt.geom.Point2D;

public class PGNullablePointConverter {
  public static final String pgType = "point";

  @ToString
  public static String toPGString(final Point2D p) {
    return null == p ? null : p.toString();
  }

  @FromString
  public static Point2D.Double fromPGString(final String p) {
    return null == p ? null : null;
  }
}
