package com.example.david.todolist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class EditEntry extends AppCompatActivity {
    String task;
    int pos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_entry);
        Intent intent = getIntent();
        task = intent.getStringExtra(Task_Constants.EDIT_INTENT_KEY);
        pos = intent.getIntExtra(Task_Constants.INDEX_INTENT_KEY, -1);
        EditText taskBox = findViewById(R.id.toDoEditText);
        taskBox.setText(task);
    }

    public void saveButtonClicked(View view) {
        String task = ((EditText)findViewById(R.id.toDoEditText)).getText().toString();
        Intent returnToMain = new Intent();
        returnToMain.putExtra(Task_Constants.EDITED_INTENT_KEY, task);
        returnToMain.putExtra(Task_Constants.INDEX_INTENT_KEY, pos);
        setResult(Task_Constants.EDIT_ENTRY_RESULT, returnToMain);
        finish();

    }
}
