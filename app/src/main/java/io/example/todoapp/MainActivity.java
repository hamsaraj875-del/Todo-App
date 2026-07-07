package io.example.todoapp;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    EditText editText;
    Adapter adapter;
    ImageButton send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        recyclerView = findViewById(R.id.recyclerView);

        ArrayList<Model> todoList = new ArrayList<>();

        todoList.add(new Model("Fresh up",false));
        todoList.add(new Model("Breakfast",false));
        todoList.add(new Model("Class",false));
        todoList.add(new Model("Launch",false));

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(layoutManager);
        adapter = new Adapter(todoList);
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
                }
            }
        });
    }
}