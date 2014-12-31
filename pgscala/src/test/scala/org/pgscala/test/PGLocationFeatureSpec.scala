package org.pgscala
package test

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.FeatureSpec
import org.scalatest.GivenWhenThen
import org.scalatest.Matchers
import org.pgscala.converters.PGConverter

@RunWith(classOf[JUnitRunner])
class PGLocationFeatureSpec extends FeatureSpec with GivenWhenThen with Matchers {
  def using[T](f: PGScala => T) =
    PGTestDb.sessionFactory.using(f)

  feature("PGPoint converter features"){

    scenario("Location(Double) test for (0,0)"){
      val p = new java.awt.geom.Point2D.Double(0d, 0d)
      using(_.get[java.awt.geom.Point2D.Double]("SELECT @1;", p)) should === (p)
      using(_.get[java.awt.geom.Point2D.Double]("SELECT $1;", p)) should === (p)
      using(_.get[java.awt.geom.Point2D.Double]("SELECT point(0,0);")) should === (p)
    }

    scenario("Location(Double) test for (-inf,inf)"){
      val p = new java.awt.geom.Point2D.Double(Double.PositiveInfinity, Double.NegativeInfinity)
      using(_.get[java.awt.geom.Point2D.Double]("SELECT @1;", p)) should === (p)
      using(_.get[java.awt.geom.Point2D.Double]("SELECT $1;", p)) should === (p)
      using(_.get[java.awt.geom.Point2D.Double]("SELECT point(@1,@2);", Double.PositiveInfinity, Double.NegativeInfinity)) should === (p)
      using(_.get[java.awt.geom.Point2D.Double]("SELECT point($1,$2);", Double.PositiveInfinity, Double.NegativeInfinity)) should === (p)
      using(_.get[java.awt.geom.Point2D.Double]("SELECT point('inf','-inf');")) should === (p)
    }

    scenario("Location(Double) test for (nan,nan)"){
      val p = new java.awt.geom.Point2D.Double(Double.NaN, Double.NaN)
      using(_.get[java.awt.geom.Point2D.Double]("SELECT $1;", p)).getX().isNaN should be (true)
      using(_.get[java.awt.geom.Point2D.Double]("SELECT point(@1,@2);", Double.NaN, Double.NaN)).getX().isNaN should be (true)
      using(_.get[java.awt.geom.Point2D.Double]("SELECT point($1,$2);", Double.NaN, Double.NaN)).getX().isNaN should be (true)
      using(_.get[java.awt.geom.Point2D.Double]("SELECT point('nan','nan');")).getX().isNaN should be (true)
    }

    scenario("Location(Double) test for (min,max)"){
      val p = new java.awt.geom.Point2D.Double(Double.MinValue, Double.MaxValue)
      val p2 = new java.awt.geom.Point2D.Double(Double.MinValue, Double.MinPositiveValue)
      using(_.get[java.awt.geom.Point2D.Double]("SELECT @1;", p)) should === (p)
      using(_.get[java.awt.geom.Point2D.Double]("SELECT $1;", p)) should === (p)
      using(_.get[java.awt.geom.Point2D.Double]("SELECT point(@1,@2);", Double.MinValue, Double.MaxValue)) should === (p)
      using(_.get[java.awt.geom.Point2D.Double]("SELECT point($1,$2);", Double.MinValue, Double.MaxValue)) should === (p)

      using(_.get[java.awt.geom.Point2D.Double]("SELECT @1;", p2)) should === (p2)
      using(_.get[java.awt.geom.Point2D.Double]("SELECT $1;", p2)) should === (p2)
      using(_.get[java.awt.geom.Point2D.Double]("SELECT point(@1,@2);", Double.MinPositiveValue, Double.MinPositiveValue)) should === (p2)
      using(_.get[java.awt.geom.Point2D.Double]("SELECT point($1,$2);", Double.MinPositiveValue, Double.MinPositiveValue)) should === (p2)
    }
//    scenario("Point test for (MIN, MAX)"){
//      val p = new java.awt.Point(Integer.MAX_VALUE, Integer.MIN_VALUE)
//      using(_.get[java.awt.Point]("SELECT @1;", p)) should === (p)
//      using(_.get[java.awt.Point]("SELECT $1;", p)) should === (p)
//      using(_.get[java.awt.Point]("SELECT point($1,$2);", Integer.MAX_VALUE, Integer.MIN_VALUE)) should === (p)
//    }
//
//    scenario("Point test for Random coordinates"){
//      val xCoord = scala.util.Random.nextInt()
//      val yCoord = scala.util.Random.nextInt()
//      val p = new java.awt.Point(xCoord, yCoord)
//      using(_.get[java.awt.Point]("SELECT @1;", p)) should === (p)
//      using(_.get[java.awt.Point]("SELECT $1;", p)) should === (p)
//      using(_.get[java.awt.Point]("SELECT point($1, $2);", xCoord, yCoord)) should === (p)
//    }
  }
}
