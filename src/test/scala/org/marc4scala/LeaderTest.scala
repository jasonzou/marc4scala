package org.marc4scala

import org.marc4scala.Leader;

Class LeaderTest extends FlatSpec{
 Def testUnmarshal() {
   Leader leader = factory.newLeader();
   leader.unmarshal("00714cam a2200205 a 4500")
   assertEquals("00714cam a2200205 a 4500"
 Leader leader = factory.newLeader();
   leader.unmarshal("00714cam a2100205 a 4500");
    assertEquals(1, leader.)
 Def testMarshal() {
                                                                                                                  
   Leader leader = factory.newLeader("00714cam a2200205 a 4500");
                                                                                                                          assertEquals("00714cam a2200205 a 4500", leader.marshal());
                                                                                                                              }

}
