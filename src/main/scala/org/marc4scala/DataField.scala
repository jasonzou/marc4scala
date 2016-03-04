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
import scala.util.matching.Regex
/**
  * Created by jason on 2016-02-20.
  */
class DataField (override val tag:String, val indicator1:Char, val indicator2:Char,
                val subfields:List[SubField]) extends Field(tag) {
  private var _subfields:List[SubField] = subfields

  if (_tag.isControlTag) throw new IllegalStateException("Control Tag in a data field")


  def this(tag:String, indicator1:Char, indicator2:Char) =
    this(tag, indicator1, indicator2, List())

  def addSubField(subfield:SubField){
    _subfields = _subfields ::: List(subfield)
  }

  def _remove(i:SubField, li:List[SubField]):List[SubField] = {
    val (head, _::tail) = li.span(i != _)
    head ::: tail
  }

  def removeSubField(subfield:SubField):Boolean = {
    // subfield remove from _subfields
    val originalLength = _subfields.length
    try {
      _subfields = _remove(subfield, _subfields)
    }catch{
      case e:MatchError =>
    }
    if (originalLength == _subfields.length){
      return false
    }
    return true
  }

  override def toString:String = {
    var retStr = new String(_tag.toString + ' ' + indicator1 + indicator2 )
    for(subfield <- _subfields){
      retStr = retStr + subfield.toString
    }

    return retStr
  }

  def subfields_():List[SubField] = _subfields

  def getSubFieldsByCode(code:Char):List[SubField] = {
    var retSubFields = List(new SubField('0',"head"))
    for(subfield <- _subfields){
      if (subfield.code == code) {
        retSubFields = retSubFields ::: List(subfield)
      }
    }
    return retSubFields.drop(1)
  }

  def find(str:String):Boolean = {
    return true
  }

  def getSubFieldsByStr(str:String):List[SubField] = {
    val pattern = new Regex(str)
    var retSubFields = List(new SubField('0',"head"))
    for(subfield <- _subfields) {
      var match2 = pattern.findFirstIn(subfield.code.toString)
      match2 match {
        case Some(s) => retSubFields = retSubFields ::: List(subfield)
        case None =>
      }
    }
    return retSubFields.drop(1)
  }
}
