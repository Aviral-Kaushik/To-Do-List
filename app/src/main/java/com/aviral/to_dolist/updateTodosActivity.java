package com.aviral.to_dolist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.aviral.to_dolist.Data.MyDbHandler;
import com.aviral.to_dolist.Model.Todos;

public class updateTodosActivity extends AppCompatActivity {

    EditText todoHeadingUpdate, todoDescriptionUpdate;
    Button updateTodo, deleteTodo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_todos);

        todoHeadingUpdate = findViewById(R.id.todoUpdatedHeading);
        todoDescriptionUpdate = findViewById(R.id.todoUpdatedDescription);
        updateTodo = findViewById(R.id.updateTodo);
        deleteTodo = findViewById(R.id.deleteTodo);

        MyDbHandler db = new MyDbHandler(updateTodosActivity.this);
        Intent getIntent = getIntent();
        Bundle bundle = getIntent.getExtras();
        String todoHeading = getIntent.getStringExtra("todoHeading");
        String todoDescription = getIntent.getStringExtra("todoDescription");
        String todoUpdatedHeading = String.valueOf(todoHeadingUpdate.getText());
        String todoUpdateDescription = String.valueOf(todoDescriptionUpdate.getText());

        updateTodo.setOnClickListener(view -> {
                Todos todo = new Todos();
                todo.setTodo_heading(todoUpdatedHeading);
                todo.setTodo_description(todoUpdateDescription);

                db.updateTodo(todo);

                Intent intent = new Intent(updateTodosActivity.this, MainActivity.class);
                startActivity(intent);

                Toast.makeText(this, "Todo Updated Successfully", Toast.LENGTH_SHORT).show();
        });

        deleteTodo.setOnClickListener(view -> {
                Todos todo = new Todos();
                todo.setTodo_heading(todoUpdatedHeading);
                todo.setTodo_description(todoUpdateDescription);

                db.deleteTodo(todo);

                Intent intent = new Intent(updateTodosActivity.this, MainActivity.class);
                startActivity(intent);
                Toast.makeText(this, "Todo Deleted Successfully", Toast.LENGTH_SHORT).show();
        });

        todoHeadingUpdate.setText(todoHeading);
        todoDescriptionUpdate.setText(todoDescription);

    }
}