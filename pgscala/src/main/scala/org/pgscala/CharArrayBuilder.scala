package org.pgscala
import Mark._

case class CharArrayBuilder(
                  baseString: String
                , replace1: Map[Int, String]
                , indices1: Indices
                , replace2: Map[Int, String]
                , indices2: Indices) {
  val result = {

    val flowMaker = (replace: Map[Int, String]
              , indices: Indices) =>
        indices.map{
          indice =>
            val replaceingLength = 2 + math.log10(indice._1).toInt
              indice._2.map(index =>
                Match(index, replaceingLength, replace(indice._1).toCharArray))
           }.flatten.toList

    val flow = (flowMaker(replace1, indices1) ::: flowMaker(replace2, indices2))
        .sortWith((x, y) => x.index < y.index)
    makeStringFromMatchList(baseString, flow)

  }


  def makeStringFromMatchList(
      query: String
    , flow:  Seq[Match]) ={
    val base = query.toCharArray
       val buff = new Array[Char](base.length +
           flow.foldLeft(0)((acc, el) => acc + el.replacement.length - el.length))

    val lastPivot = flow.foldLeft(Pivot(0,0)){(pivot: Pivot, el) =>
      val baseCopyLength =  el.index - pivot.left

      System.arraycopy(base, pivot.left, buff, pivot.right, baseCopyLength )
      System.arraycopy(el.replacement, 0, buff, pivot.right + baseCopyLength, el.replacement.length)

      Pivot(pivot.left + baseCopyLength + el.length,
          pivot.right + baseCopyLength + el.replacement.length)
      }
    val baseCopyLength =  base.length - lastPivot.left
    System.arraycopy(base, lastPivot.left, buff, lastPivot.right, baseCopyLength)

    buff
  }
  private case class Match(index: Int, length: Int, replacement: Array[Char])
  private case class Pivot(left: Int, right: Int)
}

//case class Match(index: Int, length: Int)


//
//case class CharArrayBuilder2(baseString: String,
//    replace: Map[String, Seq[Match]]) {
//  val resoult = {
//    val base = baseString.toCharArray
//    val flow = replace.map(x => x._2.map(y => SeqMatch(y.index, y.length, x._1.toCharArray))).
//          flatten.toList.sortWith((x,y) => x.index < y.index)
//    val buff = new Array[Char](base.length +
//        flow.foldLeft(0)((acc, el) => acc + el.replacement.length - el.length))
//
//    val lastPivot = flow.foldLeft(Pivot(0,0)){(pivot: Pivot, el) =>
//      val baseCopyLength =  el.index - pivot.left
//
//      System.arraycopy(base, pivot.left, buff, pivot.right, baseCopyLength )
//      System.arraycopy(el.replacement, 0, buff, pivot.right + baseCopyLength, el.replacement.length)
//
//      Pivot(pivot.left + baseCopyLength + el.length,
//          pivot.right + baseCopyLength + el.replacement.length)
//      }
//
//    val baseCopyLength =  base.length - lastPivot.left
//    System.arraycopy(base, lastPivot.left, buff, lastPivot.right, baseCopyLength)
//
//    buff
//  }
//  private case class SeqMatch(index: Int, length: Int, replacement: Array[Char])
//  private case class Pivot(left: Int, right: Int)
//}

//case class CharArrayBuilderHelper()
//
//    val flow =  indices.map{
//      indice =>
//        val replaceingLength = 1 + math.log10(indice._1).toInt
//          indice._2.map(index =>
//            SeqMatch(index, replaceingLength, replace(indice._1).toCharArray))
//            }.flatten.toList.sortWith((x,y) => x.index < y.index)
//    val buff = new Array[Char](base.length +
//        flow.foldLeft(0)((acc, el) => acc + el.replacement.length - el.length))
//
//    val lastPivot = flow.foldLeft(Pivot(0,0)){(pivot: Pivot, el) =>
//      val baseCopyLength =  el.index - pivot.left
//
//      System.arraycopy(base, pivot.left, buff, pivot.right, baseCopyLength )
//      System.arraycopy(el.replacement, 0, buff, pivot.right + baseCopyLength, el.replacement.length)
//
//      Pivot(pivot.left + baseCopyLength + el.length,
//          pivot.right + baseCopyLength + el.replacement.length)
