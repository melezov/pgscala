package org.pgscala.converters
package test

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.FeatureSpec
import org.scalatest.GivenWhenThen
import org.scalatest.Matchers

import java.awt.image.BufferedImage
import scala.util.Random

@RunWith(classOf[JUnitRunner])
class BufferedImageTest extends FeatureSpec with GivenWhenThen with Matchers {
  feature("About to test a BufferedImage converter") {
    info("I want to test if PGNullableBufferedImageConverter works correctly,")
    info("by performing a couple roundabout conversion testing image -> string -> image operations")

    def imageCompare(expected: BufferedImage, actual: BufferedImage): Unit = {
      val width = expected.getWidth
      val height = expected.getHeight
      actual.getWidth should equal(width)
      actual.getHeight should equal(height)

      val expectedBody = expected.getRGB(0, 0, width, height, null, 0, width)
      val actualBody = actual.getRGB(0, 0, width, height, null, 0, width)
      actualBody should equal(expectedBody)
    }

    scenario("BufferedImage roundabout test 1x1 black") {
      Given("a starting BufferedImage with one black pixel")
      val img = new BufferedImage(1, 1, BufferedImage.TYPE_4BYTE_ABGR)
      When("that value is converted to String")
      val imgStr = PGNullableBufferedImageConverter bufferedImageToString img
      And("""that String is converted back into an image""")
      val strImg = PGNullableBufferedImageConverter.stringToBufferedImage(imgStr)
      imageCompare(img, strImg)
    }

    scenario("BufferedImage roundabout test 1x1 blue") {
      Given("a starting BufferedImage with one black pixel")
      val img = new BufferedImage(1, 1, BufferedImage.TYPE_4BYTE_ABGR)
      img.setRGB(0, 0, 0xff0000ff);
      When("that value is converted to String")
      val imgStr = PGNullableBufferedImageConverter bufferedImageToString img
      And("""that String is converted back into an image""")
      val strImg = PGNullableBufferedImageConverter.stringToBufferedImage(imgStr)
      imageCompare(img, strImg)
    }

    scenario("BufferedImage roundabout test NxM random pixels RGB") {
      Given("a starting BufferedImage of random size")
      val width = Random.nextInt(256) + 256
      val height = Random.nextInt(256) + 256
      val body = Array.fill(width * height)(Random.nextInt)

      val img = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR)
      img.setRGB(0, 0, width, height, body, 0, width)
      When("that value is converted to String")
      val imgStr = PGNullableBufferedImageConverter bufferedImageToString img
      And("""that String is converted back into an image""")
      val strImg = PGNullableBufferedImageConverter.stringToBufferedImage(imgStr)

      val newBody = body.map(_ | Random.nextInt(256) << 24)
      img.setRGB(0, 0, width, height, newBody, 0, width)
      imageCompare(img, strImg)
    }

    scenario("BufferedImage roundabout test NxM random pixels ARGB") {
      Given("a starting BufferedImage of random size")
      val width = Random.nextInt(256) + 256
      val height = Random.nextInt(256) + 256
      val body = Array.fill(width * height)(Random.nextInt)

      val img = new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR)
      img.setRGB(0, 0, width, height, body, 0, width)
      When("that value is converted to String")
      val imgStr = PGNullableBufferedImageConverter bufferedImageToString img
      And("""that String is converted back into an image""")
      val strImg = PGNullableBufferedImageConverter.stringToBufferedImage(imgStr)
      imageCompare(img, strImg)
    }

    scenario("BufferedImage roundabout test NxM random pixels 8bit") {
      Given("a starting BufferedImage of random size")
      val width = Random.nextInt(256) + 256
      val height = Random.nextInt(256) + 256
      val body = Array.fill(width * height)(Random.nextInt)

      val img = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_INDEXED)
      img.setRGB(0, 0, width, height, body, 0, width)
      When("that value is converted to String")
      val imgStr = PGNullableBufferedImageConverter bufferedImageToString img
      And("""that String is converted back into an image""")
      val strImg = PGNullableBufferedImageConverter.stringToBufferedImage(imgStr)
      imageCompare(img, strImg)
    }
  }
}
