package com.example.studymate

import com.example.studymate.util.TaskValidation
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class TaskValidationTest {

    @Test
    fun validTitle_returnsTrue() {
        val result = TaskValidation.isValidTitle("Finish Lab 05 Assignment")
        assertTrue(result)
    }

    @Test
    fun emptyTitle_returnsFalse() {
        val result = TaskValidation.isValidTitle("")
        assertFalse(result)
    }

    @Test
    fun whitespaceOnlyTitle_returnsFalse() {
        val result = TaskValidation.isValidTitle("   ")
        assertFalse(result)
    }
}
