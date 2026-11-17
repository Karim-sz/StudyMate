package com.example.studymate.util

object TaskValidation {
    /**
     * Validates a task title.
     * Returns true if title is not empty or blank, false otherwise.
     */
    fun isValidTitle(title: String): Boolean {
        return title.trim().isNotBlank()
    }
}
