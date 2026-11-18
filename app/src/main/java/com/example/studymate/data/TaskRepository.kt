package com.example.studymate.data

class TaskRepository(private val taskDao: TaskDao) {

    suspend fun getAllTasks(): List<Task> {
        return taskDao.getAll()
    }

    suspend fun getTaskById(id: Int): Task? {
        return taskDao.getById(id)
    }

    suspend fun insertTask(task: Task) {
        taskDao.insert(task)
    }

    suspend fun updateTask(task: Task) {
        taskDao.update(task)
    }

    suspend fun deleteTask(task: Task) {
        taskDao.delete(task)
    }

    suspend fun toggleComplete(task: Task) {
        taskDao.update(task.copy(isCompleted = !task.isCompleted))
    }
}