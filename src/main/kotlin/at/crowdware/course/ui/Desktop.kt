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

data class Theme(
    var primary: String = "",
    var onPrimary: String = "",
    var primaryContainer: String = "",
    var onPrimaryContainer: String = "",
    var surface: String = "",
    var onSurface: String = "",
    var secondary: String = "",
    var onSecondary: String = "",
    var secondaryContainer: String = "",
    var onSecondaryContainer: String = "",
    var tertiary: String = "",
    var onTertiary: String = "",
    var tertiaryContainer: String = "",
    var onTertiaryContainer: String = "",
    var outline: String = "",
    var outlineVariant: String = "",
    var onErrorContainer: String = "",
    var onError: String = "",
    var inverseSurface: String = "",
    var inversePrimary: String = "",
    var inverseOnSurface: String = "",
    var background: String = "",
    var onBackground: String = "",
    var error: String = "",
    var scrim: String = ""
)

@Composable
fun desktop() {
    val langs = mutableListOf<String>()
    var lang by remember { mutableStateOf("") }
    var page by remember { mutableStateOf("") }
    var theme = Theme()
    val topicList = mutableListOf<AccordionEntry>()
    val inputStream = object {}.javaClass.classLoader.getResourceAsStream("app.sml")
    val content = inputStream?.bufferedReader()?.use { it.readText() }
    if (content != null) {
        val (parsedApp, _) = parseSML(content)
        if (parsedApp != null) {
            for (node in parsedApp.children) {
                if (node.name == "Theme") {
                    theme.primary = getStringValue(node, "primary", "")
                    theme.onPrimary = getStringValue(node, "onPrimary", "")
                    theme.primaryContainer = getStringValue(node, "primaryContainer", "")
                    theme.onPrimaryContainer = getStringValue(node, "onPrimaryContainer", "")
                    theme.surface = getStringValue(node, "surface", "")
                    theme.onSurface = getStringValue(node, "onSurface", "")
                    theme.secondary = getStringValue(node, "secondary", "")
                    theme.onSecondary = getStringValue(node, "onSecondary", "")
                    theme.secondaryContainer = getStringValue(node, "secondaryContainer", "")
                    theme.onSecondaryContainer = getStringValue(node, "onSecondaryContainer", "")
                    theme.tertiary = getStringValue(node, "tertiary", "")
                    theme.onTertiary = getStringValue(node, "onTertiary", "")
                    theme.tertiaryContainer = getStringValue(node, "tertiaryContainer", "")
                    theme.onTertiaryContainer = getStringValue(node, "onTertiaryContainer", "")
                    theme.outline = getStringValue(node, "outline", "")
                    theme.outlineVariant = getStringValue(node, "outlineVariant", "")
                    theme.onErrorContainer = getStringValue(node, "onErrorContainer", "")
                    theme.onError = getStringValue(node, "onError", "")
                    theme.inverseSurface = getStringValue(node, "inverseSurface", "")
                    theme.inversePrimary = getStringValue(node, "inversePrimary", "")
                    theme.inverseOnSurface = getStringValue(node, "inverseOnSurface", "")
                    theme.background = getStringValue(node, "background", "")
                    theme.onBackground = getStringValue(node, "onBackground", "")
                    theme.error = getStringValue(node, "error", "")
                    theme.scrim = getStringValue(node, "scrim", "")
                }
            }
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
            ShowLecture(theme, page, lang)
        }
    }
}