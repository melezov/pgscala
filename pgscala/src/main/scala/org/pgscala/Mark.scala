package org.pgscala

import scala.annotation.tailrec

object Mark {
  type Indices = Map[Int, Set[Int]]

  def apply(query: String): (Indices, Indices) =
    (mark(query, '$'), mark(query, '@'))

  def mark(query: String, ch: Char): Indices =
    find(query, ch, Nil) match {
      case Nil =>
        Map.empty

      case x =>
        x groupBy(_._1) mapValues(_.map(_._2) toSet)
    }

  @tailrec
  def find(query: String, ch: Char, soFar: List[(Int, Int)]): List[(Int, Int)] = {
    query.indexOf(ch) match {
      case -1 =>
        soFar

      case index =>
        val tail = query.substring(index + 1)
        val digit = tail.takeWhile(_.isDigit)

        if (digit.isEmpty) {
          find(tail, ch, soFar)
        }
        else {
          val rest = tail.substring(digit.length)
          val nowFar = (digit.toInt -> index) :: soFar
          find(rest, ch, nowFar)
        }
    }
  }
}