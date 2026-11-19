
package com.example.studymate

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.lifecycleScope
import com.example.studymate.data.Task
import com.example.studymate.data.TaskDataController
import com.example.studymate.ui.TaskListScreen

class MainActivity : ComponentActivity() {

    private lateinit var taskController: TaskDataController

    private val tasks = mutableStateOf<List<Task>>(emptyList())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        taskController = TaskDataController(this, lifecycleScope)

        setContent {
            TaskListScreen(
                tasks = tasks.value,

                onAdd = {
                    val intent = Intent(this, TaskFormActivity::class.java)
                    startActivity(intent)
                },

                onEdit = { taskId ->
                    val intent = Intent(this, TaskFormActivity::class.java)
                    intent.putExtra("TASK_ID", taskId)
                    startActivity(intent)
                },

                onDelete = { task ->
                    taskController.deleteTask(task) {
                        loadTasks()
                    }
                },

                onToggleComplete = { task ->
                    taskController.toggleComplete(task) {
                        loadTasks()
                    }
                }
            )
        }
    }

    override fun onResume() {
        super.onResume()

        if (::taskController.isInitialized) {
            loadTasks()
        }
    }

    private fun loadTasks() {
        taskController.loadTasks { taskList ->
            tasks.value = taskList
        }
    }
}
