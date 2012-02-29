package org.pgscala
package builder

trait PGBuilderLike {
  def builder =
    getClass.getSimpleName.init + ".scala"

  def filters: Seq[String => String]

  def inject(body: String) = (
    (filters :\ body)( _(_) )  // (-_(-_-)_-) The hood is watching
      replaceAll(" +\n", "\n")
      replaceAll("\n{3,}", "\n\n")
  )

  def i(key: String, value: String) =
    (_: String).replace("{ "+ key +" }", value)

  def l(word: String) =
    word.head.toLower + word.tail
}
