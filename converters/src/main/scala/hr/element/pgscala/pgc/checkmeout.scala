package hr.element.pgscala
package pgc

object CheckMeOut extends App {

  import Encode._
  import Implicits._

  def impale [T: Encode] (t: T) {
    println (">> " + encode [T] (t))
  }

  impale (1)
  impale (false)
  impale (Array (true, false))
  impale (Array (1, 2, 3))
  impale (0 to 3)
  impale (Array (Array (1, 2), Array (3, 4)))

}
