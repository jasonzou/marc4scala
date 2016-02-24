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
  private var _implDefined2: Array[Byte] = new Array[Byte](2)
  private var _entryMap: Array[Byte] = new Array[Byte](Constants.LeaderLength)
  private var _leaderArray: Array[Byte] = new Array[Byte](Constants.LeaderLength)

  /**
    * Sets the logical record length (positions 00-04).
    *
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
    */
  def entryMap_(entryMap: Array[Byte]){
    _entryMap = entryMap
  }
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
    Array[Byte] tempLeaderArray = ldr.getBytes()
    if (tempLeaderArray.length !== 24){
      // warning? first 24
    }else{
      try {
        s = ldr.substring(0, 5);
        if (isInteger(s))
          recordLength_(Integer.parseInt(s));
        else
          recordLength_(0);
        recordStatus_(tempLeaderArray(5));
        typeOfRecord_(tempLeaderArray(6));
        implDefined1(ldr.substring(7, 9).toCharArray());
        charCodingScheme(ldr.charAt(9));
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
