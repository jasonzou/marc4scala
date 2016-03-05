/*
 * Copyright (c) 2016. <jason.zou@gmail.com>
 *
 * Record.scala is part of marc4scala.
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
  * Created by jason on 3/1/16.
  */
class Record(var leader:Leader, var controlFields:List[ControlField],
              var dataFields:List[DataField]) {

  var _fieldsEntries:List[RecordDirectoryEntry] = List[RecordDirectoryEntry]()

  // sort fields, and put into recordDirectoryEntry
  // add compare method in RecordDirectoryEntry
  var temp:List[ControlField] = controlFields.sortWith(_.fieldTag.getTag > _.fieldTag.getTag)

  var previous:Int = 0
  for(cf <- controlFields){
    var tempRecDirEntry:RecordDirectoryEntry = new RecordDirectoryEntry(cf.tag, cf.data.length,previous)
    previous += cf.data.length
    _fieldsEntries = _fieldsEntries ::: List(tempRecDirEntry)
  }

  var temp1:List[DataField] = dataFields.sortWith(_.fieldTag.getTag > _.fieldTag.getTag)

  for(df <- dataFields){
    var subfieldsLen:Int = 0
    for(subfield <- df.subfields){
      subfieldsLen += 1 + subfield.data.length
    }
    subfieldsLen += 2
    var temp1RecDirEntry:RecordDirectoryEntry = new RecordDirectoryEntry(df.tag, subfieldsLen, previous)
    previous += subfieldsLen
    _fieldsEntries = _fieldsEntries ::: List(temp1RecDirEntry)
  }

  var _recordDirectory: RecordDirectory = new RecordDirectory(_fieldsEntries)
  var _buff:Array[Byte] = new Array[Byte](recordLength)

  Array.copy(leader.asRaw,0, _buff,0,Constants.LeaderLength)
  Array.copy(_recordDirectory.asRaw,0,_buff,Constants.LeaderLength,_recordDirectory.length)


  def this(leader:Leader) = {
    this(leader, List[ControlField](), List[DataField]())
  }

  def baseAddress:Int = Constants.LeaderLength + _recordDirectory.length

  def recordLength:Int = baseAddress + fieldsLength + 1

  def fieldsLength:Int = {
    var i = 0
    for(entry <- _fieldsEntries){
      i += entry.lengthOfField
    }
    return i
  }

  def asRaw:Array[Byte] = _buff

  override def toString:String = {
    var tempStr:String = leader.toString + "\n"
    for(cf<-temp){
      tempStr += cf.toString + "\n"
    }
    for(df<-temp1){
      tempStr += df.toString + "\n"
    }
    return tempStr
  }

}
