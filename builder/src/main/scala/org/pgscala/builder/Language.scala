package org.pgscala
package builder

sealed abstract class Language(val dir: String)

object Language {
  case object Java extends Language("java")
  case object Scala extends Language("scala")
  case object Jasmin extends Language("jasmin")
}
