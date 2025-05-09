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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import at.crowdware.course.theme.ExtendedTheme
import at.crowdware.course.util.*

@Composable
fun ShowLecture(page: String, lang: String) {
    val inputStream = object {}.javaClass.classLoader.getResourceAsStream("pages/$page")
    val content = inputStream?.bufferedReader()?.use { it.readText() }
    if (content != null) {
        val (parsedPage, _) = parseSML(content)
        if (parsedPage != null) {
            val padding = getPadding(parsedPage)
            Column(
                modifier = Modifier.
                background(MaterialTheme.colors.background).
                fillMaxSize().
                padding(top = padding.top.dp, bottom = padding.bottom.dp, start = padding.left.dp, end = padding.right.dp)) {

                for (element in parsedPage.children) {
                    renderElement(element, lang)
                }
            }
        }
    }
}

@Composable
fun renderElement(node: SmlNode, lang: String) {
    when (node.name) {
        "Column" -> {
            renderColumn(node, lang)
        }
        "Row" -> {
            renderRow(node, lang)
        }
        "Markdown" -> {
            renderMarkdown(modifier = Modifier, node, lang)
        }
        "Text" -> {
            renderText(node)
        }
        "Image" -> {
            renderImage(node)
        }
        "Youtube" -> {
            renderYoutube(node)
        }
        "Button" -> {
            renderButton(node)
        }
        else -> {
            println("unhandled element: ${node.name}")
        }
    }
}

@Composable
fun renderColumn(node: SmlNode, lang: String) {
    val padding = getPadding(node)
    Column(modifier = Modifier.padding(top = padding.top.dp, bottom = padding.bottom.dp, start = padding.left.dp, end = padding.right.dp)) {
        for (n in node.children) {
            renderElement(n, lang)
        }
    }
}

@Composable
fun renderRow(node: SmlNode, lang: String) {
    val padding = getPadding(node)
    Row(modifier = Modifier.padding(top = padding.top.dp, bottom = padding.bottom.dp, start = padding.left.dp, end = padding.right.dp)) {
        for (n in node.children) {
            renderElement(n, lang)
        }
    }
}

@Composable
fun renderMarkdown(modifier: Modifier = Modifier, node: SmlNode, lang: String) {
    val text = getStringValue(node, "text", "")
    val color = getStringValue(node, "color", "onBackground")
    val fontSize = getIntValue(node, "fontSize", 16)

    var txt = text
    if (text.startsWith("part:")) {
        val part = text.substringAfter("part:")
        val inputStream = object {}.javaClass.classLoader.getResourceAsStream("parts/$part-$lang.md")
        val content = inputStream?.bufferedReader()?.use { it.readText() }
        if (content != null) {
            txt = content
        }
    }
    val parsedMarkdown = parseMarkdown(txt)
    Text(
        modifier = modifier.fillMaxWidth(),
        text = parsedMarkdown,
        //style = TextStyle(color = hexToColor(color)),
        fontSize = fontSize.sp,
        fontWeight = getFontWeight(node),
        textAlign = getTextAlign(node)
    )
}

@Composable
fun renderText(node: SmlNode) {
    val text = getStringValue(node, "text", "")
    Text(text)
}

@Composable
fun renderImage(node: SmlNode) {

}

@Composable
fun renderYoutube(node: SmlNode) {

}

@Composable
fun renderButton(node: SmlNode) {

}

