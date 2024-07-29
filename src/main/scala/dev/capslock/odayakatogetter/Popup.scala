package dev.capslock.odayakatogetter

import scala.scalajs.js
import scala.scalajs.js.annotation.*

import com.raquo.laminar.api.L.{*, given}
import org.scalajs.dom

// // import javascriptLogo from "/javascript.svg"
// @js.native @JSImport("/javascript.svg", JSImport.Default)
// val javascriptLogo: String = js.native

@main
def PopupMain(): Unit =
  val appContainer   = dom.document.querySelector("#app")
  val root: RootNode = render(appContainer, Popup)
end PopupMain

def Popup = div(
  h1("おだやかTogetter"),
  div(
    className := "card",
    button("Purge comment NG", typ := "button"),
  ),
  Version,
  addressTag(
    "Developed by ",
    a(href := "https://x.com/windymelt", target := "_blank", "@Windymelt"),
  ),
)

def Version = div(
  p(
    "Build: ",
    code(BuildInfo.version),
  ),
)
