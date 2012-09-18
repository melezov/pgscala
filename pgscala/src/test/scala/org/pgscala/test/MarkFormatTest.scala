/*
package org.pgscala
package test

import Mark.Indices

import org.scalatest.{ FeatureSpec, GivenWhenThen }
import org.scalatest.matchers.MustMatchers

import scala.util.Random

class MarkFormatTestSpec extends FeatureSpec
                         with GivenWhenThen
                         with MustMatchers {
  def getResult(res: (Indices, Indices)): String = {
    def getStringFromSeq(s: Seq[Int]) =
      "(" + s.head + s.tail.map(", " + _).mkString + ")"

    def makeStringFromMap(map: Map[Int, Set[Int]]): String =
      map.map(x => "(" + x._1 + " ->" + getStringFromSeq(x._2.toSeq) + ")\n").mkString

    makeStringFromMap(res._1) +
      "--------------------\n" +
      makeStringFromMap(res._2)
  }

  scenario("Mark Then Format Easy Test1.") {
    val testQuery =
      "SELECT testing $1 FROM @2@1$1$1$1"
    info(testQuery + " should make map$= (1->Seq(16,27,29,31,33)")
    info("should make map@= (1->Seq(25),2->Seq(23), or similar.")

    val testMap = Mark(testQuery)
    info("result : \n" + getResult(testMap))
  }

  scenario("Mark Easy Test 2.") {
    val testQuery =

      """
SELECT *, @1
FROM log.vju_responses r
JOIN log.html_scrapes s ON r.id=s.bank_responses_id
WHERE r.isid=$1 AND r.bank=$2 AND description=$3
AND a.isid=$1
ORDER BY r.id;",isid, bank, desc;
"""
    info(testQuery + " should make map$= (1-> Seq(50, 73), 2-> Seq(60), 3-> Seq(65))")
    info("should make map@= (1-> Seq(12)), or similar.")

    val testMap = Mark(testQuery)

    val replaceMap1 = Map(
      (1 -> "---1$---"), (2 -> "---2$---"), (3 -> "---3$---"))
    val replaceMap2 = Map(
      (1 -> "---1@----"))
    val formatedQuery = CharArrayBuilder(
      testQuery, replaceMap1, testMap._1, replaceMap2, testMap._2).result
    info("result : \n" + getResult(testMap))
    info(formatedQuery.mkString)
  }

  scenario("Mark Stress Test 1") {
    val testQuery =
      """
$1$2$1$1$1$1$1$2$1$1$1$1$1$1$2$1$1$1$1$1$1$1$1$2$1$1$1$1$1$2$1$1$1$1$1$1
,@2 @2 @2@2@2@2@2@2@2@2@2@1@2@2@2@2@2@2@2@2@2@2@2@2@2@2@2@2@2@2@2@2@2
"""
    val replaceMap1 = Map(
                (1 -> "---1$---")
              , (2 -> "---2$---"))
    val replaceMap2 = Map(
                (2 -> "---2@----")
              , (1 -> "---1@----"))
    val testMap = Mark(testQuery)

    val formatedQuery = CharArrayBuilder(
      testQuery, replaceMap1, testMap._1, replaceMap2, testMap._2).result
    info("result : \n" + getResult(testMap))
    info(formatedQuery.mkString)
  }
}
*/
