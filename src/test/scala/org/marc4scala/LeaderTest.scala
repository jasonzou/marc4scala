package org.marc4scala

import org.marc4scala.Leader
import org.scalatest.FlatSpec

class LeaderTest extends FlatSpec{
  val leader = new Leader
  
  Behavior of "Leader"
  
  "Leader" must "unmarshal correctly" in {
     leader.unmarshal("00714cam a2200205 a 4500")
     assertEquals("00714cam a2200205 a 4500", leader.toString)
  }

  it must "marshal correctly" is (pending)
}