@Composable
fun parseMarkdown(markdown: String): AnnotatedString {
    val builder = AnnotatedString.Builder()
    val lines = markdown.split("\n") // Process each line individually

    for (i in lines.indices) {
        val line = lines[i]
        var j = 0
        var inCodeBlock = false

        while (j < line.length) {
            if (line[j] == '`') {
                inCodeBlock = !inCodeBlock
                j++
                continue
            }

            if (inCodeBlock) {
                // Append text literally when in code mode
                val endOfCodeBlock = line.indexOf("`", j)
                if (endOfCodeBlock != -1) {
                    builder.withStyle(SpanStyle(fontFamily = FontFamily.Monospace)) {
                        append(line.substring(j, endOfCodeBlock))
                    }
                    j = endOfCodeBlock + 1
                    inCodeBlock = false // Close code mode
                } else {
                    // If no closing backtick is found, append till end of line
                    builder.withStyle(SpanStyle(fontFamily = FontFamily.Monospace)) {
                        append(line.substring(j))
                    }
                    j = line.length
                }
                continue
            }
            when {
                line.startsWith("###### ", j) -> {
                    builder.withStyle(SpanStyle(fontSize = 14.sp, fontWeight = FontWeight.Bold)) {
                        append(line.removePrefix("###### ").trim())
                    }
                    j = line.length
                }
                line.startsWith("##### ", j) -> {
                    builder.withStyle(SpanStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold)) {
                        append(line.removePrefix("##### ").trim())
                    }
                    j = line.length
                }
                line.startsWith("#### ", j) -> {
                    builder.withStyle(SpanStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold)) {
                        append(line.removePrefix("#### ").trim())
                    }
                    j = line.length
                }
                line.startsWith("### ", j) -> {
                    builder.withStyle(SpanStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold)) {
                        append(line.removePrefix("### ").trim())
                    }
                    j = line.length
                }
                line.startsWith("## ", j) -> {
                    builder.withStyle(SpanStyle(fontSize = 24.sp, fontWeight = FontWeight.Bold)) {
                        append(line.removePrefix("## ").trim())
                    }
                    j = line.length
                }
                line.startsWith("# ", j) -> {
                    builder.withStyle(SpanStyle(fontSize = 28.sp, fontWeight = FontWeight.Bold)) {
                        append(line.removePrefix("# ").trim())
                    }
                    j = line.length
                }
                line.startsWith("![", j) -> {
                    // ignore images here
                    val endParen = line.indexOf(")", j)
                    if(endParen == -1)  // not found
                        j++
                    else
                        j = endParen + 1
                }
                line.startsWith("[", j) -> {

                    val endBracket = line.indexOf("]", j)
                    val startParen = line.indexOf("(", endBracket)
                    val endParen = line.indexOf(")", startParen)

                    if (endBracket != -1 && startParen == endBracket + 1 && endParen != -1) {
                        val linkText = line.substring(j + 1, endBracket)
                        val linkUrl = line.substring(startParen + 1, endParen)

                        builder.pushStringAnnotation(tag = "URL", annotation = linkUrl)
                        builder.withStyle(
                            SpanStyle(
                                color = ExtendedTheme.colors.linkColor,
                                textDecoration = TextDecoration.Underline
                            )
                        ) {
                            append(linkText)
                        }
                        builder.pop()
                        j = endParen + 1
                    } else {
                        builder.append(line[j])
                        j++
                    }
                }
                line.startsWith("<", j) && line.indexOf(">", j) > j -> {
                    // ignore html tags
                    val endParen = line.indexOf(">", j)
                    j = endParen + 1
                }
                line.startsWith("***", j) -> {
                    val endIndex = line.indexOf("***", j + 3)
                    if (endIndex != -1) {
                        builder.withStyle(SpanStyle(fontWeight = FontWeight.Bold, fontStyle = FontStyle.Italic)) {
                            append(line.substring(j + 3, endIndex).trim())
                        }
                        j = endIndex + 3
                    } else {
                        builder.append("***")
                        j += 3
                    }
                }
                line.startsWith("**", j) -> {
                    val endIndex = line.indexOf("**", j + 2)
                    if (endIndex != -1) {
                        builder.withStyle(SpanStyle(fontWeight = FontWeight.Bold)) {
                            append(line.substring(j + 2, endIndex).trim())
                        }
                        j = endIndex + 2
                    } else {
                        builder.append("**")
                        j += 2
                    }
                }
                line.startsWith("*", j) -> {
                    val endIndex = line.indexOf("*", j + 1)
                    if (endIndex != -1) {
                        builder.withStyle(SpanStyle(fontStyle = FontStyle.Italic)) {
                            append(line.substring(j + 1, endIndex).trim())
                        }
                        j = endIndex + 1
                    } else {
                        builder.append("*")
                        j += 1
                    }
                }
                line.startsWith("~~", j) -> {
                    val endIndex = line.indexOf("~~", j + 2)
                    if (endIndex != -1) {
                        builder.withStyle(SpanStyle(textDecoration = TextDecoration.LineThrough)) {
                            append(line.substring(j + 2, endIndex).trim())
                        }
                        j = endIndex + 2
                    } else {
                        builder.append("~~")
                        j += 2
                    }
                }
                line.startsWith("(c)", j, ignoreCase = true) -> {
                    builder.append("©")
                    j += 3
                }
                line.startsWith("(r)", j, ignoreCase = true) -> {
                    builder.append("®")
                    j += 3
                }
                line.startsWith("(tm)", j, ignoreCase = true) -> {
                    builder.append("™")
                    j += 4
                }
                else -> {
                    builder.append(line[j])
                    j++
                }
            }
        }

        if (i < lines.size - 1) {
            builder.append("\n")
        }
    }

    return builder.toAnnotatedString()
}

