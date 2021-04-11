package mbh.com.hutodolist.ui.main;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import mbh.com.hutodolist.DBHelper;

public class ToDoListViewModel extends ViewModel {
    public final static String TAG = "TodoListViewModel";
    // TODO: Implement the ViewModel
    List<Todo> todos = new ArrayList<>();
    private DBHelper dbHelper = null;     //sql

    public List<Todo> getTodos() {
        return todos;
    }
    public Todo addTodo(String todoTitle, String todoDescription, String todoDue, String type, String additional){
        Todo todo = new Todo();
        todo.setTitle(todoTitle);
        todo.setDescription(todoDescription);
        todo.setDueDate(todoDue);
        todo.setType(type);
        todo.setInformation(additional);    //additional information
        todos.add(todo);

        long todoId;
        if (dbHelper != null) {
            todoId = dbHelper.insert(todo);
            todo.setId(todoId);
        }
        return todo;
    }
    public Todo removeTodo(Todo todo){
        todos.remove(todo);
        if (dbHelper != null) {
            dbHelper.deleteRecord(todo.getId());
        }
        return todo;
    }

    public void init_database(Context context) {
        try {
            if (dbHelper == null) {
                Log.d(TAG, "init_database: DBHelper null, create one");
                dbHelper = new DBHelper(context);
                todos = dbHelper.selectAll();
            } else {
                Log.d(TAG, "init_database: DBHelper already exists");
            }
            if (!todos.isEmpty()) {
                Log.d(TAG, " animals list is not empty size: " + todos.size());
            }
        } catch (Exception e) {
            Log.d(TAG, "init_database: DBHelper threw exception : " + e);
            e.printStackTrace();
        }
    }
}