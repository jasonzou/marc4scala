/*
 * Copyright (c) 2016. <jason.zou@gmail.com>
 *
 * Leader.scala is part of marc4scala.
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

/**
 * MARC Leader
 *   based on marc4j
 *   TODO: there are at least biblio, authority, and holding different
 *         leaders (some differences)
 *
 * Created by jason on 2/22/16.
 * Leader: Bibliographic Data https://www.loc.gov/marc/bibliographic/ecbdlist.html
 *         Authority Data https://www.loc.gov/marc/authority/ecadlist.html
 *         Holding Data https://www.loc.gov/marc/holdings/echdlist.html
 *         Classification Data https://www.loc.gov/marc/classification/eccdlist.html
 *         Community Data https://www.loc.gov/marc/community/eccilist.html
 *   LEADER - Bibliogrpahic Data
 *   Character Positions
 *   00-04 - Logical record length
 *   05 - Record status
 *   06 - Type of record
 *   07 - Bibliographic level      
 *   08 - Type of control
 *   09 - Character coding scheme
 *   10 - Indicator count
 *   11 - Subfield code count
 *   12-16 - Base address of data
 *   17 - Encoding level
 *   18 - Descriptive cataloging form
 *   19 - Multipart resource record level
 *   20-23 - Entry map
 *   20 - Length of the length-of-field portion
 *   21 - Length of the starting-character-position portion
 *   22 - Length of the implementation-defined portion
 *   23 - Undefined Entry map character position 
 */
class Leader {
  private var _recordLength: Int = 0
  private var _recordStatus: Char = 0
  private var _typeOfRecord: Char = 0
  private var _implDefined1: Array[Char] = new Array[Char](2)
  private var _charCodingScheme: Char = 0
  private var _indicatorCount: Int = 0
  private var _subfieldCodeLength: Int = 0
  private var _baseAddressOfData: Int = 0
  private var _implDefined2: Array[Char] = new Array[Char](3)
  private var _entryMap: Array[Char] = new Array[Char](4)
  private var _leaderArray: Array[Byte] = new Array[Byte](Constants.LeaderLength)

  /**
    * Sets the logical record length (positions 00-04).
    * @param recordLength
    * integer representing the record length
    */
  def recordLength_(recordLength: Int){
    _recordLength = recordLength
  }
  def recordLength = _recordLength

  /**
    * Sets the record status (position 05).
    *
    * @param recordStatus
    * character representing the record status
    */
  def recordStatus_(recordStatus: Char){
    _recordStatus = recordStatus
  }
  def recordStatus = _recordStatus

  /**
    * Sets the type of record (position 06).
    *
    * @param typeOfRecord
    * character representing the type of record
    */
  def typeOfRecord_(typeOfRecord: Char){
    _typeOfRecord = typeOfRecord
  }
  def typeOfRecord = _typeOfRecord

  /**
    * Sets implementation defined values (position 07-08).
    *
    * @param implDefined1
    * character array representing the implementation defined data
    */
  def implDefined1_(implDefined1: Array[Char]){
    _implDefined1 = implDefined1
  }
  def implDefined1 = _implDefined1

  /**
    * Sets the character encoding scheme (position 09).
    * @param charCodingScheme
    * character representing the character encoding
    */
  def charCodingScheme_(charCodingScheme: Char){
    _charCodingScheme = charCodingScheme
  }
  def charCodingScheme = _charCodingScheme

  /**
    * Sets the indicator count (position 10).
    *
    * @param indicatorCount
    * integer representing the number of indicators present in a
    * data field
    */
  def indicatorCount_(indicatorCount: Int){
    _indicatorCount = indicatorCount
  }
  def indicatorCount = _indicatorCount

  /**
    * Sets the subfield code length (position 11).
    *
    * @param subfieldCodeLength
    * integer representing the subfield code length
    */
  def subfieldCodeLength_(subfieldCodeLength: Int){
    _subfieldCodeLength = subfieldCodeLength
  }
  def subfieldCodeLength = _subfieldCodeLength
   /**
     * Sets the base address of data (positions 12-16).
     *
     * @param baseAddressOfData
     * integer representing the base address of data
     */
  def baseAddressOfData_(baseAddressOfData: Int){
    _baseAddressOfData = baseAddressOfData
  }
  def baseAddressOfData = _baseAddressOfData
  /**
    * Sets implementation defined values (positions 17-19).
    *
    * @param implDefined2
    * character array representing the implementation defined data
    */
  def implDefined2_(implDefined2: Array[Char]){
    _implDefined2 = implDefined2
  }
  def implDefined2 = _implDefined2

  /**
    * Sets the entry map (positions 20-23).
    *
    * @param entryMap
    * character array representing the entry map
    * convert Array[Byte] into a String??
    */
  def entryMap_(entryMap: Array[Char]){
    _entryMap = entryMap
  }

  private def isInteger(str: String) : Boolean = true

  def entryMap = _entryMap
  /**
    * Creates a leader object from a string object.
    *
    * Indicator count and subfield code length are defaulted to 2 if they are
    * not integer values.
    *
    * @param ldr
    * the leader
    */
  def unmarshal(ldr: String){
    val tempLeaderArray:Array[Byte] = ldr.getBytes()
    if (tempLeaderArray.length != Constants.LeaderLength){
      // warning? first Constants.LeaderLength
    }else{
      try {
        val s = ldr.substring(0, 5);
        if (isInteger(s))
          recordLength_(Integer.parseInt(s));
        else
          recordLength_(0);
        recordStatus_(tempLeaderArray(5).toChar);
        typeOfRecord_(tempLeaderArray(6).toChar);
        val s0 = ldr.substring(7,9).toCharArray
        implDefined1_(s0);
        charCodingScheme_(tempLeaderArray(9).toChar);
        val s1 = tempLeaderArray(10).toString;
        if (isInteger(s1))
          indicatorCount_(Integer.parseInt(s1));
        else
           indicatorCount_(2);
        val s2 = tempLeaderArray(11).toString;
        if (isInteger(s2))
          subfieldCodeLength_(Integer.parseInt(s2));
        else
          subfieldCodeLength_(2);
        val s3 = ldr.substring(12, 17).toString;
        if (isInteger(s3))
          baseAddressOfData_(Integer.parseInt(s3));
        else
          baseAddressOfData_(0);
        val s4 = ldr.substring(17,20).toCharArray;
        implDefined2_(s4);
        val s5 = ldr.substring(20,Constants.LeaderLength).toCharArray;
        entryMap_(s5)

        var i=0
        for(i<-0 until Constants.LeaderLength){
          _leaderArray(i) = tempLeaderArray(i).toByte
        }
      } catch {
        case e: NumberFormatException => e.printStackTrace()
          throw e
      }
    }
  }

  /**
    * Creates a string object from this leader object.
    *
    * @return String - the string object from this leader object
    */
  def marshal: String = new String(_leaderArray)
  override def toString: String = new String(_leaderArray)

  def asRaw:Array[Byte] = _leaderArray

}
