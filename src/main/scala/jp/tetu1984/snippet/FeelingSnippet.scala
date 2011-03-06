package jp.tetu1984.snippet

import _root_.scala.xml.NodeSeq
import _root_.net.liftweb.util.Helpers
import _root_.net.liftweb.util.Helpers._

import _root_.jp.tetu1984.model.Feeling

object FeelingSnippet {
  def show(xhtml : NodeSeq) : NodeSeq = {
    <xml:Group>{
      Feeling.findAll.flatMap{ feeling => bind("feeling", xhtml,
        "id" -> feeling.id,
        "date" -> feeling.date,
        "feel" -> feeling.feel
      )}
    }</xml:Group>
  }
}

