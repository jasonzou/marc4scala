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
  var _recordType: String = new String("recordType")

  var _fieldsEntries:List[RecordDirectoryEntry] = List[RecordDirectoryEntry]()
  // TODO sort fields, and put into recordDirectoryEntry
  // add compare method in RecordDirectoryEntry

  var _recordDirectory: RecordDirectory = new RecordDirectory(_fieldsEntries)

  def this(leader:Leader) = {
    this(leader, List[ControlField](), List[DataField]())
  }
  def recordType = _recordType
  def recordType_(recordType:String) {
    _recordType = recordType
  }
}
