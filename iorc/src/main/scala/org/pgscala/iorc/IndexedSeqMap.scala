package org.pgscala.iorc

import scala.collection.generic.{ ImmutableMapFactory, CanBuildFrom, GenericCompanion }
import scala.collection.immutable.{ IndexedSeq, MapLike}

object IndexedSeqMap extends ImmutableMapFactory[IndexedSeqMap] {
  implicit def canBuildFrom[A, B]: CanBuildFrom[Coll, (A, B), IndexedSeqMap[A, B]] = new MapCanBuildFrom[A, B]
  private val Nil = new IndexedSeqMap(IndexedSeq.empty, Map.empty)
  def empty[A, B]: IndexedSeqMap[A, B] = Nil.asInstanceOf[IndexedSeqMap[A, B]]
}

@SerialVersionUID(0xB5BF26DFE80E3A82L) // sha1("scala.collection.immutable.IndexedSeqMap-0.1.x")
class IndexedSeqMap[A, +B] private (
  private val _seq: IndexedSeq[(A, B)],
  private val _map: Map[A, B]) extends Map[A, B]
    with MapLike[A, B, IndexedSeqMap[A, B]] {
  override def empty = IndexedSeqMap.empty
  override def stringPrefix = "IndexedSeqMap"

  override def size: Int = _seq.size

  def get(k: A): Option[B] =
    _map.get(k)

  def +[B1 >: B](kv: (A, B1)): IndexedSeqMap[A, B1] =
    _map.get(kv._1) match {
      case Some(x: AnyRef) if kv._2.asInstanceOf[AnyRef] eq x =>
        this
      case Some(x) =>
        val pos = _seq.indexWhere(_._1 == kv._1)
        val newSeq = _seq.updated(pos, kv)
        new IndexedSeqMap(newSeq, _map + kv)
      case None =>
        new IndexedSeqMap(_seq :+ kv, _map + kv)
    }

  def -(k: A): IndexedSeqMap[A, B] =
    _map.get(k) match {
      case Some(x) =>
        new IndexedSeqMap(_seq.filter(_._1 != k), _map - k)
      case None =>
        this
    }

  def ++[B1 >: B](that: IndexedSeqMap[A, B1]): IndexedSeqMap[A, B1] =
    if (that.isEmpty) {
      this
    } else if (isEmpty) {
      that.asInstanceOf[IndexedSeqMap[A, B1]]
    } else {
      super.++(that)
    }

  def ++[B1 >: B](that: IndexedSeq[(A, B1)]): IndexedSeqMap[A, B1] =
    if (that.isEmpty) {
      this
    } else if (isEmpty) {
      val newMap = that.toMap
      if (that.size == newMap.size) {
        new IndexedSeqMap(that, newMap)
      } else {
        super.++(that)
      }
    } else {
      super.++(that)
    }

  def ++[B1 >: B](that: Map[A, B1]): IndexedSeqMap[A, B1] =
    if (that.isEmpty) {
      this
    } else if (isEmpty) {
      new IndexedSeqMap(that.toIndexedSeq, that)
    } else {
      super.++(that)
    }

  override def keySet: IndexedSeqSet[A] = IndexedSeqSet.empty ++ _seq.map(_._1)

  override def toSeq: Seq[(A, B)] = _seq
  override def toIndexedSeq[C >: (A, B)]: IndexedSeq[C] = _seq
  override def toMap[T, U](implicit ev: (A, B) <:< (T, U)): Map[T, U] = _map.asInstanceOf[Map[T, U]]

  def iterator =
    _seq.iterator
  override def foreach[U](f: ((A, B)) => U): Unit =
    _seq.foreach(f)
}
