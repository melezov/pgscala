package org.pgscala

import scala.annotation.tailrec

import Parametrifier._

object Parametrifier {
  case class ParamText(text: String, pgType: String) {
    def toJDBCParam = "?::%s".format(pgType)
    def toParam(num: Int) = "$%d::%s".format(num, pgType)
    def toLiteral = "%s::%s".format(util.PGLiteral.quote(text), pgType)
  }

  case class ParamMark(ch: Char, number: Int, index: Int, width: Int)
  case class ParamReplacement(param: ParamMark, body: Array[Char])

  def apply(query: String, params: IndexedSeq[ParamText]): Parametrifier = {
    new Parametrifier(query.toCharArray, params)
  }
}

class Parametrifier private(query: Array[Char], params: IndexedSeq[ParamText]) {

  val marks = markParams(0, Nil)

  @tailrec
  private def markParams(index: Int, soFar: List[ParamMark]): List[ParamMark] =
    if (index > query.length - 1) {
      soFar
    }
    else {
      val ch = query(index); ch match {
        case '$' | '@' if index + 1 < query.length =>
          query(index + 1) match {

            case n1 if n1 >= '1' && n1 <= '9' =>
              val num1 = n1 - '0'
              if (index + 2 < query.length) {
                query(index + 2) match {

                  case n2 if n2 >= '0' && n2 <= '9' =>
                    val numX = query.view.drop(index + 1).takeWhile(_.isDigit).mkString
                    val delta = numX.length + 1
                    markParams(index + delta, ParamMark(ch, numX.toInt, index, delta) :: soFar)

                  case _ =>
                    markParams(index + 2, ParamMark(ch, num1, index, 2) :: soFar)
                }
              }
              else {
                markParams(index + 2, ParamMark(ch, num1, index, 2) :: soFar)
              }

            case _ =>
              markParams(index + 1, soFar)
          }

        case _ =>
          markParams(index + 1, soFar)
      }
  }
/*
  def flowMaker(replace: Map[Int, String]
              , indices: Indices) =>
        indices.map{
          indice =>
            val replacingLength = 1 + indice._1.toString.length
            indice._2.map(index =>
              Replacement(index, replacingLength, replace(indice._1).toCharArray))
           }.flatten.toList

    val flow = (flowMaker(replace1, indices1) ::: flowMaker(replace2, indices2))
        .sortWith((x, y) => x.index < y.index)
    makeStringFromMatchList(baseString, flow)
  }
*/

  private def replaceMarks(flow: Seq[ParamReplacement]) = {
    val buff = new Array[Char](
      (query.length /: flow)((acc, el) => acc + el.body.length - el.param.width)
    )

    val lastPivot = flow.foldLeft(0 to 0){(pivot, el) =>
      val copyLen = el.param.index - pivot.start

      System.arraycopy(query, pivot.start, buff, pivot.end, copyLen)
      System.arraycopy(el.body, 0, buff, pivot.end + copyLen, el.body.length)

      (pivot.start + copyLen + el.param.width) to
      (pivot.end   + copyLen + el.body.length)
    }

    val copyLen = query.length - lastPivot.start
    System.arraycopy(query, lastPivot.start, buff, lastPivot.end, copyLen)

    new String(buff)
  }
}
