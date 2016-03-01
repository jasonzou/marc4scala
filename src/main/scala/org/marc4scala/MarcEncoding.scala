/*
 * Copyright (c) 2016. <jason.zou@gmail.com>
 *
 * MarcEncoding.scala is part of marc4scala.
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
  * Created by jason on 22/02/16.
  */
object MarcEncoding extends Enumeration{
  final val CharEncoding_MARC8 = Value("MARC8")
  final val CharEncoding_Unicode = Value("MARC21")
  final val charEncoding_Unknown = Value("Unknown")
}
