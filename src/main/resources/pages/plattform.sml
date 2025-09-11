Page {
    padding: "8"

    Column {
        padding: "8"

        Video { src: "Plattform.mp4" }
        Spacer { amount: 8}
        Row {
            Button { label: "Sourcecode A TODO" link: "web:https://github.com/CrowdWare/ComposeDesktopDemo/blob/main/composeApp/src/jvmMain/kotlin/at/crowdware/jetpackdesktop/demo/Lektion3.kt"}
            Spacer { amount: 8 }
            Button { label: "Sourcecode B TODO" link: "web:https://github.com/CrowdWare/ComposeDesktopDemo/blob/main/composeApp/src/jvmMain/kotlin/at/crowdware/jetpackdesktop/demo/Lektion4.kt"}
            Spacer { amount: 16 }
            Button { label: "Lektion als abgeschlossen markieren" link: "finished" }
        }
    }
}