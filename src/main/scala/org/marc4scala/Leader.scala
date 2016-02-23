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

        /**
          * Sets implementation defined values (position 07-08).
          *
          * @param implDefined1
          * character array representing the implementation defined data
          */
  def setImplDefined1(implDefined1: Array[Char])

        /**
          * Sets the character encoding scheme (position 09).
          *
          * @param charCodingScheme
          * character representing the character encoding
          */
  def setCharCodingScheme(charCodingScheme: Char)

        /**
          * Sets the indicator count (position 10).
          *
          * @param indicatorCount
          * integer representing the number of indicators present in a
          * data field
          */
  def setIndicatorCount(indicatorCount: Int)

        /**
          * Sets the subfield code length (position 11).
          *
          * @param subfieldCodeLength
          * integer representing the subfield code length
          */
  def setSubfieldCodeLength(subfieldCodeLength: Int)

        /**
          * Sets the base address of data (positions 12-16).
          *
          * @param baseAddressOfData
          * integer representing the base address of data
          */
  def setBaseAddressOfData(baseAddressOfData: Int)

        /**
          * Sets implementation defined values (positions 17-19).
          *
          * @param implDefined2
          * character array representing the implementation defined data
          */
  def setImplDefined2(implDefined2: Array[Char])

        /**
          * Sets the entry map (positions 20-23).
          *
          * @param entryMap
          * character array representing the entry map
          */
  def setEntryMap(entryMap: Array[Char])

        /**
          * Returns the logical record length (positions 00-04).
          *
          * @return <code>int</code>- the record length
          */
  def getRecordLength: Int

        /**
          * Returns the record status (positions 05).
          *
          * @return <code>char</code>- the record status
          */
  def getRecordStatus: Char

        /**
          * Returns the record type (position 06).
          *
          * @return <code>char</code>- the record type
          */
  def getTypeOfRecord: Char

        /**
          * Returns implementation defined values (positions 07-08).
          *
          * @return <code>char[]</code>- implementation defined values
          */
  def getImplDefined1: Array[Char]

        /**
          * Returns the character coding scheme (position 09).
          *
          * @return <code>char</code>- the character coding scheme
          */
  def getCharCodingScheme: Char

        /**
          * Returns the indicator count (positions 10).
          *
          * @return <code>int</code>- the indicator count
          */
  def getIndicatorCount: Int

        /**
          * Returns the subfield code length (position 11).
          *
          * @return <code>int</code>- the subfield code length
          */
  def getSubfieldCodeLength: Int

        /**
          * Returns the base address of data (positions 12-16).
          *
          * @return <code>int</code>- the base address of data
          */
  def getBaseAddressOfData: Int

        /**
          * Returns implementation defined values (positions 17-19).
          *
          * @return <code>char[]</code>- implementation defined values
          */
  def getImplDefined2: Array[Char]
        /**
          * Returns the entry map (positions 20-23).
          *
          * @return <code>char[]</code>- the entry map
          */
  def getEntryMap: Array[Char]

        /**
          * Creates a leader object from a string object.
          *
          * Indicator count and subfield code length are defaulted to 2 if they are
          * not integer values.
          *
          * @param ldr
          * the leader
          */
  def unmarshal(ldr: String)

        /**
          * Creates a string object from this leader object.
          *
          * @return String - the string object from this leader object
          */
  def marshal: String
  """

  int _recordLength;
  char _recordStatus;
  char _recordType;
  char _charCodingScheme;
  char _indicatorCount;
  char _subfieldCodeLength;
  int _baseAddressOfData;
  char[] _buff = new char[MARCChar.LEADER_LEN];
  string _warn = "";

  public MARCLeader(int recLen, int baseAddress)
  {
    _recordLength = recLen;
    _baseAddressOfData = baseAddress;

    for (int i = 0; i < MARCChar.LEADER_LEN; i++)
    {
      _buff[i] = MARCChar.SPACE;
    }

    String temp = String.Format("{0:00000}", recLen);
    temp.CopyTo(0, _buff, 0, 5);

    temp = String.Format("{0:00000}", baseAddress);
    temp.CopyTo(0, _buff, 12, 5);

    _buff[10] = '2';
    _buff[11] = '2';
    _buff[20] = '4';
    _buff[21] = '5';
    _buff[22] = '0';
    _buff[23] = '0';

  }

  public MARCLeader(char[] leader)
  {

    char[] buff = new char[5];

    Array.Copy(leader, 0, buff, 0, 5);
    _recordLength = getNum(buff);

    Array.Copy(leader, 12, buff, 0, 5);
    _baseAddressOfData = getNum(buff);
    Array.Copy(leader, _buff, MARCChar.LEADER_LEN);

    if (_baseAddressOfData >= _recordLength)
    {
      _warn = "Invalid Leader";
    }

  }

  public void update(int length, int baseAddress)
  {
    setRecLength(length);
    setBaseAddressOfData(baseAddress);
  }

  private int getNum(char[] charArray)
  {
    int len = charArray.Length;
    int retNum = 0; ;

    for (int i = 0; i < len; i++)
    {
      int temp = (int)(charArray[i] - '0') * (int)Math.Pow(10, (len - i - 1));
      retNum += temp;
    }
    return retNum;
  }

  public char[] asRaw()
  {
    return _buff;
  }

  public int getRecLength()
  {
    return _recordLength;
  }

  private void setRecLength(int length)
  {
    _recordLength = length;

    String temp = String.Format("{0:00000}", length);
    temp.CopyTo(0, _buff, 0, 5);
  }

  public int getBaseAddressOfData()
  {
    return _baseAddressOfData;
  }

  private void setBaseAddressOfData(int baseAddress)
  {
    _baseAddressOfData = baseAddress;

    String temp = String.Format("{0:00000}", baseAddress);
    temp.CopyTo(0, _buff, 12, 5);
  }
  """
}
