Page {
    padding: "8"

    Column {
        padding: "8"

        Video { src: "https://crowdware.info/wp-content/uploads/videos/composedesktop/JCLayout.mp4" }
        Spacer { amount: 8}
        Row {
            Column {
                Button { label: "Beispiel A ausführen" link: "run: at.crowdware.jetpackdesktop.demo.app/contents/MacOS/at.crowdware.jetpackdesktop.demo 3"}
                Spacer { amount: 8 }
                Button { label: "Sourcecode A" link: "web:https://github.com/CrowdWare/ComposeDesktopDemo/blob/main/composeApp/src/jvmMain/kotlin/at/crowdware/jetpackdesktop/demo/Lektion3.kt"}
            }
            Spacer { amount: 8 }
            Column {
                Button { label: "Beispiel B ausführen" link: "run: at.crowdware.jetpackdesktop.demo.app/contents/MacOS/at.crowdware.jetpackdesktop.demo 4"}
                Spacer { amount: 8 }
                Button { label: "Sourcecode B" link: "web:https://github.com/CrowdWare/ComposeDesktopDemo/blob/main/composeApp/src/jvmMain/kotlin/at/crowdware/jetpackdesktop/demo/Lektion4.kt"}
            }
            Spacer { amount: 16 }
            Button { label: "Lektion als abgeschlossen markieren" link: "finished" }
        }
    }
}