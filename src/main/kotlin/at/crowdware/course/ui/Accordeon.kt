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

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.unit.dp

data class Lecture(val label: String, val page: String)
data class AccordionEntry(val title: String, val content: MutableList<Lecture>)

@Composable
fun AccordionItem(entry: AccordionEntry, onClick: (String) -> Unit) {
    var expanded by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { expanded = !expanded }
            .padding(horizontal = 8.dp, vertical = 4.dp)
            .background(MaterialTheme.colors.primary)
            .animateContentSize()
            .padding(4.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = entry.title,
                style = MaterialTheme.typography.button,
                modifier = Modifier.weight(1f),
                color = MaterialTheme.colors.onPrimary
            )

            Icon(
                imageVector = Icons.Default.KeyboardArrowDown,
                contentDescription = "Expand",
                modifier = Modifier.rotate(if (expanded) 180f else 0f),
                tint = MaterialTheme.colors.onPrimary
            )
        }

        if (expanded) {
            Spacer(modifier = Modifier.height(8.dp))
            for (item in entry.content) {
                Button(
                    onClick = {onClick(item.page)},
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Start,
                    ) {
                        Text(
                            text = item.label,
                            style = MaterialTheme.typography.button,
                            color = MaterialTheme.colors.onPrimary
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun AccordionList(items: List<AccordionEntry>, onItemClicked: (String) -> Unit) {
    val scrollState = rememberScrollState()
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .verticalScroll(scrollState)
                .padding(end = 12.dp) // Platz für Scrollbar
        ) {
            items.forEach { entry ->
                AccordionItem(entry, onClick = onItemClicked)
            }
        }

        VerticalScrollbar(
            adapter = rememberScrollbarAdapter(scrollState),
            modifier = Modifier.align(Alignment.CenterEnd)
        )
    }
}