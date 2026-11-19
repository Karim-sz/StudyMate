
package com.example.studymate

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.lifecycleScope
import com.example.studymate.data.Task
import com.example.studymate.data.TaskDataController
import com.example.studymate.ui.TaskFormScreen

class TaskFormActivity : ComponentActivity() {

    private lateinit var taskController: TaskDataController

    private val currentTask = mutableStateOf<Task?>(null)
    private val isLoading = mutableStateOf(false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        taskController = TaskDataController(this, lifecycleScope)

        val taskId = intent.getIntExtra("TASK_ID", -1)

        if (taskId != -1) {
            isLoading.value = true

            taskController.loadTask(taskId) { task ->
                currentTask.value = task
                isLoading.value = false
            }
        }

        setContent {
            if (isLoading.value) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            } else {
                TaskFormScreen(
                    task = currentTask.value,

                    onSave = { title, description, completed ->

                        if (taskId == -1) {
                            taskController.addTask(
                                title = title,
                                description = description,
                                isCompleted = completed
                            ) {
                                finish()
                            }
                        } else {
                            currentTask.value?.let { task ->
                                taskController.updateTask(
                                    task.copy(
                                        title = title,
                                        description = description,
                                        isCompleted = completed
                                    )
                                ) {
                                    finish()
                                }
                            }
                        }
                    },

                    onBack = {
                        finish()
                    }
                )
            }
        }
    }
}
