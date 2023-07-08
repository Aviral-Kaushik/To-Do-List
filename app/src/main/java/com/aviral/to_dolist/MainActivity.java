 package com.aviral.to_dolist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.aviral.to_dolist.Adapter.RecyclerViewAdapter;
import com.aviral.to_dolist.Data.MyDbHandler;
import com.aviral.to_dolist.Model.Todos;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

 public class MainActivity extends AppCompatActivity {

    FloatingActionButton newTodo;
    RecyclerView todosRecyclerView;
    ArrayList<Todos> todosArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        todosRecyclerView = findViewById(R.id.todosRecyclerView);
        todosRecyclerView.setHasFixedSize(true);
        todosRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        MyDbHandler db = new MyDbHandler(MainActivity.this);

        newTodo = findViewById(R.id.newTodoFab);

        SharedPreferences checkPreferences = getSharedPreferences("loginDetails", MODE_PRIVATE);
        boolean isLogin = checkPreferences.getBoolean("isLogin", false);

        if (!isLogin) {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
        }

        newTodo.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, newTodoActivity.class);
            startActivity(intent);
        });

        todosArrayList = new ArrayList<>();
        List<Todos> todos = db.getAllTodos();

        for (Todos todo:todos) {
            todosArrayList.add(todo);
        }

        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(MainActivity.this, todosArrayList);
        todosRecyclerView.setAdapter(recyclerViewAdapter);

    }
}