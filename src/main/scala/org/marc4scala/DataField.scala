/*
 * Copyright (c) 2016. <jason.zou@gmail.com>
 *
 * DataField.scala is part of marc4scala.
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
  * Created by jason on 2016-02-20.
  */
class DataField (tag:String, val indicator1:Char, val indicator2:Char, 
                val subfields:List[SubField]) {
  private var _subfields:List[SubField] = subfields
  val _tag = new Tag(tag)
  if (_tag.isControlTag) throw new IllegalStateException("Control Tag in a data field")

  def this(tag:String, indicator1:Char, indicator2:Char) =
    this(tag, indicator1, indicator2, List())

  def addSubField(subfield:SubField){
    _subfields = _subfields ::: List(subfield)
  }

  override def toString:String = {
    var ret = new String(_tag.toString + indicator1 + indicator2 )
    return ret
  }
}
