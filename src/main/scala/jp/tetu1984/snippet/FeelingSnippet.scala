package jp.tetu1984.snippet

import _root_.scala.xml.NodeSeq
import _root_.net.liftweb.http.S
import _root_.net.liftweb.http.SHtml._
import _root_.net.liftweb.util.Helpers
import _root_.net.liftweb.util.Helpers._

import _root_.jp.tetu1984.model.Feeling

object FeelingSnippet {
  def post(xhtml : NodeSeq) : NodeSeq = {
    val feeling = Feeling.create

    val date = new _root_.java.util.Date

    def addFeeling : Unit = {
      feeling.save
      S.notice("create new Feeling")
    }

    bind("feeling", xhtml,
      "feel" -> feeling.feel.toForm,
      "submit" -> submit("submit", () => addFeeling)
    )
  }
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

