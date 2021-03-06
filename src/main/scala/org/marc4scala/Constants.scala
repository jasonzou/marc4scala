/*
 * Copyright (c) 2016. <jason.zou@gmail.com>
 *
 * Constants.scala is part of marc4scala.
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

import scala.annotation._
/**
  * Created by jason on 2016-02-19.
  */
object Constants {
  /** RECORD TERMINATOR */
  val RecordTerminator: Byte = 0x1D

  /** FIELD TERMINATOR */
  val FieldTerminator: Byte = 0x1E

  /** SUBFIELD DELIMITER */
  val SubfieldDelimiter: Byte = 0x1F

  /** BLANK */
  val BlankSpace: Byte = 0x20

  /** NS URI */
  val MARCXML_NS_URI: String = "http://www.loc.gov/MARC21/slim"

  /** MARC-8 ANSEL ENCODING **/
  val MARC_8_ENCODING: String = "MARC8"

  /** ISO5426 ENCODING **/
  val ISO5426_ENCODING: String = "ISO5426"

  /** ISO6937 ENCODING **/
  val ISO6937_ENCODING: String = "ISO6937"

  val DirectoryEntryLength: Int = 12
  val LeaderLength: Int = 24
}
