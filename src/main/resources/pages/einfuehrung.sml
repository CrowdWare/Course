Page {
    padding: "8"

    Column {
        padding: "8"

        Video { src: "https://crowdware.info/wp-content/uploads/videos/composedesktop/JCEinführung.mp4" }
        Spacer { amount: 8}
        Row {
            Button { label: "Beispiel-Anwendung starten" link: "run: at.crowdware.jetpackdesktop.demo.app/contents/MacOS/at.crowdware.jetpackdesktop.demo 1" }
            Spacer { amount: 8 }
            Button { label: "Sourcecode" link: "web:https://github.com/CrowdWare/ComposeDesktopDemo/blob/main/composeApp/src/jvmMain/kotlin/at/crowdware/jetpackdesktop/demo/Lektion1.kt" }
            Spacer { amount: 16 }
            Button { label: "Lektion als abgeschlossen markieren" link: "finished" }
        }
    }
}