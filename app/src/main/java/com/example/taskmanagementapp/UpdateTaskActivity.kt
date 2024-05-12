package com.example.taskmanagementapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.taskmanagementapp.databinding.ActivityAddTasksBinding
import com.example.taskmanagementapp.databinding.ActivityUpdateBinding

class UpdateTaskActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUpdateBinding
    private lateinit var db : TasksDatabaseHelper
    private var taskId : Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = TasksDatabaseHelper(this)

        taskId = intent.getIntExtra("task_id",-1)
        if (taskId == -1){
            finish()
            return
        }
        val task = db.getTaskById(taskId)
        binding.titleEditText.setText(task.title)
        binding.contentEditText.setText(task.content)

        binding.updateButton.setOnClickListener{
            val newTitle = binding.titleEditText.text.toString()
            val newContent = binding.contentEditText.text.toString()
            val updatedTask =  Task(taskId,newTitle,newContent)

            db.updateTask(updatedTask)
            finish()
            Toast.makeText(this,"change Saved",Toast.LENGTH_SHORT).show()


        }

        }
}