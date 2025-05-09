/*
 * Copyright (C) 2025 CrowdWare
 *
 * This file is part of Course.
 *
 *  Course is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  Course is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with Course.  If not, see <http://www.gnu.org/licenses/>.
 */

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
    var lang by remember { mutableStateOf("") }
    var page by remember { mutableStateOf("") }
    val topicList = mutableListOf<AccordionEntry>()
    val inputStream = object {}.javaClass.classLoader.getResourceAsStream("app.sml")
    val content = inputStream?.bufferedReader()?.use { it.readText() }
    if (content != null) {
        val (parsedApp, _) = parseSML(content)
        if (parsedApp != null) {
            for(node in parsedApp.children) {
                if (node.name == "Course") {
                    lang = getStringValue(node, "lang", "")
                    langs.add(lang)

                    for (topic in node.children) {
                        if (topic.name == "Topic") {
                            val entries = mutableListOf<Lecture>()
                            for (lection in topic.children) {
                                entries.add(Lecture(label = getStringValue(lection, "label", ""), page = getStringValue(lection, "src", "")))
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

            AccordionList(items = topicList) { p ->
                page = p
            }
        }
        Column(modifier = Modifier.fillMaxWidth()) {
            ShowLecture(page, lang)
        }
    }
}