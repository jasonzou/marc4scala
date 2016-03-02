/*
 * Copyright (c) 2016. <jason.zou@gmail.com>
 *
 * Tag.scala is part of marc4scala.
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
  * Created by jason on 3/1/16.
  */
class Tag {
  /**
   * This class is for MARC tags.
   */
  private var _buff:Array[Char] = Array('0', '0', '0')

  def this(String str) = {
    val pattern = new Regex("^[0-9]{3}$")
    val match1 = pattern.findFirstIn(str)
    match1 match{
      case Some(s): _buff = str.toArray
      case None =>
    }
  }

   override toString {
     var temp:String = new String(_buff)
     return temp
   }

   def isControlTag:Boolean = {
     if (_buff(0) == '0' && _buff(1) == '0') {
       return true;
     } else {
       return false;
     }
   }

   def isDataTag: Boolean = !isControlTag
}
