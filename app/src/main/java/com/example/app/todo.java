package com.example.app;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class todo extends AppCompatActivity {
    private TaskDbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo);

        dbHelper = new TaskDbHelper(this);

        new Handler(Looper.getMainLooper()).postDelayed(this::loadTasksAndNavigate, 1000);
    }

    private void loadTasksAndNavigate() {
        List<CardInfo> result = dbHelper.getAllTasks();
        DataObject.getListData().clear();
        DataObject.getListData().addAll(result);

        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            Intent intent = new Intent(todo.this, MainActivity.class);
            startActivity(intent);
            finish();
        }, 1000);
    }
}
