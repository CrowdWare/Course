Page {
    padding: "8"

    Column {
        padding: "8"

        Video { src: "Listen.mp4" }
        Spacer { amount: 8}
        Row {
            Column {
                Button { label: "Beispiel A ausführen" link: "run: at.crowdware.jetpackdesktop.demo.app/contents/MacOS/at.crowdware.jetpackdesktop.demo 9"}
                Spacer { amount: 8 }
                Button { label: "Sourcecode A" link: "web:https://github.com/CrowdWare/ComposeDesktopDemo/blob/main/composeApp/src/jvmMain/kotlin/at/crowdware/jetpackdesktop/demo/Lektion9.kt"}
            }
            Spacer { amount: 8 }
            Column {
                Button { label: "Beispiel B ausführen" link: "run: at.crowdware.jetpackdesktop.demo.app/contents/MacOS/at.crowdware.jetpackdesktop.demo 10"}
                Spacer { amount: 8 }
                Button { label: "Sourcecode B" link: "web:https://github.com/CrowdWare/ComposeDesktopDemo/blob/main/composeApp/src/jvmMain/kotlin/at/crowdware/jetpackdesktop/demo/Lektion10.kt"}
            }
            Spacer { amount: 8 }
            Column {
                Button { label: "Beispiel C ausführen" link: "run: at.crowdware.jetpackdesktop.demo.app/contents/MacOS/at.crowdware.jetpackdesktop.demo 11"}
                Spacer { amount: 8 }
                Button { label: "Sourcecode C" link: "web:https://github.com/CrowdWare/ComposeDesktopDemo/blob/main/composeApp/src/jvmMain/kotlin/at/crowdware/jetpackdesktop/demo/Lektion11.kt"}
            }
            Spacer { amount: 16 }
            Button { label: "Lektion als abgeschlossen markieren" link: "finished" }
        }
    }
}