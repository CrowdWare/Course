import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.darkColors
import androidx.compose.runtime.*

@Composable
fun desktop() {
    var text by remember { mutableStateOf("Hello, World!") }
    val inputStream = object {}.javaClass.classLoader.getResourceAsStream("app.sml")
    val content = inputStream?.bufferedReader()?.use { it.readText() }
    if (content != null ) {
        val (parsedApp, _) = parseSML(content)
        if (parsedApp != null) {
            val appName = getStringValue(parsedApp, "name", "")
            println("App: $appName")
        }
    }

    MaterialTheme(colors = darkColors()) {
        Button(onClick = {
            text = "Hello, Desktop!"
        }) {
            Text(text)
        }
    }
}