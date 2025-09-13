Page {
    padding: "8"

    Column {
        padding: "8"

        Video { src: "https://crowdware.info/wp-content/uploads/videos/composedesktop/Treeview.mp4" }
        Spacer { amount: 8}
        Row {
            Column {
                Button { label: "Beispiel ausführen" link: "run: at.crowdware.jetpackdesktop.demo.app/contents/MacOS/at.crowdware.jetpackdesktop.demo 13"}
                Spacer { amount: 8 }
                Button { label: "Sourcecode" link: "web:https://github.com/CrowdWare/ComposeDesktopDemo/blob/main/composeApp/src/jvmMain/kotlin/at/crowdware/jetpackdesktop/demo/Lektion13.kt"}
            }
            Spacer { amount: 16 }
            Button { label: "Lektion als abgeschlossen markieren" link: "finished" }
        }
    }
}