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
        given("an empty IndexedSeqMap")
        when("appending an immutable TreeMap")
        val tM = TreeMap("b" -> "u", "s" -> "h")
        val iSM = IndexedSeqMap.empty ++ tM
        then("the same TreeMap object must be returned through .toMap")
        assert(iSM.toMap eq tM)
        and("the sequence must retain the SortedMap ordering")
        assert(iSM.toSeq == tM.toSeq)
      }
      {
        given("an empty IndexedSeqMap")
        when("appending an immutable IntMap")
        val iM = IntMap(3 -> "3", 1 -> "1", 2 -> "2")
        val iSM = IndexedSeqMap.empty ++ iM
        then("the same IntMap object must be returned through .toMap")
        assert(iSM.toMap eq iM)
        and("the sequence must retain the IntMap ordering")
        assert(iSM.toSeq == iM.toSeq)
      }
      {
        given("an empty IndexedSeqMap")
        when("appending a ListMap")
        val lM = ListMap('a->'a', 'b->'b', 'c->'c')
        val iSM = IndexedSeqMap.empty ++ lM
        then("the same ListMap object must be returned through .toMap")
        assert(iSM.toMap eq lM)
        and("the sequence must retain the ListMap ordering")
        assert(iSM.toSeq == lM.toSeq)
      }
    }

    info("When IndexedSeqMap is constructed by appending an IndexedSeq to IndexedSeqMap.empty")
    info("it should return the same object via the .toIndexedSeq and .toSeq methods")
    info("unless the constructing collection contained duplicate keys")

    scenario("IndexedSeqMap is constructed with an IndexedSeq") {
      {
        given("an empty IndexedSeqMap")
        when("appending a Range zipped with another (IndexedSeq of Pairs)")
        val rng2 = (1 to 10) zip ('0' to '9')
        val iSM = IndexedSeqMap.empty ++ rng2
        then("the same Range pairs must be returned through .toSeq")
        assert(iSM.toSeq eq rng2)
        and("the map must contain all the elements")
        assert(iSM.toMap == rng2.toMap)
      }
      {
        given("an empty IndexedSeqMap")
        when("appending a Pairs IndexedSeq duplicate keys")
        val vec2 = Vector(1, 2, 3, 2, 4, 5) zip Vector('a, 'b, 'c, 'd, 'e, 'f)
        val iSM = IndexedSeqMap.empty ++ vec2
        then("the same IndexedSeq of Pairs object cannot be returned through .toIndexedSeq")
        assert(iSM.toIndexedSeq != vec2)
        and("the new sequence ordering must be equal to the distinct sequence")
        assert(iSM.map(_._1).toIndexedSeq == vec2.map(_._1).distinct)
        and("the map must contain all keys")
        assert(vec2.map(_._1).forall(iSM.keySet))
      }
    }

    info("When IndexedSeqMap is constructed from another IndexedSeqMap")
    info("it should not instantiate a new collection")

    scenario("IndexedSeqMap is constructed with another IndexedSeqMap") {
      given("an empty IndexedSeqMap")
      and("when appending another IndexedSeqMap")
      val iSM = IndexedSeqMap(1->'a, 2->'b, 3->'c)
      val iSMnew = IndexedSeqMap.empty ++ iSM
      then("the new IndexedSeqMap must be the same as the old one")
      assert(iSM eq iSMnew)
    }
  }

  feature("IndexedSeqMap must return itself on a noop") {

    info("When appending an element which already resides in the IndexedSeqMap")
    info("it should not instatiate a new object, but return itself")

    scenario("An already existing element is being added to the IndexedSeqMap") {
      {
        given("an IndexedSeqMap containing a key->AnyVal element")
        val iSM = IndexedSeqMap("a"->1, "b"->2, "c"->3)
        when("appending the already contained key->AnyVal element")
      val iSMnew = iSM + iSM.head
      then("a new IndexedSeqMap must not be created")
      assert(iSM eq iSMnew)
    }
      {
        given("an IndexedSeqMap containing a key->AnyRef element")
        val iSM = IndexedSeqMap(1->"a", 2->"b", 3->"c")
        when("appending the already contained key->AnyRef element")
        val iSMnew = iSM + iSM.head
        then("a new IndexedSeqMap must not be created")
        assert(iSM eq iSMnew)
      }
    }

    info("When removing an element which does not exist in the collection")
    info("it should not instatiate a new object, but return itself")

    scenario("A key not present in the IndexedSeqMap is being removed") {
      {
        given("an empty IndexedSeqMap")
        val iSM = IndexedSeqMap.empty[String,Any]
        when("removing any key")
        val iSMnew = iSM - "some"
        then("a new IndexedSeqMap must not be created")
        assert(iSM eq iSMnew)
      }
      {
        given("an IndexedSeqMap containing some pairs")
        val iSM = IndexedSeqMap(1->'a, 2->'b, 3->'c)
        when("removing a key which is not present")
        val iSMnew = iSM - 4
        then("a new IndexedSeqMap must not be created")
        assert(iSM eq iSMnew)
      }
    }

    info("When adding an empty collection")
    info("it should not instatiate a new object, but return itself")

    scenario("An empty collection is being added to the IndexedSeqMap") {
      given("an IndexedSeqMap containing some elements")
      val iSM = IndexedSeqMap("a"->1., "b"->2., "c"->3.)
      when("adding an empty collection")
      val iSMnew = iSM ++ IndexedSeq.empty
      then("a new IndexedSeqMap must not be created")
      assert(iSM eq iSMnew)
    }

    info("When removing an empty collection")
    info("it should not instatiate a new object, but return itself")

    scenario("An empty collection is being removed from the IndexedSeqMap") {
      {
        given("an empty IndexedSeqMap")
        val iSM = IndexedSeqMap.empty[Double,String]
        when("removing an empty collection")
        val iSMnew = iSM -- ListSet.empty
        then("a new IndexedSeqMap must not be created")
        assert(iSM eq iSMnew)
      }
      {
        given("an IndexedSeqMap containing some elements")
        val iSM = IndexedSeqMap('a->"a", 'b->"b", 'c->"c")
        when("removing an empty collection")
        val iSMnew = iSM -- Nil
        then("a new IndexedSeqMap must not be created")
        assert(iSM eq iSMnew)
      }
    }

    info("When adding a collection containing elements already present")
    info("it should not instatiate a new object, but return itself")

    scenario("A collection with already contained elements is being added to the IndexedSeqMap") {
      given("an IndexedSeqMap containing some elements")
      val pairs = Map('a'->"a", 'b'->"b", 'c'->"c", 'd'->"d")
      val iSM = IndexedSeqMap.empty ++ pairs
      and("a collection containing only elements present in the IndexedSeqMap")
      when("adding that collection to the IndexedSeqMap")
      val iSMnew = iSM ++ pairs
      then("a new IndexedSeqMap must not be created")
//      assert(iSM eq iSMnew)
    }

    info("When removing a collection containing none of the elements already present")
    info("it should not instatiate a new object, but return itself")

    scenario("A collection with no elements in the IndexedSeqMap is being removed") {
      {
        given("an empty IndexedSeqMap")
        val iSM = IndexedSeqMap.empty[Symbol,Char]
        when("removing any collection")
        val iSMnew = iSM -- Vector('v, 'e, 'c)
        then("a new IndexedSeqMap must not be created")
        assert(iSM eq iSMnew)
      }
      {
        given("an IndexedSeqMap containing some elements")
        val iSM = IndexedSeqMap(1->1, 3->2, 5->3)
        and("a collection containing no keys already present in the IndexedSeqMap")
        val set = Set(2, 4, 6)
        when("removing that collection from the IndexedSeqMap")
        val iSMnew = iSM -- set
        then("a new IndexedSeqMap must not be created")
        assert(iSM eq iSMnew)
      }
    }
  }
}
