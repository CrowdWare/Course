Page {
    padding: "8"

    Column {
        padding: "8"

        Video { src: "https://crowdware.info/wp-content/uploads/videos/composedesktop/Dialoge.mp4" }
        Spacer { amount: 8}
        Row {
            Column {
                Button { label: "Beispiel A ausführen" link: "run: at.crowdware.jetpackdesktop.demo.app/contents/MacOS/at.crowdware.jetpackdesktop.demo 5"}
                Spacer { amount: 8 }
                Button { label: "Sourcecode A" link: "web:https://github.com/CrowdWare/ComposeDesktopDemo/blob/main/composeApp/src/jvmMain/kotlin/at/crowdware/jetpackdesktop/demo/Lektion5.kt"}
            }
            Spacer { amount: 8 }
            Column {
                Button { label: "Beispiel B ausführen" link: "run: at.crowdware.jetpackdesktop.demo.app/contents/MacOS/at.crowdware.jetpackdesktop.demo 6"}
                Spacer { amount: 8 }
                Button { label: "Sourcecode B" link: "web:https://github.com/CrowdWare/ComposeDesktopDemo/blob/main/composeApp/src/jvmMain/kotlin/at/crowdware/jetpackdesktop/demo/Lektion6.kt"}
            }
            Spacer { amount: 8 }
            Column {
                Button { label: "Beispiel C ausführen" link: "run: at.crowdware.jetpackdesktop.demo.app/contents/MacOS/at.crowdware.jetpackdesktop.demo 7"}
                Spacer { amount: 8 }
                Button { label: "Sourcecode C" link: "web:https://github.com/CrowdWare/ComposeDesktopDemo/blob/main/composeApp/src/jvmMain/kotlin/at/crowdware/jetpackdesktop/demo/Lektion7.kt"}
            }
            Spacer { amount: 8 }
            Column {
                Button { label: "Beispiel D ausführen" link: "run: at.crowdware.jetpackdesktop.demo.app/contents/MacOS/at.crowdware.jetpackdesktop.demo 8"}
                Spacer { amount: 8 }
                Button { label: "Sourcecode D" link: "web:https://github.com/CrowdWare/ComposeDesktopDemo/blob/main/composeApp/src/jvmMain/kotlin/at/crowdware/jetpackdesktop/demo/Lektion8.kt"}
            }
            Spacer { amount: 16 }
            Button { label: "Lektion als abgeschlossen markieren" link: "finished" }
        }
    }
}