package hr.element.pgscala
package pgc

import Encode.pgTypeRep

trait Encode[-T] {
  def typeCode: pgTypeRep
  def encode (t: T): AnyRef
}

object Encode {
  type pgTypeRep = Int
  def encode [T] (t: T) (implicit enc: Encode[T]) = enc encode t
  def typeCode [T] (implicit enc: Encode[T]) = enc typeCode
}

trait Decode[+T] {
  def decode (s: String): T
}

object Decode {
  def decode [T] (s: String) (implicit dec: Decode[T]) = dec decode s
}

