package org.pgscala
package builder
package converters

trait PGConverterHelper {
  protected def builder =
    getClass.getSimpleName.init + ".scala"

  protected def filters: Seq[String => String]

  protected def inject(body: String) = (
    (filters :\ body)( _(_) )  // (-_(-_-)_-) The hood is watching
      replaceAll(" +\n", "\n")
      replaceAll("\n{3,}", "\n\n")
  )

  protected def i(key: String, value: String) =
    (_: String).replace("{ " + key + " }", value)

  protected def l(word: String) =
    word.head.toLower + word.tail

  protected def lu(word: String) =
    if (word.toUpperCase == word)
      word.toLowerCase
    else l(word)
}
