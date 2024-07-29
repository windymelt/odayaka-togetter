package dev.capslock.odayakatogetter

import scala.scalajs.js
import scala.scalajs.js.annotation._
import org.scalajs.dom
import org.scalajs.dom.MutationObserver
import org.scalajs.dom.MutationObserverInit
import org.scalajs.dom.Node
import org.scalajs.dom.Element
import org.scalajs.dom.HTMLElement

@JSExportTopLevel("Content", "content")
object Content {
  if (dom.document.location.hostname == "togetter.com") {
    contentMainLogic()
  }

  def contentMainLogic() = {
    val ob = MutationObserver((list, observer) => {
      dom.document.querySelectorAll(Togetter.Comment.COMMENT_CLASS).foreach {
        elem =>
          try {
            val id =
              elem.querySelector(Togetter.Comment.USERNAME_CLASS).textContent
            val commentNGUserIds = Set[String]() // TODO: load from LocalStorage
            if commentNGUserIds.contains(id) then elem.remove()

            // TODO: run only set in config
            // desaturate comments
            elem
              .querySelector("main")
              .querySelector("span")
              .setAttribute(
                "style",
                "color: black; font-size: 1rem; font-weight: normal",
              )

            // TODO: run only set in config
            // remove comment icon
            elem.querySelector(Togetter.Comment.ICON_CLASS).remove()
          } catch {
            case e: Exception =>
          }
      }

      // TODO: run only set in config
      dom.document
        .querySelectorAll(Togetter.Comment.BUMP_COUNTER_CLASS)
        .foreach { elem =>
          elem.remove()
        }
    })
    ob.observe(
      dom.document.body,
      MutationObserverInit(childList = true, subtree = true),
    )
  }
}
