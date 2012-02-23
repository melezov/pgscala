package hr.element.pgscala
package builder

trait BuilderLike {
  def filters: Seq[String => String]

  def inject(body: String) = (
    (filters :\ body)( _(_) )  // (-_(-_-)_-) The hood is watching
      split("\n{3,}")
      mkString "\n\n"
  )

  def i(key: String, value: String) =
    (_: String).replace("{ "+ key +" }", value)

  def l(word: String) =
    word.head.toLower + word.tail
}
