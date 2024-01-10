package com.example.app;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

public class edittask extends AppCompatActivity {

    private Spinner editPrioritySpinner;
    private Spinner editStatusSpinner;
    private Spinner editCategorySpinner;
    private EditText titleEditText;
    private EditText dateEditText;
    private EditText descriptionEditText;
    private int taskId;

    private TaskDbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edittask);

        dbHelper = new TaskDbHelper(this);

        editPrioritySpinner = findViewById(R.id.editprioritySpinner);
        editStatusSpinner = findViewById(R.id.editstatusSpinner);
        editCategorySpinner = findViewById(R.id.editcategorySpinner);
        titleEditText = findViewById(R.id.create_title);
        dateEditText = findViewById(R.id.date);
        descriptionEditText = findViewById(R.id.description);
        Button updateButton = findViewById(R.id.update_button);
        Button deleteButton = findViewById(R.id.delete_button);

        setUpSpinner(editPrioritySpinner, R.array.priority);
        setUpSpinner(editStatusSpinner, R.array.status);
        setUpSpinner(editCategorySpinner, R.array.category);

        updateButton.setOnClickListener(v -> updateTask());

        deleteButton.setOnClickListener(v -> deleteTask());

        taskId = getIntent().getIntExtra("id", -1);
        if (taskId != -1) {
            loadTaskDetails(taskId);
        }

        // Set up date picker dialog
        dateEditText.setOnClickListener(v -> showDatePickerDialog());
    }

    private void showDatePickerDialog() {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                (view, yearSelected, monthOfYear, dayOfMonth) -> {
                    calendar.set(yearSelected, monthOfYear, dayOfMonth);
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                    dateEditText.setText(dateFormat.format(calendar.getTime()));
                },
                year, month, day);
        datePickerDialog.show();
    }

    private void setUpSpinner(Spinner spinner, int arrayResourceId) {

        ArrayAdapter<CharSequence> adapter = new ArrayAdapter<CharSequence>(
                this,
                R.layout.simple_spinner_dropdown_item,  // Use your custom layout for the spinner items
                getResources().getStringArray(arrayResourceId)
        ) {
            @Override
            public View getDropDownView(int position, View convertView, @NonNull ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);


                TextView textView = view.findViewById(android.R.id.text1);
                textView.setTextColor(Color.BLACK);

                return view;
            }
        };


        adapter.setDropDownViewResource(R.layout.simple_spinner);
        spinner.setAdapter(adapter);
    }

    private void updateTask() {
        String priority = editPrioritySpinner.getSelectedItem().toString();
        String status = editStatusSpinner.getSelectedItem().toString();
        String cat = editCategorySpinner.getSelectedItem().toString();
        String title = titleEditText.getText().toString();
        String des = descriptionEditText.getText().toString();
        String date = dateEditText.getText().toString();

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        dateFormat.setLenient(false);

        try {
            Date parsedDate = dateFormat.parse(date);
            assert parsedDate != null;
            date = dateFormat.format(parsedDate);
        } catch (ParseException e) {
            Toast.makeText(this, "Invalid date. Please enter a valid date in DD/MM/YYYY format.", Toast.LENGTH_SHORT).show();
            return;
        }

        dbHelper.updateTask(taskId, title, priority, status, date, cat, des);
        navigateToMainActivity();
    }

    private void deleteTask() {
        dbHelper.deleteTask(taskId);
        navigateToMainActivity();
    }

    private void loadTaskDetails(int taskId) {
        CardInfo task = dbHelper.getTaskById(taskId);

        if (task != null) {
            titleEditText.setText(task.getTitle());
            editPrioritySpinner.setSelection(getIndex(editPrioritySpinner, task.getPriority()));
            editStatusSpinner.setSelection(getIndex(editStatusSpinner, task.getStatus()));
            editCategorySpinner.setSelection(getIndex(editCategorySpinner, task.getCategory()));
            descriptionEditText.setText(task.getDescription());
            dateEditText.setText(task.getDeadline());

            Log.d("LoadTaskDetails", "Title: " + task.getTitle());
            Log.d("LoadTaskDetails", "Priority: " + task.getPriority());
            Log.d("LoadTaskDetails", "Status: " + task.getStatus());
            Log.d("LoadTaskDetails", "Category: " + task.getCategory());
            Log.d("LoadTaskDetails", "Description: " + task.getDescription());
            Log.d("LoadTaskDetails", "Deadline: " + task.getDeadline());
        }
    }

    private int getIndex(Spinner spinner, String value) {
        ArrayAdapter adapter = (ArrayAdapter) spinner.getAdapter();
        for (int i = 0; i < adapter.getCount(); i++) {
            if (Objects.requireNonNull(adapter.getItem(i)).toString().equalsIgnoreCase(value)) {
                return i;
            }
        }
        return 0;
    }

    private void navigateToMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
