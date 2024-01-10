package com.example.app;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class TaskDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "tasks.db";
    private static final int DATABASE_VERSION = 1;

    public TaskDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String SQL_CREATE_TASKS_TABLE = "CREATE TABLE " +
                TaskContract.TaskEntry.TABLE_NAME + " (" +
                TaskContract.TaskEntry.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                TaskContract.TaskEntry.COLUMN_TITLE + " TEXT NOT NULL, " +
                TaskContract.TaskEntry.COLUMN_PRIORITY + " TEXT NOT NULL, " +
                TaskContract.TaskEntry.COLUMN_STATUS + " TEXT, " +
                TaskContract.TaskEntry.COLUMN_DEADLINE + " TEXT, " +
                TaskContract.TaskEntry.COLUMN_CATEGORY + " TEXT, " +
                TaskContract.TaskEntry.COLUMN_DESCRIPTION + " TEXT" +
                ");";

        db.execSQL(SQL_CREATE_TASKS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TaskContract.TaskEntry.TABLE_NAME);
        onCreate(db);
    }

    public boolean isTaskTitleDuplicate(String title, int taskId) {
        SQLiteDatabase db = getReadableDatabase();

        String[] projection = {TaskContract.TaskEntry.COLUMN_ID};
        String selection = TaskContract.TaskEntry.COLUMN_TITLE + " = ? AND " +
                TaskContract.TaskEntry.COLUMN_ID + " <> ?";
        String[] selectionArgs = {title, String.valueOf(taskId)};

        try (Cursor cursor = db.query(
                TaskContract.TaskEntry.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        )) {
            return cursor != null && cursor.moveToFirst();
        }
    }

    public void updateTask(int taskId, String title, String priority, String status, String deadline, String category, String description) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(TaskContract.TaskEntry.COLUMN_TITLE, title);
        values.put(TaskContract.TaskEntry.COLUMN_PRIORITY, priority);
        values.put(TaskContract.TaskEntry.COLUMN_STATUS, status);
        values.put(TaskContract.TaskEntry.COLUMN_DEADLINE, deadline);
        values.put(TaskContract.TaskEntry.COLUMN_CATEGORY, category);
        values.put(TaskContract.TaskEntry.COLUMN_DESCRIPTION, description);

        String selection = TaskContract.TaskEntry.COLUMN_ID + " = ?";
        String[] selectionArgs = {String.valueOf(taskId)};

        db.update(TaskContract.TaskEntry.TABLE_NAME, values, selection, selectionArgs);
    }

    public void deleteTask(int taskId) {
        SQLiteDatabase db = getWritableDatabase();

        String selection = TaskContract.TaskEntry.COLUMN_ID + " = ?";
        String[] selectionArgs = {String.valueOf(taskId)};

        db.delete(TaskContract.TaskEntry.TABLE_NAME, selection, selectionArgs);
    }

    public List<CardInfo> getAllTasks() {

        return getTasksByCategory(null);
    }

    public List<CardInfo> getTasksByCategory(String category) {
        List<CardInfo> taskList = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();

        String[] projection = {
                TaskContract.TaskEntry.COLUMN_ID,
                TaskContract.TaskEntry.COLUMN_TITLE,
                TaskContract.TaskEntry.COLUMN_PRIORITY,
                TaskContract.TaskEntry.COLUMN_STATUS,
                TaskContract.TaskEntry.COLUMN_DEADLINE,
                TaskContract.TaskEntry.COLUMN_CATEGORY,

                TaskContract.TaskEntry.COLUMN_DESCRIPTION
        };

        String selection = null;
        String[] selectionArgs = null;

        if (category != null) {
            selection = TaskContract.TaskEntry.COLUMN_CATEGORY + " = ?";
            selectionArgs = new String[]{category};
        }

        try (Cursor cursor = db.query(
                TaskContract.TaskEntry.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        )) {
            if (cursor != null && cursor.moveToFirst()) {
                do {
                    int idIndex = cursor.getColumnIndex(TaskContract.TaskEntry.COLUMN_ID);
                    int titleIndex = cursor.getColumnIndex(TaskContract.TaskEntry.COLUMN_TITLE);
                    int priorityIndex = cursor.getColumnIndex(TaskContract.TaskEntry.COLUMN_PRIORITY);
                    int statusIndex = cursor.getColumnIndex(TaskContract.TaskEntry.COLUMN_STATUS);
                    int deadlineIndex = cursor.getColumnIndex(TaskContract.TaskEntry.COLUMN_DEADLINE);
                    int categoryIndex = cursor.getColumnIndex(TaskContract.TaskEntry.COLUMN_CATEGORY);
                    int descriptionIndex = cursor.getColumnIndex(TaskContract.TaskEntry.COLUMN_DESCRIPTION);

                    if (idIndex != -1 && titleIndex != -1 && priorityIndex != -1
                            && statusIndex != -1 && deadlineIndex != -1
                            && categoryIndex != -1 && descriptionIndex != -1) {

                        CardInfo task = new CardInfo(
                                cursor.getInt(idIndex),
                                cursor.getString(titleIndex),
                                cursor.getString(priorityIndex),
                                cursor.getString(statusIndex),
                                cursor.getString(deadlineIndex),
                                cursor.getString(categoryIndex),
                                cursor.getString(descriptionIndex)
                        );
                        taskList.add(task);
                    }
                } while (cursor.moveToNext());
            }
        }

        return taskList;
    }



    public CardInfo getTaskById(int taskId) {
        SQLiteDatabase db = getReadableDatabase();

        String[] projection = {
                TaskContract.TaskEntry.COLUMN_ID,
                TaskContract.TaskEntry.COLUMN_TITLE,
                TaskContract.TaskEntry.COLUMN_PRIORITY,
                TaskContract.TaskEntry.COLUMN_STATUS,
                TaskContract.TaskEntry.COLUMN_DEADLINE,
                TaskContract.TaskEntry.COLUMN_CATEGORY,
                TaskContract.TaskEntry.COLUMN_DESCRIPTION
        };

        String selection = TaskContract.TaskEntry.COLUMN_ID + " = ?";
        String[] selectionArgs = {String.valueOf(taskId)};

        try (Cursor cursor = db.query(
                TaskContract.TaskEntry.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        )) {
            if (cursor != null && cursor.moveToFirst()) {
                int idIndex = cursor.getColumnIndex(TaskContract.TaskEntry.COLUMN_ID);
                int titleIndex = cursor.getColumnIndex(TaskContract.TaskEntry.COLUMN_TITLE);
                int priorityIndex = cursor.getColumnIndex(TaskContract.TaskEntry.COLUMN_PRIORITY);
                int statusIndex = cursor.getColumnIndex(TaskContract.TaskEntry.COLUMN_STATUS);
                int deadlineIndex = cursor.getColumnIndex(TaskContract.TaskEntry.COLUMN_DEADLINE);
                int categoryIndex = cursor.getColumnIndex(TaskContract.TaskEntry.COLUMN_CATEGORY);
                int descriptionIndex = cursor.getColumnIndex(TaskContract.TaskEntry.COLUMN_DESCRIPTION);

                if (idIndex != -1 && titleIndex != -1 && priorityIndex != -1
                        && statusIndex != -1 && deadlineIndex != -1
                        && categoryIndex != -1 && descriptionIndex != -1) {

                    return new CardInfo(
                            cursor.getInt(idIndex),
                            cursor.getString(titleIndex),
                            cursor.getString(priorityIndex),
                            cursor.getString(statusIndex),
                            cursor.getString(deadlineIndex),
                            cursor.getString(categoryIndex),
                            cursor.getString(descriptionIndex)
                    );
                }
            }
        }

        return null;
    }
}
