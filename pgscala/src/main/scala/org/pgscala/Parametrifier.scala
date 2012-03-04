package org.pgscala

import scala.annotation.tailrec
import Parametrifier._

import util.PGLiteral
import converters.PGConverter

object Parametrifier {
  case class ParamText[T](value: T, pc: PGConverter[T]) {
    def toJDBCParam = "?::%s".format(pc.PGType)
    def toParam(num: Int) = "$%d::%s".format(num, pc.PGType)
    lazy val toLiteral = "%s::%s".format(PGLiteral.quote(toString), pc.PGType)

    override lazy val toString = pc.toPGString(value)
  }

  case class ParamMark(literal: Boolean, number: Int, index: Int, width: Int)
  case class ParamReplacement(param: ParamMark, body: Array[Char]) {
    override def toString = "%s:[%s]".format(param, new String(body))
  }

  def apply(query: String, params: IndexedSeq[ParamText[_]]): Parametrifier = {
    new Parametrifier(query.toCharArray, params)
  }
}

class Parametrifier private(query: Array[Char], params: IndexedSeq[ParamText[_]]) {

  val marks = markParams(0, Nil)

  @tailrec
  private def markParams(index: Int, soFar: List[ParamMark]): List[ParamMark] =
    if (index > query.length - 1) {
      soFar.reverse
    }
    else {
      val ch = query(index);
      ch match {
        case '$' | '@' if index + 1 < query.length =>
          val literal = ch == '@'
          query(index + 1) match {

            case n1 if n1 >= '1' && n1 <= '9' =>
              val num1 = n1 - '0'
              if (index + 2 < query.length) {
                query(index + 2) match {

                  case n2 if n2 >= '0' && n2 <= '9' =>
                    val numX = query.view.drop(index + 1).takeWhile(_.isDigit).mkString
                    val delta = numX.length + 1
                    markParams(index + delta, ParamMark(literal, numX.toInt, index, delta) :: soFar)

                  case _ =>
                    markParams(index + 2, ParamMark(literal, num1, index, 2) :: soFar)
                }
              }
              else {
                markParams(index + 2, ParamMark(literal, num1, index, 2) :: soFar)
              }

            case _ =>
              markParams(index + 1, soFar)
          }

        case _ =>
          markParams(index + 1, soFar)
      }
  }

  val (replacements, preparedParams) = createReplacements()

  private def createReplacements(): (Seq[ParamReplacement], IndexedSeq[String]) = {
    val usedParams = Array.fill(params.length)(-1)

    val reps =
      marks.map{ m =>
        val p = params(m.number - 1)

        if (m.literal) {
          ParamReplacement(m, p.toLiteral.toCharArray)
        }
        else if (-1 != usedParams(m.number - 1)) {
          ParamReplacement(m, p.toParam(m.number).toCharArray)
        }
        else {
          usedParams(m.number - 1) = m.index
          ParamReplacement(m, p.toJDBCParam.toCharArray)
        }
      }

    val pParams = usedParams
      .zipWithIndex
      .filter(-1!=_._1)
      .sortBy(_._1)
      .map(p => params(p._2).toString)

    reps -> pParams
  }

  val preparedQuery = replaceMarks(replacements)

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
