package org.pgscala.iorc
package test

import org.scalatest._

import scala.collection.immutable.{ BitSet, TreeSet, TreeMap, IntMap, ListSet, ListMap}

class IndexedSeqSetSpec extends FeatureSpec with GivenWhenThen with MustMatchers {

  feature("IndexedSeqSet must reuse its constructing collections if possible") {

    info("When IndexedSeqSet is constructed by appending a Set to IndexedSeqSet.empty")
    info("it should return the same object via the .toSet method")

    scenario("IndexedSeqSet is constructed with a Set") {
      {
        Given("an empty IndexedSeqSet")
        When("appending an immutable TreeSet")
        val tS = TreeSet("b", "u", "s", "h")
        val iSS = IndexedSeqSet.empty ++ tS
        Then("the same TreeSet object must be returned through .toSet")
        assert(iSS.toSet eq tS)
        And("the sequence must retain the SortedSet ordering")
        assert(iSS.toSeq == tS.toSeq)
      }
      {
        Given("an empty IndexedSeqSet")
        When("appending an immutable BitSet")
        val bS = BitSet(3, 1, 2)
        val iSS = IndexedSeqSet.empty ++ bS
        Then("the same BitSet object must be returned through .toSet")
        assert(iSS.toSet eq bS)
        And("the sequence must retain the BitSet ordering")
        assert(iSS.toSeq == bS.toSeq)
      }
      {
        Given("an empty IndexedSeqSet")
        When("appending a ListSet")
        val lS = ListSet('a, 'b, 'c)
        val iSS = IndexedSeqSet.empty ++ lS
        Then("the same ListSet object must be returned through .toSet")
        assert(iSS.toSet eq lS)
        And("the sequence must retain the ListSet ordering")
        assert(iSS.toSeq == lS.toSeq)
      }
    }

    info("When IndexedSeqSet is constructed by appending an IndexedSeq to IndexedSeqSet.empty")
    info("it should return the same object via the .toIndexedSeq and .toSeq methods")
    info("unless the constructing collection contained duplicate elements")

    scenario("IndexedSeqSet is constructed with an IndexedSeq") {
      {
        Given("an empty IndexedSeqSet")
        When("appending a Range")
        val rng = 1 to 10
        val iSS = IndexedSeqSet.empty ++ rng
        Then("the same Range object must be returned through .toSeq")
        assert(iSS.toSeq eq rng)
        And("the set must contain all the elements")
        assert(iSS.toSet == rng.toSet)
      }
      {
        Given("an empty IndexedSeqSet")
        When("appending a Vector with duplicate elements")
        val vec = Vector(1, 2, 3, 2, 4, 5)
        val iSS = IndexedSeqSet.empty ++ vec
        Then("the same Vector object cannot be returned through .toIndexedSeq")
        assert(iSS.toIndexedSeq != vec)
        And("the new sequence ordering must be equal to the distinct sequence")
        assert(iSS.toIndexedSeq == vec.distinct)
        And("the set must contain all elements")
        assert(vec.forall(iSS.toSet))
      }
    }

    info("When IndexedSeqSet is constructed from another IndexedSeqSet")
    info("it should not instantiate a new collection")

    scenario("IndexedSeqSet is constructed with another IndexedSeqSet") {
      Given("an empty IndexedSeqSet")
      val iSSempty = IndexedSeqSet.empty[Int]
      And("when appending another IndexedSeqSet")
      val iSS = IndexedSeqSet(1, 2, 3)
      val iSSnew = iSSempty ++ iSS
      Then("the new IndexedSeqSet must be the same as the old one")
      assert(iSS eq iSSnew)
    }
  }

  feature("IndexedSeqSet must return itself on a noop") {

    info("When appending an element which already resides in the IndexedSeqSet")
    info("it should not instatiate a new object, but return itself")

    scenario("An already existing element is being added to the IndexedSeqSet") {
      Given("an IndexedSeqSet containing an element")
      val iSS = IndexedSeqSet("a", "b", "c")
      When("appending the already contained element")
      val iSSnew = iSS + iSS.head
      Then("a new IndexedSeqSet must not be created")
      assert(iSS eq iSSnew)
    }

    info("When removing an element which does not exist in the collection")
    info("it should not instatiate a new object, but return itself")

    scenario("An element not residing in the IndexedSeqSet is being removed") {
      {
        Given("an empty IndexedSeqSet")
        val iSS = IndexedSeqSet.empty[String]
        When("removing any element")
        val iSSnew = iSS - "some"
        Then("a new IndexedSeqSet must not be created")
        assert(iSS eq iSSnew)
      }
      {
        Given("an IndexedSeqSet containing some elements")
        val iSS = IndexedSeqSet(1, 2, 3)
        When("removing an element which is not contained")
        val iSSnew = iSS - 4
        Then("a new IndexedSeqSet must not be created")
        assert(iSS eq iSSnew)
      }
    }

    info("When adding an empty collection")
    info("it should not instatiate a new object, but return itself")

    scenario("An empty collection is being added to the IndexedSeqSet") {
      {
        Given("an empty IndexedSeqSet")
        val iSS = IndexedSeqSet.empty[Long]
        When("adding an empty collection")
        val iSSnew = iSS ++ Nil
        Then("a new IndexedSeqSet must not be created")
        assert(iSS eq iSSnew)
      }
      {
        Given("an IndexedSeqSet containing some elements")
        val iSS = IndexedSeqSet(1, 2, 3)
        When("adding an empty collection")
        val iSSnew = iSS ++ BitSet.empty
        Then("a new IndexedSeqSet must not be created")
        assert(iSS eq iSSnew)
      }
    }

    info("When removing an empty collection")
    info("it should not instatiate a new object, but return itself")

    scenario("An empty collection is being removed from the IndexedSeqSet") {
      {
        Given("an empty IndexedSeqSet")
        val iSS = IndexedSeqSet.empty[Double]
        When("removing any collection")
        val iSSnew = iSS -- Nil
        Then("a new IndexedSeqSet must not be created")
        assert(iSS eq iSSnew)
      }
      {
        Given("an IndexedSeqSet containing some elements")
        val iSS = IndexedSeqSet('a, 'b, 'c)
        When("removing an empty collection")
        val iSSnew = iSS -- Nil
        Then("a new IndexedSeqSet must not be created")
        assert(iSS eq iSSnew)
      }
    }

    info("When adding a collection containing elements already present")
    info("it should not instatiate a new object, but return itself")

    scenario("A collection with already contained elements is being added to the IndexedSeqSet") {
      Given("an IndexedSeqSet containing some elements")
      val iSS = IndexedSeqSet('a', 'b', 'c', 'd')
      And("a collection containing only elements present in the IndexedSeqSet")
      val rng = 'b' to 'c'
      When("adding that collection to the IndexedSeqSet")
      val iSSnew = iSS ++ rng
      Then("a new IndexedSeqSet must not be created")
      assert(iSS eq iSSnew)
    }

    info("When removing a collection containing none of the elements already present")
    info("it should not instatiate a new object, but return itself")

    scenario("A collection with no elements in the IndexedSeqSet is being removed") {
      {
        Given("an empty IndexedSeqSet")
        val iSS = IndexedSeqSet.empty[Symbol]
        When("removing any collection")
        val iSSnew = iSS -- Vector('v, 'e, 'c)
        Then("a new IndexedSeqSet must not be created")
        assert(iSS eq iSSnew)
      }
      {
        Given("an IndexedSeqSet containing some elements")
        val iSS = IndexedSeqSet(1, 3, 5)
        And("a collection containing no elements already present in the IndexedSeqSet")
        val set = Set(2, 4, 6)
        When("removing that collection from the IndexedSeqSet")
        val iSSnew = iSS -- set
        Then("a new IndexedSeqSet must not be created")
        assert(iSS eq iSSnew)
      }
    }
  }

  feature("IndexedSeqSet must behave like a Set") {

    info("IndexedSeqSet must reject duplicate elements")

    scenario("A range is added to IndexedSeqSet") {
      Given("an IndexedSeqSet containing a range of numbers")
      val iSS = IndexedSeqSet.empty ++ (1 to 100)
      When("an overlapping range is added to the IndexedSeqSet")
      val rng = 11 to 110
      Then("the new IndexedSeqSet must be the union of ranges")
      (iSS ++ rng).size must be (rng.end)
    }
  }
}
