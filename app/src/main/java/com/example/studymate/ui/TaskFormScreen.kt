
package com.example.studymate.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.studymate.data.Task

@Composable
fun TaskFormScreen(
    task: Task?,
    onSave: (String, String, Boolean) -> Unit
) {
    var title by remember(task) {
        mutableStateOf(task?.title ?: "")
    }

    var description by remember(task) {
        mutableStateOf(task?.description ?: "")
    }

    var completed by remember(task) {
        mutableStateOf(task?.isCompleted ?: false)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        Text(
            text = if (task != null) "Edit Task" else "Add Task",
            style = MaterialTheme.typography.headlineMedium
        )

        OutlinedTextField(
            value = title,
            onValueChange = { title = it },
            label = { Text("Task Title") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = description,
            onValueChange = { description = it },
            label = { Text("Description") },
            modifier = Modifier.fillMaxWidth()
        )

        Row {
            Checkbox(
                checked = completed,
                onCheckedChange = { completed = it }
            )

            Text("Completed")
        }

        Button(
            onClick = {
                onSave(title, description, completed)
            },
            enabled = title.isNotBlank(),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Save Task")
        }
    }
}
