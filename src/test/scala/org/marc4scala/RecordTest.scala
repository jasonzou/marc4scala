/*
 * Copyright (c) 2016. <jason.zou@gmail.com>
 *
 * RecordTest.scala is part of marc4scala.
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
import java.io._

/**
  * Created by jason on 3/1/16.
  */
class RecordTest extends FlatSpec {

  it must "create a record properly" in {
    val leader: Leader = new Leader()
    leader.unmarshal("00000cam a2200000 a 4500")

    val cf: ControlField = new ControlField("001", "12883376")

    val df: DataField = new DataField("245", '1', '0')
    df.addSubField(new SubField('a', "Summerland /"))
    df.addSubField(new SubField('c', "Michael Chabon."))

    val record: Record = new Record(leader, List(cf), List(df))
    info(record.toString)

    var out = None: Option[FileOutputStream]

    try {

      out = Some(new FileOutputStream("/Users/jason/Test.mrc"))
      for(c<- record.asRaw) {
        out.get.write(c)
      }
    } catch {
      case e: IOException => e.printStackTrace
    } finally {
      info("entered finally ...")
      if (out.isDefined) out.get.close
    }

  }

}
