package hr.element.pgscala
package builder

sealed abstract class Language(val name: String)
case object Java extends Language("java")
case object Scala extends Language("scala")
case object Jasmin extends Language("jasmin")
