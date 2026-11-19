
package com.example.studymate

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.lifecycleScope
import com.example.studymate.data.Task
import com.example.studymate.data.TaskDataController
import com.example.studymate.ui.TaskFormScreen

class TaskFormActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val taskId = intent.getIntExtra("TASK_ID", -1)

        val controller = TaskDataController(
            this,
            lifecycleScope
        )

        setContent {

            var task by remember {
                mutableStateOf<Task?>(null)
            }

            var isLoading by remember {
                mutableStateOf(taskId != -1)
            }

            LaunchedEffect(taskId) {
                if (taskId != -1) {
                    controller.loadTask(taskId) {
                        task = it
                        isLoading = false
                    }
                }
            }

            if (isLoading) {

                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }

            } else if (taskId != -1 && task == null) {

                Column(
                    modifier = Modifier.padding(24.dp)
                ) {
                    Text("Task not found")

                    Button(
                        onClick = { finish() }
                    ) {
                        Text("Back")
                    }
                }

            } else {

                TaskFormScreen(
                    task = task,

                    onSave = { title, description, completed ->

                        val existingTask = task

                        if (existingTask == null) {

                            controller.addTask(
                                title = title,
                                description = description,
                                isCompleted = completed
                            ) {
                                finish()
                            }

                        } else {

                            controller.updateTask(
                                existingTask.copy(
                                    title = title,
                                    description = description,
                                    isCompleted = completed
                                )
                            ) {
                                finish()
                            }
                        }
                    }
                )
            }
        }
    }
}
