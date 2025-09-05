Page {
    padding: "8"

    Column {
        padding: "8"

        Video { src: "JCTextEingabe.mp4" }
        Spacer { amount: 8}
        Row {
            Button { label: "Beispiel-Anwendung starten" link: "run: at.crowdware.jetpackdesktop.demo.app/contents/MacOS/at.crowdware.jetpackdesktop.demo 2" }
            Spacer { amount: 16 }
            Button { label: "Lektion als abgeschlossen markieren" link: "finished" }
        }
    }
}