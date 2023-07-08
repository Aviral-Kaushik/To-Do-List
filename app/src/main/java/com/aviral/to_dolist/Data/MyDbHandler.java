package com.aviral.to_dolist.Data;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.aviral.to_dolist.Model.Todos;
import com.aviral.to_dolist.Params.Params;

import java.util.ArrayList;
import java.util.List;

public class MyDbHandler extends SQLiteOpenHelper {

    public MyDbHandler(Context context) {
        super(context, Params.DB_NAME, null, Params.DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String create = "CREATE TABLE " + Params.TABLE_NAME + "("
                + Params.KEY_ID + " INTEGER PRIMARY KEY, " + Params.KEY_TODO_HEADING
                + " TEXT, " + Params.KEY_TODO_DESCRIPTION + " TEXT" + ")";

        Log.d("DataBase", "Query is:" + create);

        db.execSQL(create);
        db.isOpen();
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void addTodo(Todos todo) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues todosValue = new ContentValues();
        todosValue.put(Params.KEY_TODO_HEADING, todo.getTodo_heading());
        todosValue.put(Params.KEY_TODO_DESCRIPTION, todo.getTodo_description());

        db.insert(Params.TABLE_NAME, null, todosValue);
        Log.d("DataBase", "Successfully Inserted");
        db.close();
    }

    public List<Todos> getAllTodos() {
        List<Todos> todosList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT * FROM "+  Params.TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                Todos todos = new Todos();
                todos.setId(Integer.parseInt(cursor.getString(0)));
                todos.setTodo_heading(cursor.getString(1));
                todos.setTodo_description(cursor.getString(2));
                todosList.add(todos);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return todosList;
    }

    public void updateTodo(Todos todo) {
        SQLiteDatabase db = this.getReadableDatabase();

        ContentValues todoValues = new ContentValues();
        todoValues.put(Params.KEY_TODO_HEADING, todo.getTodo_heading());
        todoValues.put(Params.KEY_TODO_DESCRIPTION, todo.getTodo_description());

        db.update(Params.TABLE_NAME, todoValues, Params.KEY_ID + "=?",
                new String[]{String.valueOf(todo.getId())});
    }

    public void deleteTodo(Todos todo) {
        SQLiteDatabase db = this.getReadableDatabase();
        db.delete(Params.TABLE_NAME, Params.KEY_ID + "=?", new String[]{String.valueOf(todo.getId())});
        db.close();
    }
}
