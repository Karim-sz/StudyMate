
package com.example.studymate

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.studymate.ui.TaskFormScreen

class TaskFormActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val taskId = intent.getIntExtra("TASK_ID", -1)

        setContent {
            TaskFormScreen(
                isEditing = taskId != -1,

                onSave = { title, description, completed ->

                    val result = Intent()
                    result.putExtra("TASK_TITLE", title)
                    result.putExtra("TASK_DESCRIPTION", description)
                    result.putExtra("TASK_COMPLETED", completed)

                    setResult(RESULT_OK, result)
                    finish()
                },

                onBack = {
                    finish()
                }
            )
        }
    }
}
