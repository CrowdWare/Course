package at.crowdware.course.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import at.crowdware.course.util.getStringValue
import at.crowdware.course.util.parseSML

@Composable
fun desktop() {
    val langs = mutableListOf<String>()
    val topicList = mutableListOf<AccordionEntry>()
    val inputStream = object {}.javaClass.classLoader.getResourceAsStream("app.sml")
    val content = inputStream?.bufferedReader()?.use { it.readText() }
    if (content != null) {
        val (parsedApp, _) = parseSML(content)
        if (parsedApp != null) {

            for(node in parsedApp.children) {
                if (node.name == "Course") {
                    langs.add(getStringValue(node, "lang", ""))

                    for (topic in node.children) {
                        if (topic.name == "Topic") {
                            val entries = mutableListOf<String>()
                            for (lection in topic.children) {
                                entries.add(getStringValue(lection, "label", ""))
                            }
                            topicList.add(AccordionEntry(getStringValue(topic, "label", ""), entries))
                        }
                    }
                }
            }
        }
    }

    Row(modifier = Modifier.height(35.dp).padding(8.dp)) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Text("Topics", color = MaterialTheme.colors.onPrimary)
        }
    }
    Row (modifier = Modifier.background(MaterialTheme.colors.surface).fillMaxHeight().padding(8.dp)) {
        Column(modifier = Modifier.width(300.dp)) {

            AccordionList(topicList)
        }
        Column(modifier = Modifier.fillMaxWidth()) {
            Text("Right", color = MaterialTheme.colors.onSurface)
        }
    }
}