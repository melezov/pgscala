package hr.element.pgscala.util
package test
package record

import org.scalatest.{FeatureSpec, GivenWhenThen}
import org.scalatest.matchers.MustMatchers

class PGIdentSpec extends FeatureSpec
                  with GivenWhenThen
                  with MustMatchers {

  feature("Identities can be quoted in PostgreSQL manner") {
/*
    val origStr = """It's OK -> \ Don"t {worry} be (happy)! /"""
    val quotStr = """"It's OK -> \\ Don""t {worry} be (happy)! /""""

    scenario("Ident quoting mimics PostgreSQL quote_ident") {
      val dbQuotStr = PGTestDb.qry("SELECT quote_ident(%s);" format quotStr){ rS =>
        rS.next()
        rS.getString(1)
      }

      dbQuotStr must equal(quotStr)
    }
*/
  }
}
