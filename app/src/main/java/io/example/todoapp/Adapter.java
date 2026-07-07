package io.example.todoapp;

import static java.security.AccessController.getContext;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder>{
    private List<Model> todoList;


    public Adapter(List<Model> todoList){
        this.todoList = todoList;
    }

    @NonNull
    @Override

    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int ViewType){

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view,parent,false);
        return new ViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.ViewHolder holder ,int position){
        Model todo = todoList.get(position);
        holder.todoName.setText(todo.getTodoName());

        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int pos = holder.getAdapterPosition();

                if (pos != RecyclerView.NO_POSITION) {
                    todoList.remove(pos);
                    notifyItemRemoved(pos);
                    notifyItemRangeChanged(pos, todoList.size());
                }

            }
        });
    }

    @Override
    public int getItemCount(){
        return todoList.size();
    }



    //my viewholder class

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView todoName;
        CheckBox isChecked;
        ImageButton button;

        public ViewHolder(@NonNull View itemView){
            super(itemView);
            todoName = itemView.findViewById(R.id.todoName);
            isChecked = itemView.findViewById(R.id.checkBox);
            button = itemView.findViewById(R.id.button);
        }
    }


}
