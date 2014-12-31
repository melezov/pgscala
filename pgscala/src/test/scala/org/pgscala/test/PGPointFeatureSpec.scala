package org.pgscala
package test

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.FeatureSpec
import org.scalatest.GivenWhenThen
import org.scalatest.Matchers

@RunWith(classOf[JUnitRunner])
class PGPointFeatureSpec extends FeatureSpec with GivenWhenThen with Matchers {
  def using[T](f: PGScala => T) =
    PGTestDb.sessionFactory.using(f)

  feature("PGPoint converter features"){

    scenario("Point test For (0,0)"){
      val p = new java.awt.Point(0, 0)
      using(_.get[java.awt.Point]("SELECT @1;", p)) should === (p)
      using(_.get[java.awt.Point]("SELECT $1;", p)) should === (p)
      using(_.get[java.awt.Point]("SELECT point(0,0);")) should === (p)
    }

    scenario("Point test for (MIN, MAX)"){
      val p = new java.awt.Point(Integer.MAX_VALUE, Integer.MIN_VALUE)
      using(_.get[java.awt.Point]("SELECT @1;", p)) should === (p)
      using(_.get[java.awt.Point]("SELECT $1;", p)) should === (p)
      using(_.get[java.awt.Point]("SELECT point($1,$2);", Integer.MAX_VALUE, Integer.MIN_VALUE)) should === (p)
    }

    scenario("Point test for Random coordinates"){
      val xCoord = scala.util.Random.nextInt()
      val yCoord = scala.util.Random.nextInt()
      val p = new java.awt.Point(xCoord, yCoord)
      using(_.get[java.awt.Point]("SELECT @1;", p)) should === (p)
      using(_.get[java.awt.Point]("SELECT $1;", p)) should === (p)
      using(_.get[java.awt.Point]("SELECT point($1, $2);", xCoord, yCoord)) should === (p)
    }
  }
}
