package hr.element.pgscala.util
package converters

import scala.collection.mutable.ListBuffer

object PGArray {
  def pack(elements: Traversable[String]): String =
    elements.map { v =>
      if (v != null) {
        if (v.contains(',') ||
            v.contains(' ') ||
            v.contains('\\') ||
            v.contains('"') ||
            v.contains('{') ||
            v.contains('}') ||
            v.isEmpty) {
          "\"" + v.replace("\\", "\\\\").replace("\"", "\\\"") + "\""
        }
        else {
          v
        }
      }
      else {
        "NULL"
      }
    }.mkString("{", ",", "}")
}