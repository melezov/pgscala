package hr.element.pgscala.util.statementPreparer


class CharArrayBuilder(baseString: String,
    replace: Map[String, Seq[Match]]) {
  val resoult = {
    val base = baseString.toCharArray
    val flow = replace.map(x => x._2.map(y => SeqMatch(y.index, y.length, x._1.toCharArray))).
          flatten.toList.sortWith((x,y) => x.index < y.index)
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
  private case class SeqMatch(index: Int, length: Int, replacement: Array[Char])
  private case class Pivot(left: Int, right: Int)
}

case class Match(index: Int, length: Int)


