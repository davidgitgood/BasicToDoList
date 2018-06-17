package com.example.david.todolist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class EnterEntry extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_entry);
    }

    public void saveButtonClicked(View view) {
        String task = ((EditText)findViewById(R.id.toDoEditText)).getText().toString();
        if (task.length() > 0) {
            Intent returnToMain = new Intent();
            returnToMain.putExtra(Task_Constants.TASK_INTENT_KEY, task);
            setResult(Task_Constants.ENTER_ENTRY_RESULT, returnToMain);
        }
        finish();
    }
}