/*
fun hexToColor(hex: String, default: String = "#000000"): Color {
    var value = hex
    if (hex.isEmpty()) {
        value = default
    }

    if(!hex.startsWith("#")) {
        when(hex) {
            "primary" -> {value = currentProject.app?.theme?.primary ?: "" }
            "onPrimary" -> {value = currentProject.app?.theme?.onPrimary ?: "" }
            "primaryContainer" -> {value = currentProject.app?.theme?.primaryContainer ?: "" }
            "onPrimaryContainer" -> {value = currentProject.app?.theme?.onPrimaryContainer ?: "" }
            "surface" -> {value = currentProject.app?.theme?.surface ?: "" }
            "onSurface" -> {value = currentProject.app?.theme?.onSurface ?: "" }
            "secondary" -> {value = currentProject.app?.theme?.secondary ?: "" }
            "onSecondary" -> {value = currentProject.app?.theme?.onSecondary ?: "" }
            "secondaryContainer" -> {value = currentProject.app?.theme?.secondaryContainer ?: "" }
            "onSecondaryContainer" -> {value = currentProject.app?.theme?.onSecondaryContainer ?: "" }
            "tertiary" -> {value = currentProject.app?.theme?.tertiary ?: "" }
            "onTertiary" -> {value = currentProject.app?.theme?.onTertiary ?: "" }
            "tertiaryContainer" -> {value = currentProject.app?.theme?.tertiaryContainer ?: "" }
            "onTertiaryContainer" -> {value = currentProject.app?.theme?.onTertiaryContainer ?: "" }
            "outline" -> {value = currentProject.app?.theme?.outline ?: "" }
            "outlineVariant" -> {value = currentProject.app?.theme?.outlineVariant ?: "" }
            "onErrorContainer" -> {value = currentProject.app?.theme?.onErrorContainer ?: "" }
            "onError" -> {value = currentProject.app?.theme?.onError ?: "" }
            "inverseSurface" -> {value = currentProject.app?.theme?.inverseSurface ?: "" }
            "inversePrimary" -> {value = currentProject.app?.theme?.inversePrimary ?: "" }
            "inverseOnSurface" -> {value = currentProject.app?.theme?.inverseOnSurface ?: "" }
            "background" -> {value = currentProject.app?.theme?.background ?: "" }
            "onBackground" -> {value = currentProject.app?.theme?.onBackground ?: "" }
            "error" -> {value = currentProject.app?.theme?.error ?: "" }
            "scrim" -> {value = currentProject.app?.theme?.scrim ?: "" }
            else -> {value = default}
        }
    }

    val color = value.trimStart('#')
    return when (color.length) {
        6 -> {
            // Hex without alpha (e.g., "RRGGBB")
            val r = color.substring(0, 2).toIntOrNull(16) ?: return Color.Black
            val g = color.substring(2, 4).toIntOrNull(16) ?: return Color.Black
            val b = color.substring(4, 6).toIntOrNull(16) ?: return Color.Black
            Color(r, g, b)
        }
        8 -> {
            // Hex with alpha (e.g., "AARRGGBB")
            val a = color.substring(0, 2).toIntOrNull(16) ?: return Color.Black
            val r = color.substring(2, 4).toIntOrNull(16) ?: return Color.Black
            val g = color.substring(4, 6).toIntOrNull(16) ?: return Color.Black
            val b = color.substring(6, 8).toIntOrNull(16) ?: return Color.Black
            Color(r, g, b, a)
        }
        else -> Color.Black
    }
}*/

fun getFontWeight(node: SmlNode): FontWeight {
    val key = getStringValue(node, "fontWeight", "").trim().lowercase()
    return fontWeightMap.getOrDefault(key, FontWeight.Normal)
}

fun getTextAlign(node: SmlNode): TextAlign {
    val key = getStringValue(node, "textAlign", "").trim().lowercase()
    return textAlignMap.getOrDefault(key, TextAlign.Start)
}

val fontWeightMap = mapOf(
    "bold" to FontWeight.Bold,
    "black" to FontWeight.Black,
    "thin" to FontWeight.Thin,
    "extrabold" to FontWeight.ExtraBold,
    "extralight" to FontWeight.ExtraLight,
    "light" to FontWeight.Light,
    "medium" to FontWeight.Medium,
    "semibold" to FontWeight.SemiBold,
    "" to FontWeight.Normal
)

val textAlignMap = mapOf(
    "left" to TextAlign.Start,
    "center" to TextAlign.Center,
    "right" to TextAlign.End,
    "" to TextAlign.Start
)