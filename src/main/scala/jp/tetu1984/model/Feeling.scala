package jp.tetu1984.model

import _root_.net.liftweb.mapper._

class Feeling extends LongKeyedMapper[Feeling] with IdPK {
  def getSingleton = Feeling

  object date extends MappedDateTime(this) {
    override def defaultValue = (new _root_.java.util.Date)
  }

  object feel extends MappedInt(this) {
    override def defaultValue = 50
  }
}

object Feeling extends Feeling with LongKeyedMetaMapper[Feeling]
