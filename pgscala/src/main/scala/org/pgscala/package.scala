package org

package object pgscala extends Implicits {
  val IndexedSeqSet = iorc.IndexedSeqSet
  val IndexedSeqMap = iorc.IndexedSeqMap
}

package pgscala {
  import Parametrifier._
  import converters._

  trait Implicits extends converters.Implicits {
    implicit def impaleParamText[T](p: T)(implicit c: PGConverter[T]) =
      ParamText(p, c)

    type IndexedSeqSet[T] = iorc.IndexedSeqSet[T]
    type IndexedSeqMap[K, V] = iorc.IndexedSeqMap[K, V]
  }
}
