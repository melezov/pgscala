package org.pgscala
package test

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.FeatureSpec
import org.scalatest.GivenWhenThen
import org.scalatest.Matchers

@RunWith(classOf[JUnitRunner])
class PGScalaFeatureSpec extends FeatureSpec with GivenWhenThen with Matchers {
  def using[T](f: PGScala => T) =
    PGTestDb.sessionFactory.using(f)

  feature("PGScala features"){

    scenario("BigDecimal round about test"){
      val bd = BigDecimal("1.23456789012345678901234567890")
      using(_.get[BigDecimal]("SELECT @1;", bd)) should === (bd)
      using(_.get[BigDecimal]("SELECT $1;", bd)) should === (bd)
    }
  }
}
