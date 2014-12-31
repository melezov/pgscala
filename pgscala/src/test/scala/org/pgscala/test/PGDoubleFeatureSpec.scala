package org.pgscala
package test

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.FeatureSpec
import org.scalatest.GivenWhenThen
import org.scalatest.Matchers
import org.pgscala.converters.PGConverter

@RunWith(classOf[JUnitRunner])
class PGDoubleFeatureSpec extends FeatureSpec with GivenWhenThen with Matchers {
  def using[T](f: PGScala => T) =
    PGTestDb.sessionFactory.using(f)

  feature("PGDouble converter features"){

    scenario("Double test 1 . 0"){
      val p = 0d
      using(_.get[Double]("SELECT @1;", p)) should === (p)
      using(_.get[Double]("SELECT $1;", p)) should === (p)
      using(_.get[Double]("SELECT 0::double precision;")) should === (p)
    }

    scenario("Double test 2 - NaN"){
      val p = Double.NaN
      using(_.get[Double]("SELECT @1;", p)).isNaN() should be (true)
      using(_.get[Double]("SELECT $1;", p)).isNaN() should be (true)
      using(_.get[Double]("SELECT 'nan'::double precision;").isNaN()) should be (true)
    }

    scenario("Double test 2 - Min Positive value"){
      val p = Double.MinPositiveValue
      using(_.get[Double]("SELECT 4.9E-324::decimal;")) should === (p)
      using(_.get[Double]("SELECT 4.9E-324::numeric;")) should === (p)
      using(_.get[Double]("SELECT @1;", p)) should === (p)
      using(_.get[Double]("SELECT $1;", p)) should === (p)
      using(_.get[Double]("SELECT 4.9E-324::double precision;")) should === (p)
    }

  }
}
