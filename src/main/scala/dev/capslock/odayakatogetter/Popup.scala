package dev.capslock.odayakatogetter

import scala.scalajs.js
import scala.scalajs.js.annotation.*

import com.raquo.laminar.api.L.{*, given}
import org.scalajs.dom
import com.raquo.laminar.DomApi
import scala.concurrent.Future
import org.scalablytyped.runtime.StringDictionary
import scala.concurrent.duration.FiniteDuration

@main
def PopupMain(): Unit =
  val appContainer = dom.document.querySelector("#app")
  val got =
    typings.chrome.global.chrome.storage.local.get("ot-setting")

  got.`then` { g =>
    val root: RootNode = render(appContainer, Popup(g))
  }
end PopupMain

def Popup(setting: StringDictionary[Any]) = div(
  className := "container-fluid",
  styleAttr := "min-width: 400px;",
  h2("おだやかTogetter"),
  code(setting.get("hide-comment-like").getOrElse(false).asInstanceOf[Boolean]),
  div(
    className := "card",
    i("変更はサイトのリロード後に反映されます"),
    Switch("hide-comment-like", "コメントのいいね数を表示しない"),
    Switch("calm-comment-style", "コメントのスタイルを抑制的にする"),
  ),
  Version,
  addressTag(
    "Developed by ",
    a(href := "https://x.com/windymelt", target := "_blank", "@Windymelt"),
  ),
  TweetShareButton,
)

def Version = div(
  p(
    "Build: ",
    code(BuildInfo.version),
  ),
)

def Switch(elemId: String, labelString: String, isChecked: Boolean = false) =
  div(
    className := "form-check form-switch",
    input(
      className := "form-check-input",
      typ       := "checkbox",
      role      := "switch",
      idAttr    := elemId,
      checked   := isChecked,
    ),
    label(
      className := "form-check-label",
      forId     := elemId,
      labelString,
    ),
  )

def TweetShareButton = Seq(
  foreignHtmlElement(
    DomApi.unsafeParseHtmlString(
      """<a target="_blank" href="https://twitter.com/intent/tweet?button_hashtag=おだやかTogetter&ref_src=twsrc%5Etfw" class="twitter-hashtag-button" data-show-count="false">Tweet #おだやかTogetter</a>""",
    ),
  ),
  foreignHtmlElement(
    DomApi.unsafeParseHtmlString(
      """<script async src="https://platform.twitter.com/widgets.js" charset="utf-8"></script>""",
    ),
  ),
)
