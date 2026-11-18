package com.example.studymate.data

import android.content.Context
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TaskDataController(
    context: Context,
    private val scope: CoroutineScope
) {

    private val repository = TaskRepository(
        TaskDatabase.getInstance(context).taskDao()
    )

    fun loadTasks(onResult: (List<Task>) -> Unit) {
        scope.launch(Dispatchers.IO) {
            val tasks = repository.getAllTasks()
            withContext(Dispatchers.Main) {
                onResult(tasks)
            }
        }
    }

    fun loadTask(id: Int, onResult: (Task?) -> Unit) {
        scope.launch(Dispatchers.IO) {
            val task = repository.getTaskById(id)
            withContext(Dispatchers.Main) {
                onResult(task)
            }
        }
    }

    fun addTask(
        title: String,
        description: String,
        isCompleted: Boolean,
        onDone: () -> Unit = {}
    ) {
        scope.launch(Dispatchers.IO) {
            repository.insertTask(
                Task(
                    title = title,
                    description = description,
                    isCompleted = isCompleted
                )
            )
            withContext(Dispatchers.Main) {
                onDone()
            }
        }
    }

    fun updateTask(task: Task, onDone: () -> Unit = {}) {
        scope.launch(Dispatchers.IO) {
            repository.updateTask(task)
            withContext(Dispatchers.Main) {
                onDone()
            }
        }
    }

    fun toggleComplete(task: Task, onDone: () -> Unit = {}) {
        scope.launch(Dispatchers.IO) {
            repository.toggleComplete(task)
            withContext(Dispatchers.Main) {
                onDone()
            }
        }
    }

    fun deleteTask(task: Task, onDone: () -> Unit = {}) {
        scope.launch(Dispatchers.IO) {
            repository.deleteTask(task)
            withContext(Dispatchers.Main) {
                onDone()
            }
        }
    }
}