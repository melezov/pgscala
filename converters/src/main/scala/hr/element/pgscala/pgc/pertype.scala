package hr.element.pgscala
package pgc

import Encode._ ; import Decode._ 

import java.sql.Types


trait LowPriImplicits {

  protected def mke [T] ( encode1   : T => AnyRef
                        , typeCode1 : pgTypeRep
                        ) : Encode [T]
    = new Encode  [T] {
      val typeCode      = typeCode1
      def encode (t: T) = encode1 (t)
    }

  protected def mkd [T] ( decode1 : String => T ) : Decode[T]
    = new Decode [T] { def decode (s: String) = decode1 (s) }


  implicit def arrayE [T: Encode]
    = mke [Array[T]] ( _ map (encode [T]) mkString ("ga: ", ", ", " :")
                     , Types.VARCHAR )
  implicit def arrayD [T] (implicit mf: Manifest[T], dec: Decode[T])
    = mkd (_ => Array [T] ())

  implicit def trvE [T: Encode] = mke [TraversableOnce[T]] (
    _ map (encode[T]) mkString ("gs: ", ", ", ": ")
    , Types.SQLXML )

  implicit def trvD [T: Decode] = mkd [TraversableOnce[T]] (_ => Traversable[T] ())

  implicit def nullE [T: Encode]
    = mke [Option[T]] ( _ map (encode[T]) orNull, typeCode[T] )

  implicit def nullD [T: Decode]
    = mkd [Option[T]] (Option (_) map (decode[T]))

}

trait Implicits extends LowPriImplicits {

  implicit def intE: Encode[Int] = mke [Int] ( _ toString, Types.INTEGER )
  implicit def intD: Decode[Int] = mkd [Int] ( _ toInt )

  implicit def boolE: Encode[Boolean] = mke[Boolean] ( i => if (i) "t" else "f", Types.BIT )
  implicit def boolD: Decode[Boolean] = mkd[Boolean] ( _ == "t" )

  implicit def arrayIntE: Encode[Array[Int]]
    = mke[Array[Int]] ( _ map (encode[Int]) mkString ("ia: ", ", ", " :")
                      , Types.ARRAY )
  implicit def arrayIntD: Decode[Array[Int]]
    = mkd[Array[Int]] ( _ => Array[Int] (7, 8, 9) )
}

object Implicits extends Implicits

