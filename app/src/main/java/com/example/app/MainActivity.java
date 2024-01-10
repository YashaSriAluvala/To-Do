package com.example.app;

// MainActivity.java
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private TaskDbHelper dbHelper;
    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new TaskDbHelper(this);

        spinner = findViewById(R.id.spinner);

        ArrayAdapter<CharSequence> spinnerAdapter = ArrayAdapter.createFromResource(
                this,
                R.array.sort_options,
                android.R.layout.simple_spinner_item
        );
        spinnerAdapter.setDropDownViewResource(R.layout.simple_spinner);
        spinner.setAdapter(spinnerAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                setRecycler();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });

        FloatingActionButton add = findViewById(R.id.addbutton);
        add.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddTask.class);
            startActivity(intent);
        });

        setRecycler();
    }

    private void setRecycler() {
        RecyclerView recyclerView = findViewById(R.id.recycler_view);

        List<CardInfo> allTasks;
        if (spinner.getSelectedItemPosition() == 0) {
            allTasks = dbHelper.getAllTasks();
        } else {
            allTasks = filterDataByCategory();
        }

        Adapter adapter = new Adapter(allTasks);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private List<CardInfo> filterDataByCategory() {
        return dbHelper.getTasksByCategory(spinner.getSelectedItem().toString());
    }
}
