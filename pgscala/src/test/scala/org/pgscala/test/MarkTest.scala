/*package  org.pgscala
package test

import org.scalatest.{ FeatureSpec, GivenWhenThen }
import org.scalatest.matchers.MustMatchers
import org.pgscala.Mark.Indices

class MarkTestSpec extends FeatureSpec
  with GivenWhenThen
  with MustMatchers {
given("a non-empty stack")
      when("when pop is invoked on the stack")
      then("the most recently pushed element should be returned")
      and("the stack should have one less item than before")
  def getresult(res: (Indices, Indices)): String = {
    def getStringFromSeq(s: Seq[Int]) ={
      "(" + s.head + s.tail.map(", "+ _).mkString + ")"
    }
    def makeStringFromMap(map: Map[Int, Set[Int]]): String = {
      map.map(x => "(" + x._1 + " ->" + getStringFromSeq(x._2.toSeq) + ")\n" ).mkString
    }
    makeStringFromMap(res._1) +
    "--------------------\n"  +
    makeStringFromMap(res._2)

  }

  scenario("Mark Easy Test1.") {
    val testQuery =
      "Select some $1 form  @2@1$1$1$1"
    info(testQuery + " should make map$= (1->Seq(16,27,29,31,33)")
    info("should make map@= (1->Seq(25),2->Seq(23), or similar.")

    val testMap = Mark(testQuery)
    info("result : \n" + getresult(testMap))
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
    info("result : \n" + getresult(testMap))

  }

  scenario("Mark Easy Test 3.") {
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
    val testMap = Mark(testQuery)
    info("result : \n" + getresult(testMap))
  }

  scenario("Mark Easy Test 4.") {
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
    val testMap = Mark(testQuery)
    info("result : \n" + getresult(testMap))

  }

  scenario("Mark Easy Test 5.") {
    val testQuery =
      """
$1$1$$1
"""
    info(testQuery + "Should crash.")
    val testMap = Mark(testQuery)
    info("result : \n" + getresult(testMap))

  }

  scenario("Mark Stress Test 1.") {
    val testQuery =
"""
$1$2$1$1$1$1$1$2$1$1$1$1$1$1$2$1$1$1$1$1$1$1$1$2$1$1$1$1$1$2$1$1$1$1$1$1
,@2 @2 @2@2@2@2@2@2@2@2@2@2@2@2@2@2@2@2@2@2@2@2@2@2@2@2@2@2@2@2@2@2@2
"""
    info(testQuery + " should crash? Missing @1.")
    val testMap = Mark(testQuery)
    info("result : \n" + getresult(testMap))
  }
    scenario("Mark Stress Test 2.") {
    val testQuery =
"""
$111$211$111$122$12$13$1$24$3413$4156$615$614
$61$1$652$16$541$16$41$1$6514$61$164$2$1$1$1$1$1$2$1$1$1$1$1$1
,@2 @2 @2@2@4322@2432@2@2@2@242@2@2@2@2@22343
@2@2@2@2@2@2@2@2@4322@2@2@2@2@2@2@2@2@2
"""
    info(testQuery + "Should have a lot of results")
    val testMap = Mark(testQuery)
    info("result : \n" + getresult(testMap))
  }

    scenario("Mark Stress Test 3.") {
    val testQuery =
"""
$111-123
234$611231
234$01
"""
    info(testQuery + "Should have 3 results, if valid.")
    val testMap = Mark(testQuery)
    info("result : \n" + getresult(testMap))
  }
    scenario("Mark Stress Test 4.") {
    val testQuery =
"""
$ 111-123
234$ 611231
234$ 01
"""
    info(testQuery + "Should have no results")
    val testMap = Mark(testQuery)
    info("result : \n" + getresult(testMap))
  }
}
*/
