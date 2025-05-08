package at.crowdware.course.ui

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
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

data class AccordionEntry(val title: String, val content: MutableList<String>)

@Composable
fun AccordionItem(entry: AccordionEntry) {
    var expanded by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { expanded = !expanded }
            .padding(horizontal = 8.dp, vertical = 4.dp)
            .background(MaterialTheme.colors.primary)
            .animateContentSize()
            .padding(16.dp)
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
                Text(
                    text = item,
                    style = MaterialTheme.typography.button,
                    color = MaterialTheme.colors.onPrimary
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}

@Composable
fun AccordionList(items: List<AccordionEntry>) {
    val scrollState = rememberScrollState()
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .verticalScroll(scrollState)
                .padding(end = 12.dp) // Platz für Scrollbar
        ) {
            items.forEach { entry ->
                AccordionItem(entry)
            }
        }

        VerticalScrollbar(
            adapter = rememberScrollbarAdapter(scrollState),
            modifier = Modifier.align(Alignment.CenterEnd)
        )
    }
}