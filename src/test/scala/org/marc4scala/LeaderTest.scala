/*
 * Copyright (c) 2016. <jason.zou@gmail.com>
 *
 * LeaderTest.scala is part of marc4scala.
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

import org.scalatest.FlatSpec

class LeaderTest extends FlatSpec{
  val leader = new Leader
  
  behavior of "Leader"
  
  "Leader" must "unmarshal correctly" in {
     leader.unmarshal("00714cam a2200205 a 4500")
     assert("00714cam a2200205 a 4500" == leader.toString)
     info("unmarshal properly")

     assert(714 == leader.recordLength)
     info("record length is correct")

     assert('c' == leader.recordStatus)
     info("record status is correct")
  }

  it must "marshal correctly" is (pending)
}
