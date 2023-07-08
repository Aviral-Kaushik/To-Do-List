package com.aviral.to_dolist.Model;

public class Todos {

    private int id;
    private String todo_heading;
    private String todo_description;

    public Todos(String todo_heading, String todo_description) {
        this.todo_heading = todo_heading;
        this.todo_description = todo_description;
    }

    public Todos(int id, String todo_heading, String todo_description) {
        this.id = id;
        this.todo_heading = todo_heading;
        this.todo_description = todo_description;
    }

    public Todos() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTodo_heading() {
        return todo_heading;
    }

    public void setTodo_heading(String todo_heading) {
        this.todo_heading = todo_heading;
    }

    public String getTodo_description() {
        return todo_description;
    }

    public void setTodo_description(String todo_description) {
        this.todo_description = todo_description;
    }

}
