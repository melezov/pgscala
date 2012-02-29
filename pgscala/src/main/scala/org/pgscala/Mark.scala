package org.pgscala

object Mark {
  type Indices = Map[Int, Seq[Int]]

  def apply(query: String, n: Int): (Indices, Indices) = {
    val range = (1 to n)
    val number = "\\d*".r

    def getNumberAfter(i: Int) =
      (number.findFirstIn(query.drop(i + 1))).get.toInt

    def find(wha: Char)(
      res: Seq[(Int, Int)] = Seq.empty[(Int, Int)]
    , last: Int = 0)
    : Seq[(Int, Int)] =
      query.indexOf(wha, last) match {
        case -1 => res
        case x => find(wha)((x, getNumberAfter(x)) +: res, x + 1)
        }

      ((find('$')()).groupBy(_._2).mapValues(_.map(_._1))
    , (find('@')().groupBy(_._2).mapValues(_.map(_._1))))
    }
}

      //.sortWith((x, y) => x._1 > y._1)
//
//    loc.foldLeft(Map.empty[Int, Seq[Int]], Map.empty[Int, Seq[Int]]){
//      (acc, el) => (number.findFirstIn(query.drop(el._1)) match {
//        case Some(x) =>
//          val int = x.toInt
//            el._2 match {
//              case '$' => (acc._1 + (int -> el._1), acc._2)
//              case '@' => (acc._1, acc._2 + (int -> el._1))
//            }
//        case None => System.error("You Shall Not Pass!")
//      }
//
//
//        acc
//    }
//
//    val locIt = loc.iterator
//    (1 to n).foldRight((
//      Map.empty[Int, Seq[Int]], Map.empty[Int, Seq[Int]])) {
//      (el, acc) =>
//        if (locIt.hasNext) {
//
//          val next = locIt.next()
//
//          next._2 match {
//            case '$' => (acc._1 + (el -> next._1), acc._2)
//            case '@' => (acc._1, acc._2 + (el -> next._1))
//          }
//        } else
//          acc
//    }
//  }
//}


//                                                         zipWithIndex version
//(Map.empty[Int,Int] , Map.empty[Int,Int])
//    val zip = query.zipWithIndex
//    val loc = zip.filter(x => (x._1 == '$')||(x._1 == '@')).reverse.iterator
//
//    range.foldLeft((
//          Map.empty[Int,Int]
//        , Map.empty[Int,Int]
//        , loc)) {
//      (acc, el) =>
//        val next = acc._3.next()
//        next._1 match {
//          case '$' => (acc._1 ++ (el -> next._2), acc._2, loc)
//          case '@' => (acc._1 , acc._2 ++ (el -> next._2), loc)
//        }
//    }
