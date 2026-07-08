package io.example.todoapp;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.core.splashscreen.SplashScreen;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements Adapter.OnTaskChangedListener {

    RecyclerView recyclerView;
    EditText editText;
    Adapter adapter;
    ImageButton send;
    ProgressBar progressBar;
    TextView progress;
    ArrayList<Model> todoList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SplashScreen.installSplashScreen(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        progressBar = findViewById(R.id.progressBar);
        progress = findViewById(R.id.progress);

        todoList.add(new Model("Breakfast",false));
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        updateProgress(todoList);

        recyclerView.setLayoutManager(layoutManager);
        adapter = new Adapter(todoList,this);
        recyclerView.setAdapter(adapter);


        editText = findViewById(R.id.editText);
        send = findViewById(R.id.send);
        send.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(editText.getText().toString().length()==0){
                    Toast.makeText(MainActivity.this,"Please Add Some Todo ",Toast.LENGTH_SHORT).show();
                }else{
                    todoList.add(new Model(editText.getText().toString(),false));
                    editText.setText("");
                    adapter.notifyItemInserted(todoList.size() - 1);
                    updateProgress(todoList);
                }
            }
        });

    }
    public void updateProgress(List<Model> todoList){
        if(todoList.size() == 0){
            progressBar.setProgress(0);
            progress.setText("0%");
        }
        else{
            int count = 0;
            for(Model item:todoList){
                if(item.getIsCompleted()){
                    count++;
                }
            }
            int perc = (count*100)/todoList.size();
            progressBar.setProgress(perc);
            progress.setText(String.valueOf(perc)+"%");
        }
    }

    @Override
    public void onTaskChanged() {
        updateProgress(todoList);
    }
}