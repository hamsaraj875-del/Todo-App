package io.example.todoapp;

import static java.security.AccessController.getContext;

import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder>{
    private List<Model> todoList;
    private OnTaskChangedListener listener;


    public Adapter(List<Model> todoList,OnTaskChangedListener listener){
        this.todoList = todoList;
        this.listener = listener;
    }

    @NonNull
    @Override

    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int ViewType){

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view,parent,false);
        return new ViewHolder(itemView);


    }public interface OnTaskChangedListener {
        void onTaskChanged();
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
                    listener.onTaskChanged();
                }

            }
        });
        holder.isChecked.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {


                if (isChecked) {
                    holder.todoName.setPaintFlags(
                            holder.todoName.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG
                    );
                } else {
                    holder.todoName.setPaintFlags(
                            holder.todoName.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG)
                    );
                }
                todo.setCompleted(isChecked);
                listener.onTaskChanged();
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
