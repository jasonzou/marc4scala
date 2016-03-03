/*
 * Copyright (c) 2016. <jason.zou@gmail.com>
 *
 * DataFieldTest.scala is part of marc4scala.
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
  * Created by jason on 3/3/16.
  */

class DataFieldTest extends FlatSpec{
  "DataField" must "has a correct tag and two indicators" in {
    val datafield1 = new DataField("245", '1', '0')
    assert("245" == datafield1.tag)
    assert('1' == datafield1.indicator1)
    assert('0' == datafield1.indicator2)
    info("Tag, ind1, and ind2 are goog")

  }

  it must "add a subfield correctly" in {
    val datafield1 = new DataField("245", '1', '0')
    val subfield:SubField = new SubField('a', "Summerland")
    datafield1.addSubField(subfield)
    assert(datafield1.subfields_.length == 1)
    info(datafield1.toString)
  }

  it must "add more subfields correctly" in {
    val df:DataField = new DataField("245", '1', '0', List(new SubField('a',"The summer-land")))
    val sf1:SubField = new SubField('h', "[electronic resource] : ")
    val sf2:SubField = new SubField('c', "Michael Chabon")
    df.addSubField(sf1)
    df.addSubField(sf2)
    val s:SubField = df.subfields(0)
    assert(3==df.subfields_.length)
    assert('a' == s.code)
    info(df.toString)
  }

  it must "remove one or more subfields correctly" in {
    val df:DataField = new DataField("245", '1', '0', List(new SubField('a',"The summer-land")))
    val sf1:SubField = new SubField('h', "[electronic resource] : ")
    val sf2:SubField = new SubField('c', "Michael Chabon")
    df.addSubField(sf1)
    df.addSubField(sf2)
    assert(3==df.subfields_.length)
    info(df.toString)
    df.removeSubField(sf1)
    df.removeSubField(sf1)
    info(df.toString)
    df.removeSubField(sf2)
    info(df.toString)
  }

  it must "retrieve one or more subfields correctly" in {
    val df: DataField = new DataField("245", '1', '0', List(new SubField('a', "The summer-land")))
    val sf1: SubField = new SubField('h', "[electronic resource] : ")
    val sf2: SubField = new SubField('c', "Michael Chabon")
    val sf3: SubField = new SubField('h', "3[electronic resource] : ")
    val sf4: SubField = new SubField('h', "4[electronic resource] : ")
    df.addSubField(sf1)
    df.addSubField(sf2)
    df.addSubField(sf3)
    df.addSubField(sf4)
    assert(5 == df.subfields_.length)
    info(df.toString)
    var sfs:List[SubField] = df.getSubFieldsByStr("[a-d]")
    info(sfs.length.toString)
    for(sf <- sfs){ info(sf.toString) }
    sfs = df.getSubFieldsByCode('h')
    for(sf <- sfs){ info(sf.toString) }
  }
}
