package bootstrap.liftweb

import _root_.net.liftweb.mapper.Schemifier
import _root_.net.liftweb.common._
import _root_.net.liftweb.util._
import _root_.net.liftweb.http._
import _root_.net.liftweb.sitemap._
import _root_.net.liftweb.sitemap.Loc._
import Helpers._
import _root_.net.liftweb.mapper.{DB, ConnectionManager, Schemifier, DefaultConnectionIdentifier, StandardDBVendor}

import _root_.jp.tetu1984.model.Feeling

/**
  * A class that's instantiated early and run.  It allows the application
  * to modify lift's environment
  */
class Boot {
  def models = List(Feeling)

  def boot {
    if (!DB.jndiJdbcConnAvailable_?) {
      val vendor =
        new StandardDBVendor(Props.get("db.driver") openOr "org.h2.Driver",
          Props.get("db.url") openOr "jdbc:h2:lift_proto.db;AUTO_SERVER=TRUE",
          Props.get("db.user"),
          Props.get("db.password"))

      LiftRules.unloadHooks.append(vendor.closeAllConnections_! _)

      DB.defineConnectionManager(DefaultConnectionIdentifier, vendor)
    }

    // where to search snippet
    LiftRules.addToPackages("jp.tetu1984")

    // Build SiteMap
    val entries = Menu(Loc("Home", List("index"), "Home")) :: 
                  Menu(Loc("Show", List("show"), "Show")) :: 
                  Menu(Loc("Create", List("create"), "Create")) :: Nil
    LiftRules.setSiteMap(SiteMap(entries:_*))

    Schemifier.schemify(true, Schemifier.infoF _, models :_*)
  }
}

