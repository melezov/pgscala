package org.pgscala.iorc

import scala.collection.SetLike
import scala.collection.generic.{ ImmutableSetFactory, CanBuildFrom, GenericSetTemplate, GenericCompanion }
import scala.collection.immutable.IndexedSeq

object IndexedSeqSet extends ImmutableSetFactory[IndexedSeqSet] {
  implicit def canBuildFrom[A]: CanBuildFrom[Coll, A, IndexedSeqSet[A]] = setCanBuildFrom
  override def empty[A]: IndexedSeqSet[A] = new IndexedSeqSet(IndexedSeq.empty, Set.empty)
}

@SerialVersionUID(0x70747D637D2FEEE4L) // sha1("scala.collection.immutable.IndexedSeqSet-0.1.x")
class IndexedSeqSet[A] private (
  private val _seq: IndexedSeq[A],
  private val _set: Set[A]) extends Set[A]
    with GenericSetTemplate[A, IndexedSeqSet]
    with scala.collection.SetLike[A, IndexedSeqSet[A]] {
  override def companion: GenericCompanion[IndexedSeqSet] = IndexedSeqSet
  override def stringPrefix = "IndexedSeqSet"

  override def size: Int = _seq.size

  def contains(elem: A): Boolean =
    _set.contains(elem)

  def +(elem: A): IndexedSeqSet[A] =
    if (contains(elem)) this
    else new IndexedSeqSet(_seq :+ elem, _set + elem)

  def -(elem: A): IndexedSeqSet[A] =
    if (!contains(elem)) this
    else new IndexedSeqSet(_seq.filter(_ != elem), _set - elem)

  def ++(that: IndexedSeqSet[A]): IndexedSeqSet[A] = {
    if (that.isEmpty) {
      this
    } else if (isEmpty) {
      that
    } else {
      super.++(that)
    }
  }

  def ++(that: IndexedSeq[A]): IndexedSeqSet[A] = {
    val minLen = _set.size
    val empLen = that.size
    val maxLen = minLen + empLen

    val newSet = _set ++ that
    newSet.size match {
      case `minLen` =>
        /* All elements are already contained, noop */
        this

      case `empLen` =>
        /* We were empty, so reuse the provided IndexedSeq */
        new IndexedSeqSet(that, newSet)

      case `maxLen` =>
        /* We are not empty and all elements need to be added */
        new IndexedSeqSet(_seq ++ that, newSet)

      case _ =>
        /* Only some of the new elements need to be added.          */
        /* This means that either we already contained some of the  */
        /* elements, or that the provided IndexedSeq had duplicates */
        val seqDelta = that.filterNot(_set).distinct
        new IndexedSeqSet(_seq ++ seqDelta, newSet)
    }
  }

  def ++(that: Set[A]): IndexedSeqSet[A] = {
    val minLen = _set.size
    val empLen = that.size
    val maxLen = minLen + empLen

    val newSet = _set ++ that
    newSet.size match {
      case `minLen` =>
        /* All elements are already contained, noop */
        this

      case `empLen` =>
        /* We were empty, so reuse the provided Set */
        new IndexedSeqSet(that.toIndexedSeq, that)

      case `maxLen` =>
        /* We are not empty and all elements need to be added */
        new IndexedSeqSet(_seq ++ that, newSet)

      case _ =>
        /* Only some of the new elements need to be added.           */
        /* This means that we already contained some of the elements */
        val setDelta = that.toIndexedSeq.filterNot(_set)
        new IndexedSeqSet(_seq ++ setDelta, newSet)
    }
  }

  override def toSeq: IndexedSeq[A] = _seq
  override def toIndexedSeq: IndexedSeq[A] = _seq
  override def toSet[B >: A]: Set[B] = _set.asInstanceOf[Set[B]]

  def iterator =
    _seq.iterator
  override def foreach[U](f: A => U): Unit =
    _seq.foreach(f)
}
