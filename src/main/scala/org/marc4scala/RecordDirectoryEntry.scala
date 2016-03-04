/*
 * Copyright (c) 2016. <jason.zou@gmail.com>
 *
 * RecordDirectoryEntry.scala is part of marc4scala.
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
class RecordDirectoryEntry(val tag:String, var lengthOfField:Int, var startingPosition:Int) {
  private var _buff:Array[Byte] = new Array[Byte](Constants.DirectoryEntryLength)

  val _tag: Array[Char] = tag.reverse.padTo(3,"0").reverse.mkString.toCharArray
  val _lenField: Array[Char] = "%04d".format(lengthOfField).toString.toCharArray
  val _startingPosition: Array[Char] = "%05d".format(startingPosition).toString.toCharArray

  // Tag
  for (i <- 0 to 2)  {
    _buff(i) = _tag(i).toByte
  }

  // length of field
  var temp:Array[Char] = new Array[Char](4)
  for(i<- 0 to 3){
    _buff(i+3) = _lenField(i).toByte
    temp(i) = _lenField(i)
  }
  lengthOfField = temp.mkString.toInt

  // starting character position
  var temp1:Array[Char] = new Array[Char](5)
  for(i<- 0 to 4){
    _buff(i+7) = _startingPosition(i).toByte
    temp1(i) = _startingPosition(i)
  }
  startingPosition = temp1.mkString.toInt

  def asRaw:Array[Byte] = _buff

}
