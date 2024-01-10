package com.example.app;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class AddTask extends AppCompatActivity {

    private TaskDbHelper dbHelper;
    private EditText createTitle, createDate, description;
    private Spinner addPrioritySpinner, addStatusSpinner, addCategorySpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        dbHelper = new TaskDbHelper(this);

        createTitle = findViewById(R.id.title);
        createDate = findViewById(R.id.date);
        addPrioritySpinner = findViewById(R.id.addpriority);
        addStatusSpinner = findViewById(R.id.addstatus);
        addCategorySpinner = findViewById(R.id.addcategorySpinner);
        description = findViewById(R.id.description);
        Button saveButton = findViewById(R.id.save_button);

        ArrayAdapter<CharSequence> priorityAdapter = ArrayAdapter.createFromResource(
                this, R.array.priority, R.layout.simple_spinner_dropdown_item);
        priorityAdapter.setDropDownViewResource(R.layout.simple_spinner);
        addPrioritySpinner.setAdapter(priorityAdapter);

        ArrayAdapter<CharSequence> statusAdapter = ArrayAdapter.createFromResource(
                this, R.array.status, R.layout.simple_spinner_dropdown_item);
        statusAdapter.setDropDownViewResource(R.layout.simple_spinner);
        addStatusSpinner.setAdapter(statusAdapter);

        ArrayAdapter<CharSequence> categoryAdapter = ArrayAdapter.createFromResource(
                this, R.array.category, R.layout.simple_spinner_dropdown_item);
        categoryAdapter.setDropDownViewResource(R.layout.simple_spinner);
        addCategorySpinner.setAdapter(categoryAdapter);


        createDate.setOnClickListener(v -> showDatePickerDialog());

        saveButton.setOnClickListener(v -> saveTask());
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
                    createDate.setText(dateFormat.format(calendar.getTime()));
                },
                year, month, day);
        datePickerDialog.show();
    }


    private void saveTask() {
        if (createTitle.getText().toString().trim().length() > 0) {
            String title = createTitle.getText().toString();
            String dateString = createDate.getText().toString();
            String priority = addPrioritySpinner.getSelectedItem().toString();
            String status = addStatusSpinner.getSelectedItem().toString();
            String category = addCategorySpinner.getSelectedItem().toString();
            String desc = description.getText().toString();

            if (dbHelper.isTaskTitleDuplicate(title, -1)) {
                Toast.makeText(this, "Duplicate title. Please choose a different title.", Toast.LENGTH_SHORT).show();
                return;
            }

            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
            dateFormat.setLenient(false);

            try {
                Date date = dateFormat.parse(dateString);

                assert date != null;
                dateString = dateFormat.format(date);
            } catch (ParseException e) {
                Toast.makeText(this, "Invalid date. Please enter a valid date in DD/MM/YYYY format.", Toast.LENGTH_SHORT).show();
                return;
            }

            SQLiteDatabase db = dbHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(TaskContract.TaskEntry.COLUMN_TITLE, title);
            values.put(TaskContract.TaskEntry.COLUMN_PRIORITY, priority);
            values.put(TaskContract.TaskEntry.COLUMN_STATUS, status);
            values.put(TaskContract.TaskEntry.COLUMN_DEADLINE, dateString);
            values.put(TaskContract.TaskEntry.COLUMN_CATEGORY, category);
            values.put(TaskContract.TaskEntry.COLUMN_DESCRIPTION, desc);

            long newRowId = db.insert(TaskContract.TaskEntry.TABLE_NAME, null, values);

            if (newRowId != -1) {
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
            } else {
                Toast.makeText(this, "Error inserting data into the database", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Please fill in all required fields.", Toast.LENGTH_SHORT).show();
        }
    }
}
