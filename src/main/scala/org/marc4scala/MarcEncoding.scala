package org.marc4scala

/**
  * Created by jason on 22/02/16.
  */
object MarcEncoding extends Enumeration{
  final val CharEncoding_MARC8 = Value("MARC8")
  final val CharEncoding_Unicode = Value("MARC21")
  final val charEncoding_Unknown = Value("Unknown")
}
