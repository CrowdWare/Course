Page {
    padding: "8"

    Column {
        padding: "8"

        Video { src: "https://crowdware.info/wp-content/uploads/videos/composedesktop/Todo.mp4" }
        Spacer { amount: 8}
        Row {
            Column {
                Button { label: "Beispiel ausführen" link: "run: at.crowdware.jetpackdesktop.todo.app/contents/MacOS/at.crowdware.jetpackdesktop.todo"}
                Spacer { amount: 8 }
                Button { label: "Sourcecode" link: "web:https://github.com/CrowdWare/ComposeDesktopTodo/blob/main/composeApp/src/jvmMain/kotlin/at/crowdware/jetpackdesktop/todo/main.kt"}
            }
            Spacer { amount: 16 }
            Button { label: "Lektion als abgeschlossen markieren" link: "finished" }
        }
    }
}