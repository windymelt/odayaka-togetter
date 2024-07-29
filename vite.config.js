import { defineConfig } from "vite";
import scalaJSPlugin from "@scala-js/vite-plugin-scalajs";
import { crx, defineManifest } from "@crxjs/vite-plugin";

const manifest = defineManifest({
    manifest_version: 3,
    name: "おだやかTogetter",
    version: "0.0.1",
    permissions: ["activeTab", "scripting", "storage"],
    action: {
        default_popup: "popup.html",
    },
    content_scripts: [
        {
            js: ["content.js"],
            matches: [
                "https://togetter.com/*"
            ]
        }
    ],
});

export default defineConfig({
    plugins: [scalaJSPlugin(), crx({ manifest })],
});