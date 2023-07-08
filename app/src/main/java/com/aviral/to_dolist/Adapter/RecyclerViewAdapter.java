package com.aviral.to_dolist.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aviral.to_dolist.Model.Todos;
import com.aviral.to_dolist.R;
import com.aviral.to_dolist.newTodoActivity;
import com.aviral.to_dolist.updateTodosActivity;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private final Context context;
    private final List<Todos> todosList;

    public RecyclerViewAdapter(Context context, List<Todos> todosList) {
        this.context = context;
        this.todosList = todosList;
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.todo_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder holder, int position) {
        Todos todos = todosList.get(position);

        holder.todoHeading.setText(todos.getTodo_heading());
        holder.todoDescription.setText(todos.getTodo_description());
    }

    @Override
    public int getItemCount() {
        return todosList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView todoHeading;
        public TextView todoDescription;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);

            todoHeading = itemView.findViewById(R.id.todo_heading);
            todoDescription = itemView.findViewById(R.id.todo_descriptiion);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            Todos todo = todosList.get(position);
            String todoHeading = todo.getTodo_heading();
            String todoDescription = todo.getTodo_description();

            Bundle bundle = new Bundle();
            bundle.putString("todoHeading", todoHeading);
            bundle.putString("todoDescription", todoDescription);

            Intent intent = new Intent(context, updateTodosActivity.class);
            intent.putExtras(bundle);
            intent.putExtra("todoHeading", todoHeading);
            intent.putExtra("todoDescription", todoDescription);
            context.startActivity(intent);
        }
    }
}
