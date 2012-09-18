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
        given("an empty IndexedSeqSet")
        when("appending an immutable TreeSet")
        val tS = TreeSet("b", "u", "s", "h")
        val iSS = IndexedSeqSet.empty ++ tS
        then("the same TreeSet object must be returned through .toSet")
        assert(iSS.toSet eq tS)
        and("the sequence must retain the SortedSet ordering")
        assert(iSS.toSeq == tS.toSeq)
      }
      {
        given("an empty IndexedSeqSet")
        when("appending an immutable BitSet")
        val bS = BitSet(3, 1, 2)
        val iSS = IndexedSeqSet.empty ++ bS
        then("the same BitSet object must be returned through .toSet")
        assert(iSS.toSet eq bS)
        and("the sequence must retain the BitSet ordering")
        assert(iSS.toSeq == bS.toSeq)
      }
      {
        given("an empty IndexedSeqSet")
        when("appending a ListSet")
        val lS = ListSet('a, 'b, 'c)
        val iSS = IndexedSeqSet.empty ++ lS
        then("the same ListSet object must be returned through .toSet")
        assert(iSS.toSet eq lS)
        and("the sequence must retain the ListSet ordering")
        assert(iSS.toSeq == lS.toSeq)
      }
    }

    info("When IndexedSeqSet is constructed by appending an IndexedSeq to IndexedSeqSet.empty")
    info("it should return the same object via the .toIndexedSeq and .toSeq methods")
    info("unless the constructing collection contained duplicate elements")

    scenario("IndexedSeqSet is constructed with an IndexedSeq") {
      {
        given("an empty IndexedSeqSet")
        when("appending a Range")
        val rng = 1 to 10
        val iSS = IndexedSeqSet.empty ++ rng
        then("the same Range object must be returned through .toSeq")
        assert(iSS.toSeq eq rng)
        and("the set must contain all the elements")
        assert(iSS.toSet == rng.toSet)
      }
      {
        given("an empty IndexedSeqSet")
        when("appending a Vector with duplicate elements")
        val vec = Vector(1, 2, 3, 2, 4, 5)
        val iSS = IndexedSeqSet.empty ++ vec
        then("the same Vector object cannot be returned through .toIndexedSeq")
        assert(iSS.toIndexedSeq != vec)
        and("the new sequence ordering must be equal to the distinct sequence")
        assert(iSS.toIndexedSeq == vec.distinct)
        and("the set must contain all elements")
        assert(vec.forall(iSS.toSet))
      }
    }

    info("When IndexedSeqSet is constructed from another IndexedSeqSet")
    info("it should not instantiate a new collection")

    scenario("IndexedSeqSet is constructed with another IndexedSeqSet") {
      given("an empty IndexedSeqSet")
      val iSSempty = IndexedSeqSet.empty[Int]
      and("when appending another IndexedSeqSet")
      val iSS = IndexedSeqSet(1, 2, 3)
      val iSSnew = iSSempty ++ iSS
      then("the new IndexedSeqSet must be the same as the old one")
      assert(iSS eq iSSnew)
    }
  }

  feature("IndexedSeqSet must return itself on a noop") {

    info("When appending an element which already resides in the IndexedSeqSet")
    info("it should not instatiate a new object, but return itself")

    scenario("An already existing element is being added to the IndexedSeqSet") {
      given("an IndexedSeqSet containing an element")
      val iSS = IndexedSeqSet("a", "b", "c")
      when("appending the already contained element")
      val iSSnew = iSS + iSS.head
      then("a new IndexedSeqSet must not be created")
      assert(iSS eq iSSnew)
    }

    info("When removing an element which does not exist in the collection")
    info("it should not instatiate a new object, but return itself")

    scenario("An element not residing in the IndexedSeqSet is being removed") {
      {
        given("an empty IndexedSeqSet")
        val iSS = IndexedSeqSet.empty[String]
        when("removing any element")
        val iSSnew = iSS - "some"
        then("a new IndexedSeqSet must not be created")
        assert(iSS eq iSSnew)
      }
      {
        given("an IndexedSeqSet containing some elements")
        val iSS = IndexedSeqSet(1, 2, 3)
        when("removing an element which is not contained")
        val iSSnew = iSS - 4
        then("a new IndexedSeqSet must not be created")
        assert(iSS eq iSSnew)
      }
    }

    info("When adding an empty collection")
    info("it should not instatiate a new object, but return itself")

    scenario("An empty collection is being added to the IndexedSeqSet") {
      {
        given("an empty IndexedSeqSet")
        val iSS = IndexedSeqSet.empty[Long]
        when("adding an empty collection")
        val iSSnew = iSS ++ Nil
        then("a new IndexedSeqSet must not be created")
        assert(iSS eq iSSnew)
      }
      {
        given("an IndexedSeqSet containing some elements")
        val iSS = IndexedSeqSet(1, 2, 3)
        when("adding an empty collection")
        val iSSnew = iSS ++ BitSet.empty
        then("a new IndexedSeqSet must not be created")
        assert(iSS eq iSSnew)
      }
    }

    info("When removing an empty collection")
    info("it should not instatiate a new object, but return itself")

    scenario("An empty collection is being removed from the IndexedSeqSet") {
      {
        given("an empty IndexedSeqSet")
        val iSS = IndexedSeqSet.empty[Double]
        when("removing any collection")
        val iSSnew = iSS -- Nil
        then("a new IndexedSeqSet must not be created")
        assert(iSS eq iSSnew)
      }
      {
        given("an IndexedSeqSet containing some elements")
        val iSS = IndexedSeqSet('a, 'b, 'c)
        when("removing an empty collection")
        val iSSnew = iSS -- Nil
        then("a new IndexedSeqSet must not be created")
        assert(iSS eq iSSnew)
      }
    }

    info("When adding a collection containing elements already present")
    info("it should not instatiate a new object, but return itself")

    scenario("A collection with already contained elements is being added to the IndexedSeqSet") {
      given("an IndexedSeqSet containing some elements")
      val iSS = IndexedSeqSet('a', 'b', 'c', 'd')
      and("a collection containing only elements present in the IndexedSeqSet")
      val rng = 'b' to 'c'
      when("adding that collection to the IndexedSeqSet")
      val iSSnew = iSS ++ rng
      then("a new IndexedSeqSet must not be created")
      assert(iSS eq iSSnew)
    }

    info("When removing a collection containing none of the elements already present")
    info("it should not instatiate a new object, but return itself")

    scenario("A collection with no elements in the IndexedSeqSet is being removed") {
      {
        given("an empty IndexedSeqSet")
        val iSS = IndexedSeqSet.empty[Symbol]
        when("removing any collection")
        val iSSnew = iSS -- Vector('v, 'e, 'c)
        then("a new IndexedSeqSet must not be created")
        assert(iSS eq iSSnew)
      }
      {
        given("an IndexedSeqSet containing some elements")
        val iSS = IndexedSeqSet(1, 3, 5)
        and("a collection containing no elements already present in the IndexedSeqSet")
        val set = Set(2, 4, 6)
        when("removing that collection from the IndexedSeqSet")
        val iSSnew = iSS -- set
        then("a new IndexedSeqSet must not be created")
        assert(iSS eq iSSnew)
      }
    }
  }

  feature("IndexedSeqSet must behave like a Set") {

    info("IndexedSeqSet must reject duplicate elements")

    scenario("A range is added to IndexedSeqSet") {
      given("an IndexedSeqSet containing a range of numbers")
      val iSS = IndexedSeqSet.empty ++ (1 to 100)
      when("an overlapping range is added to the IndexedSeqSet")
      val rng = 11 to 110
      then("the new IndexedSeqSet must be the union of ranges")
      (iSS ++ rng).size must be (rng.end)
    }
  }
}
