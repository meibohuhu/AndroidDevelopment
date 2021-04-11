package mbh.com.hutodolist.ui.main;

import androidx.lifecycle.ViewModelProviders;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.TextUtils;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import mbh.com.hutodolist.R;

public class ToDoListFragment extends Fragment implements
        TodoRecyclerViewAdapter.OnAdapterItemInteraction {

    private ToDoListViewModel mViewModel;
    public final static String TAG = "TodoList";
    TodoRecyclerViewAdapter adapter;

    public static ToDoListFragment newInstance() {
        return new ToDoListFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        getActivity().getWindow().
                setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.todo_list_fragment, container, false);
    }

    @Override
    public void onItemSelected(Todo todo) {     //display
        onDisplay(todo);
    }

    @Override
    public void onItemLongClick(Todo todo) {    //delete
        onDelete(todo);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(ToDoListViewModel.class);
        mViewModel.init_database(getActivity());     //sql

        // TODO: Use the ViewModel
        // Instantiate the recyclerView
        RecyclerView recyclerView = getActivity().findViewById(R.id.todo_items);    //in todo_list_fragment.xml
        // Instantiate the layoutManager and set it into the recyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new TodoRecyclerViewAdapter(mViewModel.getTodos(), this);
        recyclerView.setAdapter(adapter);

        Button saveButton = getActivity().findViewById(R.id.todo_saveButton);
        //Instantiate the button and the click listener.
        saveButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                onSave();
            }
        });
    }

    //This method checks that the user has entered an animal name and then creates a new Todo and fills it with the information from the EditTexts and the selected RadioButton.
    private void onSave(){
        EditText title = getActivity().findViewById(R.id.todo_title);
        String todoTitle = title.getText().toString();
        EditText dueDate = getActivity().findViewById(R.id.todo_due);
        String todoDueDate = dueDate.getText().toString();

    // Don’t enter info which does not contain mandatory items
        if (TextUtils.isEmpty(todoTitle) || TextUtils.isEmpty(todoDueDate)) {
            showMissingInfoAlert();
            if (TextUtils.isEmpty(todoTitle))  Log.d(TAG, "Missing Todo title");
            if (TextUtils.isEmpty(todoDueDate)) Log.d(TAG,"Missing Todo Due Date");
        } else {

            EditText description = getActivity().findViewById(R.id.todo_description);
            String todoDescription = description.getText().toString();
            RadioGroup types = getActivity().findViewById(R.id.todo_type);
            EditText additional = getActivity().findViewById(R.id.todo_additional);
            String todoAdditional = additional.getText().toString();

            String type = "rock";
            switch (types.getCheckedRadioButtonId()) {
                case R.id.todo_typeLife:
                    type = Todo.LIFE;
                    break;
                case R.id.todo_typeEntertainment:
                    type = Todo.ENTERTAINMENT;
                    break;
                case R.id.todo_typeStudy:
                    type = Todo.STUDY;
                    break;
            }

            // Add the object at the end of the array.
            mViewModel.addTodo(todoTitle, todoDescription, todoDueDate, type, todoAdditional);
            // Notifies the adapter that the underlying data has changed, // any View reflecting the data should refresh itself.
            adapter.notifyDataSetChanged();
            // Remove the soft keyboard after hitting the save button
            InputMethodManager inputManager = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            inputManager.hideSoftInputFromWindow(getActivity().
                            getCurrentFocus().getWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    //create a delete method
    private void onDelete(Todo todo){
// When clicked, delete the item that was clicked. // (Show a toast to indicate what is occurring)
        if (todo != null) {
            String item = "deleting: " + todo.getTitle();
            Toast.makeText(getActivity(), item, Toast.LENGTH_SHORT).show();
            Log.d(TAG, " onItemClick: " + todo.getTitle());
// Removes the object from the array held in the viewModel
            mViewModel.removeTodo(todo);
// Notifies that the underlying data has changed
            adapter.notifyDataSetChanged();
        }
    }

    //create a display methods

    //Alert
    public void showMissingInfoAlert() {
//        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
        ContextThemeWrapper ctw =
                new ContextThemeWrapper(getActivity(), R.style.AlertDialogCustom );
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(ctw);

        alertDialogBuilder.setTitle(getResources().getString(R.string.alert_info));
        alertDialogBuilder.setIcon(android.R.drawable.ic_dialog_alert);
        // set dialog message
        alertDialogBuilder.setMessage(getResources().getString(R.string.alert_message)).setCancelable(false)
                .setPositiveButton("OK",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        // if this button is clicked, close current activity
                        dialog.cancel();
                    }
                });
        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();
        // show it
        alertDialog.show();
    }

    //Alert
    public void onDisplay(Todo todo) {
//        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
        ContextThemeWrapper ctw =
                new ContextThemeWrapper(getActivity(), R.style.AlertDialogCustom);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());

        alertDialogBuilder.setTitle(getResources().getString(R.string.alert_info));
        alertDialogBuilder.setIcon(android.R.drawable.ic_dialog_alert);
        // set dialog message
        String info = todo.getInformation();
        alertDialogBuilder.setMessage(info).setCancelable(false)
                .setPositiveButton("OK",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // if this button is clicked, close current activity
                        dialog.cancel();
                    }
                });
        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();
        // show it
        alertDialog.show();
    }



}