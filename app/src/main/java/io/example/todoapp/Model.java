package io.example.todoapp;

import android.widget.CheckBox;
import android.widget.TextView;

public class Model {
    String todoName;
    Boolean isCompleted;


    public Model (String todoName,Boolean isCompleted){
        this.todoName = todoName;
        this.isCompleted = isCompleted;
    }

    public String getTodoName(){
        return todoName;
    }

    public Boolean getIsCompleted(){
        return isCompleted;
    }
}
