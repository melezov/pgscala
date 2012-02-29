package hr.element.pgscala.util.statementPreparer
package test

import org.scalatest.{ FeatureSpec, GivenWhenThen }
import org.scalatest.matchers.MustMatchers

class MarkTestSpec extends FeatureSpec
  with GivenWhenThen
  with MustMatchers {

  scenario("Mark Test, Easy Mode.") {
    val testQuery =
      "Select some $1 form  @2@1$1$1$1"
    info(testQuery + " should make map$= (1->Seq(16,27,29,31,33)")
    info("should make map@= (1->Seq(25),2->Seq(23), or similar.")

    val testMap = Mark(testQuery, 3)
    testMap._1.foreach(x => println(x._1 + " " + x._2))
    println("--------------------")
    testMap._2.foreach(x => println(x._1 + " " + x._2))

  }
  scenario("Mark Test2, Easy Mode.") {
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

    val testMap = Mark(testQuery, 4)
    testMap._1.foreach(x => println(x._1 + " " + x._2))
    println("--------------------")
    testMap._2.foreach(x => println(x._1 + " " + x._2))

  }

  scenario("Mark Test3, Easy Mode.") {
    val testQuery =
      """
SELECT *, @1
FROM log.vju_responses r
JOIN log.html_scrapes s ON r.id=s.bank_responses_id
WHERE r.isid=$1 AND r.bank=$2 AND description=$3
AND a.isid=$1
ORDER BY r.id;",isid, $@, desc;
"""
    info(testQuery + " should crash")
    val testMap = Mark(testQuery, 4)
  }

  scenario("Mark Test4, Easy Mode.") {
    val testQuery =
      """
SELECT *, @1
FROM log.vju_responses r
JOIN log.html_scrapes s ON r.id=s.bank_responses_id
WHERE r.isid=$1 AND r.bank=$2 AND description=$3
AND a.isid=$1
ORDER BY r.id;",isid, $5, desc;
"""
    info(testQuery + " should crash")
    val testMap = Mark(testQuery, 40)
  }

  scenario("Mark Test5, Easy Mode.") {
    val testQuery =
      """
$1$1$$1
"""
    info(testQuery + "Should crash.")
    val testMap = Mark(testQuery, 1)
  }

  scenario("Mark Test6, Easy Mode.") {
    val testQuery =
"""
$1$2$1$1$1$1$1$2$1$1$1$1$1$1$2$1$1$1$1$1$1$1$1$2$1$1$1$1$1$2$1$1$1$1$1$1
,@2@2@2@2@2@2@2@2@2@2@2@2@2@2@2@2@2@2@2@2@2@2@2@2@2@2@2@2@2@2@2@2@2
"""
    info(testQuery + " should crash? Missing @1.")
    val testMap = Mark(testQuery, 3)
  }
}
