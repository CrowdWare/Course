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

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.updateTransition
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import at.crowdware.course.util.getBooleanValue
import at.crowdware.course.util.getStringValue
import at.crowdware.course.util.parseSML
import java.io.File

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
fun desktop(appTitle: MutableState<String>, demoDir: File) {
    val langs = mutableListOf<String>()
    var lang by remember { mutableStateOf("") }
    var lecture by remember { mutableStateOf(Lecture("", "home.sml", "")) }
    var theme by remember { mutableStateOf(Theme()) }
    val topicList = remember { mutableStateListOf<AccordionEntry>() }
    val bundledSml by remember {
        mutableStateOf(
            object {}.javaClass.classLoader
                .getResourceAsStream("app.sml")
                ?.bufferedReader()?.use { it.readText() }
                .orEmpty()
        )
    }
    var courseId by remember {
        mutableStateOf(
            parseSML(bundledSml).first
                ?.let { getStringValue(it, "id", "default") }
                ?: "default"
        )
    }
    val appSmlFile = remember(courseId) {
        val userHome = System.getProperty("user.home")
        val dir = File(File(userHome, ".course"), courseId).apply { mkdirs() }
        File(dir, "app.sml")
    }
    var showAccordion by remember { mutableStateOf(false) }
    val transition = updateTransition(targetState = showAccordion, label = "AccordionTransition")
    val width by transition.animateDp(label = "AccordionWidth") { expanded ->
        if (expanded) 450.dp else 0.dp
    }

    LaunchedEffect(courseId) {
        if (!appSmlFile.exists()) {
            appSmlFile.parentFile?.mkdirs()
            appSmlFile.writeText(bundledSml)
        }
    }
    var content by remember { mutableStateOf("") }
    LaunchedEffect(courseId) {
        if (!appSmlFile.exists()) {
            appSmlFile.parentFile?.mkdirs()
            appSmlFile.writeText(bundledSml)
        }
        content = appSmlFile.readText()
    }

    LaunchedEffect(content) {
        if (content.isEmpty()) return@LaunchedEffect
        val (parsedApp, _) = parseSML(content)
        parsedApp?.let {
            courseId = getStringValue(it, "id", courseId)
            topicList.clear()
            langs.clear()
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
                    val name = getStringValue(node, "name", "")
                    appTitle.value = name
                    langs.add(lang)

                    for (topic in node.children) {
                        if (topic.name == "Topic") {
                            val entries = mutableListOf<Lecture>()
                            for (lecture in topic.children) {
                                entries.add(
                                    Lecture(
                                        label = getStringValue(lecture, "label", ""),
                                        src = getStringValue(lecture, "src", ""),
                                        duration = getStringValue(lecture, "duration", ""),
                                        ready = getBooleanValue(lecture, "ready", false),
                                    )
                                )
                            }
                            topicList.add(AccordionEntry(getStringValue(topic, "label", ""), entries))
                        }
                    }
                }
            }
        }
    }


    Column {
        Row(
            modifier = Modifier
                .height(35.dp)
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { showAccordion = !showAccordion }) {
                Icon(
                    imageVector = if (showAccordion) Icons.Default.Close else Icons.Default.Menu,
                    contentDescription = if (showAccordion) "Close" else "Menu",
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }
            if (showAccordion) {
                Text("Topics", color = MaterialTheme.colorScheme.onPrimary)
            }
        }

        Row(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.surface)
                .fillMaxHeight()
                .padding(8.dp)
        ) {
            AnimatedVisibility(
                visible = showAccordion,
                enter = expandHorizontally(),
                exit = shrinkHorizontally()
            ) {
                Column(modifier = Modifier.width(width)) {
                    AccordionList(items = topicList) { l ->
                        lecture = l
                    }
                }
            }

            Column(modifier = Modifier.fillMaxWidth()) {
                ShowLecture(theme, lecture.src, lang, demoDir, onFinished = {
                    val updated = lecture.copy(ready = true)
                    lecture = updated

                    val text0 = appSmlFile.readText()
                    val newText = setLectionReadyInSml(text0, updated.src)
                    if (newText != text0) {
                        appSmlFile.writeText(newText)
                        content = newText
                    }
                })
            }
        }
    }
}

// CHANGE: add helper to set `ready: true` inside the matching `Lection { ... }` block
private fun setLectionReadyInSml(text: String, src: String): String {
    val m = Regex("""\bsrc\s*:\s*["']${Regex.escape(src)}["']""").find(text) ?: return text
    val head = text.substring(0, m.range.first)
    val lectionMatch = Regex("""Lection\s*\{""").findAll(head).lastOrNull() ?: return text
    val braceStart = text.indexOf('{', lectionMatch.range.first)
    if (braceStart < 0) return text

    var i = braceStart + 1
    var depth = 1
    var inString = false
    var quoteChar = '\u0000'
    var escape = false
    while (i < text.length) {
        val ch = text[i]
        if (inString) {
            if (escape) {
                escape = false
            } else if (ch == '\\') {
                escape = true
            } else if (ch == quoteChar) {
                inString = false
            }
        } else {
            if (ch == '"' || ch == '\'') {
                inString = true
                quoteChar = ch
            } else if (ch == '{') {
                depth++
            } else if (ch == '}') {
                depth--
                if (depth == 0) break
            }
        }
        i++
    }
    if (depth != 0) return text
    val blockEnd = i
    val bodyStart = braceStart + 1
    val body = text.substring(bodyStart, blockEnd)

    val readyRegex = Regex("""\bready\s*:\s*(true|false)""")
    return if (readyRegex.containsMatchIn(body)) {
        val newBody = readyRegex.replace(body, "ready: true")
        text.replaceRange(bodyStart, blockEnd, newBody)
    } else {
        val lineStart = text.lastIndexOf('\n', blockEnd - 1).let { if (it == -1) 0 else it + 1 }
        val indent = buildString {
            var j = lineStart
            while (j < text.length && (text[j] == ' ' || text[j] == '\t')) { append(text[j]); j++ }
        }
        val insertion = "\n$indent    ready: true"
        text.replaceRange(blockEnd, blockEnd, insertion)
    }
}