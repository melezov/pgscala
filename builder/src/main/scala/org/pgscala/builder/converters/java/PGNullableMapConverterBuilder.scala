package org.pgscala
package builder
package converters

object PGNullableMapConverterBuilder extends PGNullableConverterBuilder {
  override val imports = """import org.joda.convert.FromString;
import org.joda.convert.StringConverter;
import org.joda.convert.ToString;

import scala.collection.mutable.Map;
import scala.collection.mutable.HashMap;

import scala.collection.Iterator;
import scala.Tuple2;"""

  val pgType = "hstore"

  override val upperType = "Map"

  val clazz = "scala.collection.mutable.Map<String, String>"

  override val jasminType = "Lscala.collection.mutable.Map;"

  private val signature = "Lscala.collection.mutable.Map<Ljava/lang/String;Ljava/lang/String;>;"

  override val jasminToSignature = """
  .signature "(%s)Ljava/lang/String;"""" format signature

  override val jasminFromSignature = """
  .signature "(Ljava/lang/String;)%s"""" format signature

  val to = """
    if (m.isEmpty()) return "";

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

    return sB.append('"').toString()"""

  val from = """

    final HashMap<String, String> map = new HashMap<String, String>();
    if (m.isEmpty()) return map;

    final String[] pairs = m.substring(1, m.length() - 1).split("\", \"", -1);
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

    return map"""

  override val language = Scala

  override def inject(body: String) =
    super.inject(body)
      .replace(
        "return null == m ? null :",
        "if (null == m) return null;")

}
