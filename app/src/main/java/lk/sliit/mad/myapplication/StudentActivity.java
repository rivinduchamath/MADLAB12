package lk.sliit.mad.myapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import lk.sliit.mad.myapplication.database.FeedReaderDbHelper;
import lk.sliit.mad.myapplication.entity.Message;

public class StudentActivity extends AppCompatActivity {

    ListView listView;
    TextView textViewStudent;
    private Intent intent;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);
        listView = findViewById(R.id.listViewMessage);
        textViewStudent = findViewById(R.id.textViewStudent);

        intent = getIntent();
        textViewStudent.setText("Welcome " + intent.getStringExtra("Name"));

        FeedReaderDbHelper dbHandler = new FeedReaderDbHelper(getApplicationContext());

        ArrayAdapter<List> arrayAdapter =  new ArrayAdapter<List>(this, android.R.layout.simple_list_item_1,dbHandler.loadMessageData());
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String pos =  listView.getItemAtPosition(position).toString();
                Intent intent = new Intent(StudentActivity.this, MessageView.class);
                intent.putExtra("Subject",pos);
                startActivity(intent);
            }
        });

    }
}