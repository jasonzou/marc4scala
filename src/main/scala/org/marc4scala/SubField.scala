/*
 * Copyright (c) 2016. <jason.zou@gmail.com>
 *
 * SubField.scala is part of marc4scala.
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
import scala.util.matching.Regex
/**
  * Created by jason on 2016-02-20.
  */
class SubField(val code:Char,val data:String) {
  override def toString = new String("$"+code+data)
  private var _buff:Array[Byte] = new Array[Byte](data.length + 2)

  _buff(0) = Constants.SubfieldDelimiter
  _buff(1) = code.toByte
  var i:Int = 2
  for(c<-data){
    _buff(i) = c.toByte
    i += 1
  }
  def isCode(code1:Char):Boolean = (code == code1)

  def find(str:String):Boolean = {
    var pattern = new Regex(str)
    val match2 = pattern.findFirstIn(data)
    var ret = false
    match2 match{
      case Some(s) => ret = true
      case None =>
    }
    return ret
  }

  def length:Int = 2+data.length
  def asRaw:Array[Byte] = _buff
}
