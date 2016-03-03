/*
 * Copyright (c) 2016. [jason.zou@gmail.com]
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
    val datafield1 = new DataField("245", '1' , '0')
    val subfield:SubField = new SubField('a', "Summerland")
    datafield1.addSubField(subfield)
    assert(datafield1.subfields_.length == 1)
    info(datafield1.toString)
  }

  it must "add more subfields correctly" in {
    val df:DataField = new DataField("245", '1', '0', List(new SubField('a', "The summer-land")))
    val sf1:SubField = new SubField('h', "[electronic resource] : ")
    val sf2:SubField = new SubField('c',  "Michael Chabon")
    df.addSubField(sf1)
    df.addSubField(sf2)
    val s:SubField = df.subfields(0)
    assert(3==df.subfields_.length)
    assert('a' == s.code)
    info(df.toString)
  }

  it must "remove one or more subfields correctly" in {
    val df:DataField = new DataField("245", '1', '0', List(new SubField('a', "The summer-land")))
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
    val df: DataField = new DataField("245", '0',  '4',  List(new SubField('a', "The summer-land")))
    val sf1: SubField = new SubField('h',  "[electronic resource] : ")
    val sf2: SubField = new SubField('c', "Michael Chabon")
    val sf3: SubField = new SubField('h',  "3[electronic resource] : ")
    val sf4: SubField = new SubField('b',  "a southern story / ")
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

    val sList:List[SubField] = df.getSubFieldsByStr("a");
    assert(1 == sList.length);
    assert('a' ==  sList(0).code);
/*
    val sList2:List[SubField] = df.getSubFieldsByStr("ac");
    info("ac ===========================================")
    for(sf <- sList2){ info(sf.toString) }
    assert(2 == sList2.length);
    assert('a' ==  sList2(0).code);
    assert('c' ==  sList2(1).code);
    for(sf <- sList2){ info(sf.toString) }
*/
    val sList3:List[SubField] = df.getSubFieldsByStr("[a-c]");
    info("[a-c] ===========================================")
    for(sf <- sList3){ info(sf.toString) }
    assert(3 == sList3.length);
    assert('a' ==  sList3(0).code);
    assert('c' ==  sList3(1).code);
    assert('b' ==  sList3(2).code);
    for(sf <- sList3){ info(sf.toString) }

    val sList4:List[SubField] = df.getSubFieldsByStr("[a-cg-j]");
    info("[a-cg-j] ===========================================")
    for(sf <- sList4){ info(sf.toString) }
    assert(5 ==  sList4.length);
    assert('a' ==  sList4(0).code);
    assert('h' ==  sList4(1).code);
    assert('c' ==  sList4(2).code);
    assert('b' ==  sList4(4).code);

    val sList5:List[SubField] = df.getSubFieldsByStr("[g-ja-b]");
    info("[g-ja-b] ===========================================")
    for(sf <- sList5){ info(sf.toString) }
    assert(4 == sList5.length);
    assert('a' ==  sList5(0).code);
    assert('h' ==  sList5(1).code);
    assert('h' ==  sList5(2).code);
    assert('b' ==  sList5(3).code);

    val sList6: List[SubField] =  df.getSubFieldsByStr("[a-eb]");
    info("[a-eb] ===========================================")
    for(sf <- sList6){ info(sf.toString) }
    assert(3 == sList6.length);
    assert('a' ==  sList6(0).code);
    assert('c' ==  sList6(1).code);
    assert('b' ==  sList6(2).code);

    val sList7: List[SubField] =  df.getSubFieldsByStr("[^h]");
    info("[^h] ===========================================")
    for(sf <- sList7){ info(sf.toString) }
    assert(3 == sList7.length);
    assert('a' ==  sList7(0).code);
    assert('c' ==  sList7(1).code);
    assert('b' ==  sList7(2).code);

    val sList8:List[SubField] =  df.getSubFieldsByStr("[a-z&&[^bc]]");
    info("[a-z&&[^bc]] ===========================================")
    for(sf <- sList8){ info(sf.toString) }
    assert(3 ==  sList8.length);
    assert('a' ==  sList8(0).code);
    assert('h' ==  sList8(1).code);
    assert('h' ==  sList8(2).code);
  }
}

/*
  @Test
  public void testGetSubFieldsWithBadSubFieldSpec1() {
    DataField df = factory.newDataField("245", '0' ==  '4');
    SubField sf1 = factory.newSubField('a' ==  "The summer-land ");
    SubField sf2 = factory.newSubField('h' ==  "[electronic resource] : ");
    SubField sf3 = factory.newSubField('b' ==  "a southern story / ");
    SubField sf4 = factory.newSubField('c' ==  "by a child of the sun.");
    df.addSubField(sf1);
    df.addSubField(sf2);
    df.addSubField(sf3);
    df.addSubField(sf4);

    exception.expect(PatternSyntaxException.class);
    df.getSubFields("[c-a]");
  }

  @Test
  public void testGetSubFieldsWithBadSubFieldSpec2() {
    DataField df = factory.newDataField("245", '0' ==  '4');
    SubField sf1 = factory.newSubField('a' ==  "The summer-land ");
    SubField sf2 = factory.newSubField('h' ==  "[electronic resource] : ");
    SubField sf3 = factory.newSubField('b' ==  "a southern story / ");
    SubField sf4 = factory.newSubField('c' ==  "by a child of the sun.");
    df.addSubField(sf1);
    df.addSubField(sf2);
    df.addSubField(sf3);
    df.addSubField(sf4);

    exception.expect(PatternSyntaxException.class);
    df.getSubFields("[abc");
  }

  @Test
  public void testComparable() throws Exception {
    DataField df1 = factory.newDataField("600", '0' ==  '0');
    DataField df2 = factory.newDataField("600", '0' ==  '0');
    assert(0, df1.compareTo(df2));
    df2.setTag("245");
    assert(4, df1.compareTo(df2));
    df2.setTag("700");
    assert(-1, df1.compareTo(df2));
  }
*/
