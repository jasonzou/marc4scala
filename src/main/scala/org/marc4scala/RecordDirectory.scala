/*
 * Copyright (c) 2016. <jason.zou@gmail.com>
 *
 * RecordDirectory.scala is part of marc4scala.
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
  * Created by jason on 3/4/16.
  */
class RecordDirectory(val recordEntries:List[RecordDirectoryEntry]) {
  private var _recordDirectory:List[RecordDirectoryEntry] = recordEntries
  private var _entries:Int = _recordDirectory.length
  private var _recordLength:Int = 0 //TODO
  private var _baseAddress:Int = 0 //TODO
  private var _buff:Array[Byte] = new Array[Byte](Constants.DirectoryEntryLength * _recordDirectory.length + 1)

  var i = 0
  for(entry <- _recordDirectory){
    Array.copy(entry, 0, _buff, i*_recordDirectory.length, _recordDirectory.length)
    i += 1
  }
  _buff(_buff.length - 1) = Constants.FieldTerminator

  def numOfEntries = _entries
  def entries:List[RecordDirectoryEntry] = _recordDirectory
  def asRaw:Array[Byte] = _buff

  def baseAddress = _baseAddress
  def recordLength = _recordLength
  def length = Constants.DirectoryEntryLength * _recordDirectory.length + 1
  def fieldsLength:Int = {
    var i = 0
    for(entry <- _recordDirectory){
      i += entry.lengthOfField
    }
    return i
  }

}
