package org

package object pgscala extends Implicits {
  /*
  val IndexedSeqSet = iorc.IndexedSeqSet
  val IndexedSeqMap = iorc.IndexedSeqMap
  */
}

package pgscala {
  import Parametrifier._
  import converters._

  class PGTraversableConverter[T](implicit val c: PGConverter[T]) extends PGConverter[Traversable[T]] {
    val PGType = c.PGType + "[]"

    def toPGString(aT: Traversable[T]): String =
      util.PGArray.pack(aT.map(c.toPGString).toArray)

    def fromPGString(aT: String): IndexedSeq[T] =
      util.PGArray.unpack(aT).map(c.fromPGString).toIndexedSeq
  }

  trait Implicits extends converters.Implicits {
    /*
    type IndexedSeqSet[T] = iorc.IndexedSeqSet[T]
    type IndexedSeqMap[K, V] = iorc.IndexedSeqMap[K, V]
*/
    implicit def impaleParamText[T](p: T)(implicit c: PGConverter[T]) =
      ParamText(p, c)

    implicit def impaleParamTextMapPatch(p: Map[String, String]) =
      ParamText(p, PGMapConverter)

    implicit def impaleTraversableParamText[T](p: Traversable[T])(implicit c: PGConverter[T]) =
      ParamText(p, new PGTraversableConverter[T])
  }
}
