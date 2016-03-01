/*
 * Copyright (c) 2016. <jason.zou@gmail.com>
 *
 * VariableField.scala is part of marc4scala.
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
trait VariableField{
    /**
      * Sets the identifier.
      * The purpose of this identifier is to provide an identifier
      * for persistency.
      *
      * @param id
      * the identifier
      */
    def setId(id: Long)

    /**
      * Returns the identifier.
      *
      * @return Long - the identifier
      */
    def getId: Long

    /**
      * Returns the tag name.
      *
      * @return String - the tag name
      */
    def getTag: String

    /**
      * Sets the tag name.
      *
      * @param tag
      * the tag name
      */
    def setTag(tag: String)

    /**
      * Returns true if the given regular expression matches a subsequence of a
      * data element within the variable field.
      *
      * @param pattern
      * the regular expression
      * @return true if the pattern matches, false othewise
      */
    def find(pattern: String): Boolean
}
