/*
 * Copyright (c) 2016. <jason.zou@gmail.com>
 *
 * SubFieldTest.scala is part of marc4scala.
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
class SubFieldTest extends FlatSpec{

  val subfield_a = new SubField('a', "Raccoons and ripe corn / ")
  val subfield_c = new SubField('c', "Jim Arnosky.")

  "SubField" must "convert to string correctly" in {
    assert("$aRaccoons and ripe corn / " == subfield_a.toString)
    assert("$cJim Arnosky." == subfield_c.toString)
    info("toString properly")

  }

  it must "compare subfield codes correctly" in {
    assert(subfield_a.isCode('a') == true)
    assert(subfield_a.isCode('c') == false)
    assert(subfield_c.isCode('c') == true)
    assert(subfield_c.isCode('5') == false)
    assert(subfield_c.code == 'c')
    info("isCode properly")
    info(subfield_c.data)
  }

  it must "find strings correctly" in {
    assert(subfield_a.find("ripe") == true)
    assert(subfield_a.find("Ripe") == false)
    assert(subfield_c.find("Jim") == true)
    assert(subfield_c.find("Arnosky.") == true)
    info("find strings Okay")
  }
}
