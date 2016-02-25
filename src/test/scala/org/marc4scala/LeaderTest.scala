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
