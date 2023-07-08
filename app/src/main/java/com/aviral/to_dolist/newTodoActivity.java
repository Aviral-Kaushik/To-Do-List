package com.aviral.to_dolist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.aviral.to_dolist.Data.MyDbHandler;
import com.aviral.to_dolist.Model.Todos;

public class newTodoActivity extends AppCompatActivity {
    
    EditText todo_heading, todo_description;
    Button addNewTodo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_todo);

        MyDbHandler db = new MyDbHandler(newTodoActivity.this);
        
        todo_heading = findViewById(R.id.todo_heading);
        todo_description = findViewById(R.id.todoDescription);
        addNewTodo = findViewById(R.id.addNewTodo);
        
        addNewTodo.setOnClickListener(view -> {
            String todoHeading = String.valueOf(todo_heading.getText());
            String todoDescription = String.valueOf(todo_description.getText());
            
            if (!todoHeading.equals("") && !todoDescription.equals("")) {
                Todos newTodo = new Todos();
                newTodo.setTodo_heading(todoHeading);
                newTodo.setTodo_description(todoDescription);

                db.addTodo(newTodo);
                Toast.makeText(this, "Todo Added Successfully", Toast.LENGTH_SHORT).show();

                todo_heading.setText("");
                todo_description.setText("");

                Intent intent = new Intent(newTodoActivity.this, MainActivity.class);
                startActivity(intent);

            } else {
                Toast.makeText(this, "Invalid Details", Toast.LENGTH_SHORT).show();
            }
        });
        
    }
}