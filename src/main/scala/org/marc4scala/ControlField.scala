/*
 * Copyright (c) 2016. <jason.zou@gmail.com>
 *
 * ControlField.scala is part of marc4scala.
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

class ControlField(override val tag:String, val data:String) extends Field(tag){
  //val _tag = fieldTag
  if (_tag.isDataTag) throw new IllegalStateException("Data Tag in a control field")

  def isControlField:Boolean = _tag.isControlTag

  override def toString:String = _tag.toString + " " + data

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
}
