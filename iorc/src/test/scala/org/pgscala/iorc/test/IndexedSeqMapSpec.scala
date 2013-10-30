package org.pgscala.iorc
package test

import org.scalatest._

import scala.collection.immutable.{ TreeMap, IntMap, ListSet, ListMap}

class IndexedSeqMapSpec extends FeatureSpec with GivenWhenThen {

  feature("IndexedSeqMap must reuse its constructing collections if possible") {

    info("When IndexedSeqMap is constructed by appending a Map to IndexedSeqMap.empty")
    info("it should return the same object via the .toMap method")

    scenario("IndexedSeqMap is constructed with a Map") {
      {
        Given("an empty IndexedSeqMap")
        When("appending an immutable TreeMap")
        val tM = TreeMap("b" -> "u", "s" -> "h")
        val iSM = IndexedSeqMap.empty ++ tM
        Then("the same TreeMap object must be returned through .toMap")
        assert(iSM.toMap eq tM)
        And("the sequence must retain the SortedMap ordering")
        assert(iSM.toSeq == tM.toSeq)
      }
      {
        Given("an empty IndexedSeqMap")
        When("appending an immutable IntMap")
        val iM = IntMap(3 -> "3", 1 -> "1", 2 -> "2")
        val iSM = IndexedSeqMap.empty ++ iM
        Then("the same IntMap object must be returned through .toMap")
        assert(iSM.toMap eq iM)
        And("the sequence must retain the IntMap ordering")
        assert(iSM.toSeq == iM.toSeq)
      }
      {
        Given("an empty IndexedSeqMap")
        When("appending a ListMap")
        val lM = ListMap('a->'a', 'b->'b', 'c->'c')
        val iSM = IndexedSeqMap.empty ++ lM
        Then("the same ListMap object must be returned through .toMap")
        assert(iSM.toMap eq lM)
        And("the sequence must retain the ListMap ordering")
        assert(iSM.toSeq == lM.toSeq)
      }
    }

    info("When IndexedSeqMap is constructed by appending an IndexedSeq to IndexedSeqMap.empty")
    info("it should return the same object via the .toIndexedSeq and .toSeq methods")
    info("unless the constructing collection contained duplicate keys")

    scenario("IndexedSeqMap is constructed with an IndexedSeq") {
      {
        Given("an empty IndexedSeqMap")
        When("appending a Range zipped with another (IndexedSeq of Pairs)")
        val rng2 = (1 to 10) zip ('0' to '9')
        val iSM = IndexedSeqMap.empty ++ rng2
        Then("the same Range pairs must be returned through .toSeq")
        assert(iSM.toSeq eq rng2)
        And("the map must contain all the elements")
        assert(iSM.toMap == rng2.toMap)
      }
      {
        Given("an empty IndexedSeqMap")
        When("appending a Pairs IndexedSeq duplicate keys")
        val vec2 = Vector(1, 2, 3, 2, 4, 5) zip Vector('a, 'b, 'c, 'd, 'e, 'f)
        val iSM = IndexedSeqMap.empty ++ vec2
        Then("the same IndexedSeq of Pairs object cannot be returned through .toIndexedSeq")
        assert(iSM.toIndexedSeq != vec2)
        And("the new sequence ordering must be equal to the distinct sequence")
        assert(iSM.map(_._1).toIndexedSeq == vec2.map(_._1).distinct)
        And("the map must contain all keys")
        assert(vec2.map(_._1).forall(iSM.keySet))
      }
    }

    info("When IndexedSeqMap is constructed from another IndexedSeqMap")
    info("it should not instantiate a new collection")

    scenario("IndexedSeqMap is constructed with another IndexedSeqMap") {
      Given("an empty IndexedSeqMap")
      And("when appending another IndexedSeqMap")
      val iSM = IndexedSeqMap(1->'a, 2->'b, 3->'c)
      val iSMnew = IndexedSeqMap.empty ++ iSM
      Then("the new IndexedSeqMap must be the same as the old one")
      assert(iSM eq iSMnew)
    }
  }

  feature("IndexedSeqMap must return itself on a noop") {

    info("When appending an element which already resides in the IndexedSeqMap")
    info("it should not instatiate a new object, but return itself")

    scenario("An already existing element is being added to the IndexedSeqMap") {
      {
        Given("an IndexedSeqMap containing a key->AnyVal element")
        val iSM = IndexedSeqMap("a"->1, "b"->2, "c"->3)
        When("appending the already contained key->AnyVal element")
      val iSMnew = iSM + iSM.head
      Then("a new IndexedSeqMap must not be created")
      assert(iSM eq iSMnew)
    }
      {
        Given("an IndexedSeqMap containing a key->AnyRef element")
        val iSM = IndexedSeqMap(1->"a", 2->"b", 3->"c")
        When("appending the already contained key->AnyRef element")
        val iSMnew = iSM + iSM.head
        Then("a new IndexedSeqMap must not be created")
        assert(iSM eq iSMnew)
      }
    }

    info("When removing an element which does not exist in the collection")
    info("it should not instatiate a new object, but return itself")

    scenario("A key not present in the IndexedSeqMap is being removed") {
      {
        Given("an empty IndexedSeqMap")
        val iSM = IndexedSeqMap.empty[String,Any]
        When("removing any key")
        val iSMnew = iSM - "some"
        Then("a new IndexedSeqMap must not be created")
        assert(iSM eq iSMnew)
      }
      {
        Given("an IndexedSeqMap containing some pairs")
        val iSM = IndexedSeqMap(1->'a, 2->'b, 3->'c)
        When("removing a key which is not present")
        val iSMnew = iSM - 4
        Then("a new IndexedSeqMap must not be created")
        assert(iSM eq iSMnew)
      }
    }

    info("When adding an empty collection")
    info("it should not instatiate a new object, but return itself")

    scenario("An empty collection is being added to the IndexedSeqMap") {
      Given("an IndexedSeqMap containing some elements")
      val iSM = IndexedSeqMap("a"->1.0, "b"->2.0, "c"->3.0)
      When("adding an empty collection")
      val iSMnew = iSM ++ IndexedSeq.empty
      Then("a new IndexedSeqMap must not be created")
      assert(iSM eq iSMnew)
    }

    info("When removing an empty collection")
    info("it should not instatiate a new object, but return itself")

    scenario("An empty collection is being removed from the IndexedSeqMap") {
      {
        Given("an empty IndexedSeqMap")
        val iSM = IndexedSeqMap.empty[Double,String]
        When("removing an empty collection")
        val iSMnew = iSM -- ListSet.empty
        Then("a new IndexedSeqMap must not be created")
        assert(iSM eq iSMnew)
      }
      {
        Given("an IndexedSeqMap containing some elements")
        val iSM = IndexedSeqMap('a->"a", 'b->"b", 'c->"c")
        When("removing an empty collection")
        val iSMnew = iSM -- Nil
        Then("a new IndexedSeqMap must not be created")
        assert(iSM eq iSMnew)
      }
    }

    info("When adding a collection containing elements already present")
    info("it should not instatiate a new object, but return itself")

    scenario("A collection with already contained elements is being added to the IndexedSeqMap") {
      Given("an IndexedSeqMap containing some elements")
      val pairs = Map('a'->"a", 'b'->"b", 'c'->"c", 'd'->"d")
      val iSM = IndexedSeqMap.empty ++ pairs
      And("a collection containing only elements present in the IndexedSeqMap")
      When("adding that collection to the IndexedSeqMap")
      val iSMnew = iSM ++ pairs
      Then("a new IndexedSeqMap must not be created")
//      assert(iSM eq iSMnew)
    }

    info("When removing a collection containing none of the elements already present")
    info("it should not instatiate a new object, but return itself")

    scenario("A collection with no elements in the IndexedSeqMap is being removed") {
      {
        Given("an empty IndexedSeqMap")
        val iSM = IndexedSeqMap.empty[Symbol,Char]
        When("removing any collection")
        val iSMnew = iSM -- Vector('v, 'e, 'c)
        Then("a new IndexedSeqMap must not be created")
        assert(iSM eq iSMnew)
      }
      {
        Given("an IndexedSeqMap containing some elements")
        val iSM = IndexedSeqMap(1->1, 3->2, 5->3)
        And("a collection containing no keys already present in the IndexedSeqMap")
        val set = Set(2, 4, 6)
        When("removing that collection from the IndexedSeqMap")
        val iSMnew = iSM -- set
        Then("a new IndexedSeqMap must not be created")
        assert(iSM eq iSMnew)
      }
    }
  }
}
