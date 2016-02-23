package org.marc4scala

/*
 * Copyright (c) 2016. <jason.zou@gmail.com>
 *
 * This file is part of MARC4Scala.
 *
 * MARC4Scala is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * MARC4Scala is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with MARC4Scala; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */

/**
  * Created by jason on 2016-02-19.
  */
object Constants {
  /** RECORD TERMINATOR */
  val RecordTerminator: Byte = 0x1D

  /** FIELD TERMINATOR */
  val FieldTerminator: Byte = 0x1E

  /** SUBFIELD DELIMITER */
  val SubfieldDelimiter: Byte = 0x1F

  /** BLANK */
  val BlankSpace: Byte = 0x20

  /** NS URI */
  val MARCXML_NS_URI: String = "http://www.loc.gov/MARC21/slim"

  /** MARC-8 ANSEL ENCODING **/
  val MARC_8_ENCODING: String = "MARC8"

  /** ISO5426 ENCODING **/
  val ISO5426_ENCODING: String = "ISO5426"

  /** ISO6937 ENCODING **/
  val ISO6937_ENCODING: String = "ISO6937"

  val DirectoryEntryLength: Short = 12
  val LeaderLength: Short = 24
}
/*
 try {
            String s;
            s = ldr.substring(0, 5);
            if (isInteger(s))
                setRecordLength(Integer.parseInt(s));
            else
                setRecordLength(0);
            setRecordStatus(ldr.charAt(5));
            setTypeOfRecord(ldr.charAt(6));
            setImplDefined1(ldr.substring(7, 9).toCharArray());
            setCharCodingScheme(ldr.charAt(9));
            s = String.valueOf(ldr.charAt(10));
            if (isInteger(s))
                setIndicatorCount(Integer.parseInt(s));
            else
                setIndicatorCount(2);
            s = String.valueOf(ldr.charAt(11));
            if (isInteger(s))
                setSubfieldCodeLength(Integer.parseInt(s));
            else
                setSubfieldCodeLength(2);
            s = ldr.substring(12, 17);
            if (isInteger(s))
                setBaseAddressOfData(Integer.parseInt(s));
            else
                setBaseAddressOfData(0);
            setImplDefined2(ldr.substring(17, 20).toCharArray());
            setEntryMap(ldr.substring(20, 24).toCharArray());
        } catch (NumberFormatException e) {
            throw new RuntimeException("Unable to parse leader", e);
        }
*/
