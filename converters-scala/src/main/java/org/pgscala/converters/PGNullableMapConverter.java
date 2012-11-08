package org.pgscala.converters;

import org.joda.convert.*;

import org.joda.convert.FromString;
import org.joda.convert.StringConverter;
import org.joda.convert.ToString;

import scala.collection.mutable.Map;
import scala.collection.mutable.HashMap;

import scala.collection.Iterator;
import scala.Tuple2;

/** Do not edit - generated in Builder / PGNullableMapConverterBuilder.scala */

public enum PGNullableMapConverter implements StringConverter<Map<String, String>> {
  INSTANCE;

  public static final String pgType = "hstore";

  @ToString
  public static String mapToString(final Map<String, String> m) {
    if (null == m) return null;

    final StringBuilder sB = new StringBuilder();

    final Iterator<Tuple2<String, String>> i = m.iterator();
    while(i.hasNext()) {
      final Tuple2<String, String> kv = i.next();

      if (null == kv._1) {
        throw new NullPointerException("Map key cannot be NULL!");
      }
      else if (null == kv._2) {
        throw new NullPointerException("Map values cannot be NULL!");
      }

      final String k = kv._1.replace("\\", "\\\\").replace("\"", "\\\"");
      final String v = kv._2.replace("\\", "\\\\").replace("\"", "\\\"");

      (sB.length() == 0
        ? sB.append('"')
        : sB.append("\", \"")).append(k).append("\"=>\"").append(v);
    }

    return sB.append('"').toString();
  }

  @FromString
  public static Map<String, String> stringToMap(final String m) {
    if (null == m) return null;

    final String[] pairs = m.substring(1, m.length() - 1).split("\", \"");
    final HashMap<String, String> map = new HashMap<String, String>();

    for(final String pair : pairs) {
      final String[] kv = pair.split("\"=>\"", -1);

      if (kv.length != 2) {
        throw new IllegalArgumentException("Illegal pair: " + pair);
      }

      map.put(
        kv[0].replace("\\\"", "\"").replace("\\\\", "\\")
      , kv[1].replace("\\\"", "\"").replace("\\\\", "\\")
      );
    }

    return map;
  }

// -----------------------------------------------------------------------------

  public String convertToString(final Map<String, String> m) {
    return mapToString(m);
  }

  public Map<String, String> convertFromString(final Class<? extends Map<String, String>> clazz, final String m) {
    return stringToMap(m);
  }
}
