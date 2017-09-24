package com.gmail.ioanna.data.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.gmail.ioanna.data.dbEntity.Task;

import java.util.ArrayList;
import java.util.List;


public class DatabaseManager {

    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String PERCENT_OF_COMPLETION = "percentOfCompletion";
    private static final String STATE = "state";
    private static final String ESTIMATED_TIME = "estimatedTime";
    private static final String START_TIME = "startDate";
    private static final String DUE_TIME = "dueDate";

    private static DatabaseManager instance;

    private Context context;
    private DBHelper dbHelper;
    private SQLiteDatabase database;

    //Singleton
    public static DatabaseManager getInstance(Context context){
        if(instance == null){
            instance = new DatabaseManager(context);
        }
        return instance;
    }

    private DatabaseManager(Context context){
        this.context = context;
        dbHelper = new DBHelper(context);
    }

    public void open(boolean isWritable){
        if(isWritable){
            database = dbHelper.getWritableDatabase();
        } else{
            database = dbHelper.getReadableDatabase();
        }
    }

    public void close(){
        if(database != null){
            database.close();
        }
    }

    public void insertTask(Task task){

        database.beginTransaction();
        try {
            StringBuilder sql = new StringBuilder();

            //INSERT INTO tasks('id', 'name', 'percentOfCompletion', 'state', 'estimatedTime', 'startDate', 'dueDate') VALUES (1, 'name', 100, 'New', 90, 2017-08-12, 2017-10-20)
            sql.append("INSERT INTO newTasks ('id', 'name', 'percentOfCompletion', 'state', 'estimatedTime', " +
                    "'startDate', 'dueDate') ");
            sql.append("VALUES (");
            sql.append("'");
            sql.append(task.getId());
            sql.append("', '");
            sql.append(task.getName());
            sql.append("', ");
            sql.append(task.getPercentOfCompletion());
            sql.append(", '");
            sql.append(task.getState());
            sql.append("', ");
            sql.append(task.getEstimatedTime());
            sql.append(", '");
            sql.append(task.getStartDate());
            sql.append("', '");
            sql.append(task.getDueDate());
            sql.append("')");

            Log.e("DataBaseManager", "insertUser() sql = " + sql.toString());
            database.execSQL(sql.toString());
            database.setTransactionSuccessful();
        }finally {
            database.endTransaction();
        }

    }

    public int updateTask(Task task){

        //database.beginTransaction();
        try {
            ContentValues values = new ContentValues();
            values.put(NAME, task.getName());
            values.put(PERCENT_OF_COMPLETION, task.getPercentOfCompletion());
            values.put(STATE, task.getState());
            values.put(ESTIMATED_TIME, task.getEstimatedTime());
            values.put(START_TIME, task.getStartDate());
            values.put(DUE_TIME, task.getDueDate());
            return database.update("newTasks", values, ID + " = ?",
                    new String[]{String.valueOf(task.getId())});
        }catch(Exception e){
            return 100;
        }
        /*finally {
            database.endTransaction();
        }*/
    }

    public List<Task> getTasks(){

        List<Task> listTasks = new ArrayList<>();

        // Зададим условие для выборки - список столбцов
        String[] projection = {ID, NAME, PERCENT_OF_COMPLETION, STATE, ESTIMATED_TIME,
                START_TIME, DUE_TIME};

        // Делаем запрос
        Cursor cursor = database.query(
                "newTasks",   // таблица
                projection,            // столбцы
                null,                  // столбцы для условия WHERE
                null,                  // значения для условия WHERE
                null,                  // Don't group the rows
                null,                  // Don't filter by row groups
                null);                   // порядок сортировки

        try {
            Log.e("getTasks()","Таблица содержит " + cursor.getCount() + " гостей.\n\n");

            // Узнаем индекс каждого столбца
            int idColumnIndex = cursor.getColumnIndex(ID);
            int nameColumnIndex = cursor.getColumnIndex(NAME);
            int percentOfCompletionColumnIndex = cursor.getColumnIndex(PERCENT_OF_COMPLETION);
            int stateColumnIndex = cursor.getColumnIndex(STATE);
            int estimatedTimeColumnIndex = cursor.getColumnIndex(ESTIMATED_TIME);
            int startDateColumnIndex = cursor.getColumnIndex(START_TIME);
            int dueDateColumnIndex = cursor.getColumnIndex(DUE_TIME);

            // Проходим через все ряды
            while (cursor.moveToNext()) {
                // Используем индекс для получения строки или числа
                int currentId = cursor.getInt(idColumnIndex);
                String currentName = cursor.getString(nameColumnIndex);
                String currentState = cursor.getString(stateColumnIndex);
                int currentPercentOfCompletion = cursor.getInt(percentOfCompletionColumnIndex);
                int currentEstimatedTime = cursor.getInt(estimatedTimeColumnIndex);
                String currentStartDate = cursor.getString(startDateColumnIndex);
                String currentDueDate = cursor.getString(dueDateColumnIndex);
                Log.e("getTasks", currentName + " " + currentState + " " + currentPercentOfCompletion
                + " " + currentEstimatedTime + " " + currentStartDate + " " + currentDueDate);
                listTasks.add(new Task(currentId, currentName, currentPercentOfCompletion,
                        currentState, currentEstimatedTime, currentStartDate,currentDueDate));
            }
        } finally {
            // Всегда закрываем курсор после чтения
            cursor.close();
        }
        return listTasks;
    }

    public Task getTaskById(int id){

        /*Cursor cursor = database.rawQuery("SELECT * FROM user INNER JOIN country ON " +
                        "user.countryId = country.id WHERE id = ?",
                new String[]{String.valueOf(id)});*/

        Cursor cursor = database.rawQuery("SELECT * FROM newTasks WHERE id = " + id, null);

        if(cursor != null){

            Task task = new Task();

            //INSERT INTO tasks('id', 'name', 'percentOfCompletion', 'state', 'estimatedTime', 'startDate', 'dueDate') VALUES (1, 'name', 100, 'New', 90, 2017-08-12, 2017-10-20)
            //вытягиваем данные из Cursor
            cursor.moveToFirst();
            int taskId = cursor.getInt(0);
            String name = cursor.getString(1);
            int percentOfCompletion = cursor.getInt(2);
            String state = cursor.getString(3);
            int estimatedTime = cursor.getInt(4);
            String startDate = cursor.getString(5);
            String dueDate = cursor.getString(6);

            //заполняем объект Task
            task.setId(taskId);
            task.setName(name);
            task.setPercentOfCompletion(percentOfCompletion);
            task.setState(state);
            task.setEstimatedTime(estimatedTime);
            task.setStartDate(startDate);
            task.setDueDate(dueDate);

            return task;

        }else{
            Log.e("DatabaseManager","getTaskById() cursor is null");
        }
        return null;
    }

}
