Page {
    padding: "8"

    Column {
        padding: "8"

        Video { src: "Theming.mp4" }
        Spacer { amount: 8}
        Row {
            Column {
                Button { label: "Beispiel ausführen" link: "run: at.crowdware.jetpackdesktop.theming.app/contents/MacOS/at.crowdware.jetpackdesktop.theming"}
                Spacer { amount: 8 }
                Button { label: "Sourcecode" link: "web:https://github.com/CrowdWare/ComposeDesktopTheming/blob/main/composeApp/src/jvmMain/kotlin/at/crowdware/jetpackdesktop/theming/main.kt"}
            }
            Spacer { amount: 16 }
            Button { label: "Lektion als abgeschlossen markieren" link: "finished" }
        }
    }
}