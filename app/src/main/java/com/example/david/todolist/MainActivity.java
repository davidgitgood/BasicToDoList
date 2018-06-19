package com.example.david.todolist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ListView taskListView;
    private List<String> tasks;
    private ArrayAdapter taskDisplayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        taskListView = findViewById(R.id.taskListView);
        tasks = new ArrayList<>();
        taskDisplayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, tasks);
        taskListView.setAdapter(taskDisplayAdapter);
        taskListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent editItem = new Intent();
                editItem.putExtra(Task_Constants.EDIT_INTENT_KEY, tasks.get(position).toString());
                editItem.putExtra(Task_Constants.INDEX_INTENT_KEY, position);
                editItem.setClass(MainActivity.this, EditEntry.class);
                startActivityForResult(editItem, Task_Constants.EDIT_ENTRY_REQUEST);
            }
        });
        taskListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                tasks.remove(position);
                taskDisplayAdapter.notifyDataSetChanged();
                return true;
            }
        });
    }

    public void addButtonClicked(View view) {
        Intent moveToEnterEntry = new Intent();
        moveToEnterEntry.setClass(this, EnterEntry.class);
        startActivityForResult(moveToEnterEntry, Task_Constants.ENTER_ENTRY_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == Task_Constants.ENTER_ENTRY_RESULT) {
            String task = data.getStringExtra(Task_Constants.TASK_INTENT_KEY);
            tasks.add(task);
        }
        else if (requestCode == Task_Constants.EDIT_ENTRY_RESULT) {
            String task = data.getStringExtra(Task_Constants.EDITED_INTENT_KEY);
            int index = data.getIntExtra(Task_Constants.INDEX_INTENT_KEY, -1);
            tasks.remove(index);
            tasks.add(index, task);
        }
        taskDisplayAdapter.notifyDataSetChanged();
    }
}
