/*
 * Copyright (c) 2016. <jason.zou@gmail.com>
 *
 * ControlFieldTest.scala is part of marc4scala.
 *
 * marc4scala is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * marc4scala is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with marc4scala; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */

package org.marc4scala
import org.scalatest.FlatSpec

/**
  * Created by jason on 3/2/16.
  */
class ControlFieldTest extends FlatSpec{

  val controlField_008 = new ControlField("008", "Raccoons and ripe corn / ")
  val controlField_002 = new ControlField("002", "Jim Arnosky.")


  "ControlField" must "convert to string correctly" in {
    assert("008 Raccoons and ripe corn / " == controlField_008.toString)
    assert("002 Jim Arnosky." == controlField_002.toString)
    info("toString properly")
    info(controlField_008.fieldTag.getTag)
  }

  it should "throw an exception" in {
    val controlField_999_thrown = intercept[Exception] {
      val control999 = new ControlField("999", "test")
    }
    assert(controlField_999_thrown.getMessage === "Data Tag in a control field")
    info(controlField_999_thrown.getMessage)
  }

  it must "compare controlField codes correctly" in {
    assert(controlField_008.isControlField == true)
    assert(controlField_008.isControlField != false)
    assert(controlField_002.isControlField == true)
    assert(controlField_002.isControlField != false)
    assert(controlField_002.tag == "002")
    info("isCode properly")
    info(controlField_002.data)
  }

  it must "find strings correctly" in {
    assert(controlField_008.find("ripe") == true)
    assert(controlField_008.find("Ripe") == false)
    assert(controlField_002.find("Jim") == true)
    assert(controlField_002.find("Arnosky.") == true)
    info("find strings Okay")
  }

}
