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
  private var _fieldLength:Int = 2
  for(sf <- _subfields){
    _fieldLength += sf.length
  }
  private var _buff:Array[Byte] = new Array[Byte](_fieldLength)

  private var i:Int = 2
  _buff(0) = indicator1.toByte
  _buff(1) = indicator2.toByte
  for(sf <- _subfields){
    Array.copy(sf.asRaw,0,_buff,i,sf.length)
    i = i + sf.length
  }

  if (_tag.isControlTag) throw new IllegalStateException("Control Tag in a data field")


  def this(tag:String, indicator1:Char, indicator2:Char) =
    this(tag, indicator1, indicator2, List())

  def asRaw:Array[Byte]={
    var temp:Array[Byte] = new Array[Byte](5+_fieldLength)
    var i:Int = 0
    for(c<-_tag.toString.toCharArray){
      temp(i) = c.toByte
      i = i + 1
    }
    temp(3) = indicator1.toByte
    temp(4) = indicator2.toByte
    Array.copy(_buff,0, temp, 5, _fieldLength)
    return temp
  }

  private def _update: Unit ={
    _fieldLength = 2
    for(sf <- _subfields){
      _fieldLength += sf.length
    }
    _buff = new Array[Byte](_fieldLength)

    var i:Int = 0
    for(sf <- _subfields){
      Array.copy(sf.asRaw, 0, _buff, i, sf.length)
      i = i + sf.length
    }
  }

  def addSubField(subfield:SubField){
    _subfields = _subfields ::: List(subfield)
    _update
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
    _update
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
