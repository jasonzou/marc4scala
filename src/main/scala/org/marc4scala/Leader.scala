package org.marc4scala

/**
  * Created by jason on 2/22/16.
  */
class Leader {
  private var _recordLength: Int = 0
  private var _recordStatus: Byte = 0
  private var _typeOfRecord: Byte = 0
  private var _implDefined1: Array[Byte] = new Array[Byte](2)
  private var _charCodingScheme: Byte = 0
  private var _indicatorCount: Short = 0
  private var _subfieldCodeLength: Short = 0
  private var _baseAddressOfData: Array[Byte] = new Array[Byte](5)
  private var _implDefined2: Array[Byte] = new Array[Byte](3)
  private var _entryMap: Array[Byte] = new Array[Byte](5)
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
  def recordStatus_(recordStatus: Byte){
    _recordStatus = recordStatus
  }
  def recordStatus = _recordStatus

  /**
    * Sets the type of record (position 06).
    *
    * @param typeOfRecord
    * character representing the type of record
    */
  def typeOfRecord_(typeOfRecord: Byte){
    _typeOfRecord = typeOfRecord
  }
  def typeOfRecord = _typeOfRecord

  /**
    * Sets implementation defined values (position 07-08).
    *
    * @param implDefined1
    * character array representing the implementation defined data
    */
  def implDefined1_(implDefined1: Array[Byte]){
    _implDefined1 = implDefined1
  }
  def implDefined1 = _implDefined1

  /**
    * Sets the character encoding scheme (position 09).
    * @param charCodingScheme
    * character representing the character encoding
    */
  def charCodingScheme_(charCodingScheme: Byte){
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
  def indicatorCount_(indicatorCount: Byte){
    _indicatorCount = indicatorCount
  }
  def indicatorCount = _indicatorCount

  /**
    * Sets the subfield code length (position 11).
    *
    * @param subfieldCodeLength
    * integer representing the subfield code length
    */
  def subfieldCodeLength_(subfieldCodeLength: Byte){
    _subfieldCodeLength = subfieldCodeLength
  }
  def subfieldCodeLength = _subfieldCodeLength
   /**
     * Sets the base address of data (positions 12-16).
     *
     * @param baseAddressOfData
     * integer representing the base address of data
     */
  def baseAddressOfData_(baseAddressOfData: Array[Byte]){
    _baseAddressOfData = baseAddressOfData
  }
  def baseAddressOfData = _baseAddressOfData
  /**
    * Sets implementation defined values (positions 17-19).
    *
    * @param implDefined2
    * character array representing the implementation defined data
    */
  def implDefined2_(implDefined2: Array[Byte]){
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
  def entryMap_(entryMap: Array[Byte]){
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
    if (tempLeaderArray.length != 24){
      // warning? first 24
    }else{
      try {
        val s = ldr.substring(0, 5);
        if (isInteger(s))
          recordLength_(Integer.parseInt(s));
        else
          recordLength_(0);
        recordStatus_(tempLeaderArray(5));
        typeOfRecord_(tempLeaderArray(6));
        val s0 = ldr.substring(7,9)
        implDefined1(s0);
        charCodingScheme(tempLeaderArray(9));
        val s1 = tempLeaderArray(10);
        if (isInteger(s1))
          indicatorCount(Integer.parseInt(s1));
        else
           indicatorCount(2);
        val s2 = tempLeaderArray(11);
        if (isInteger(s2))
          subfieldCodeLength(Integer.parseInt(s2));
        else
          subfieldCodeLength(2);
        val s3 = ldr.substring(12, 17);
        if (isInteger(s3))
          baseAddressOfData(Integer.parseInt(s3));
        else
          baseAddressOfData(0);
        val s4 = ldr.substring(17,20);
        implDefined2(s4);
        val s5 = ldr.substring(20,24);
        entryMap(s5);
      } catch (NumberFormatException e) {
         throw new RuntimeException("Unable to parse leader", e);
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

}
