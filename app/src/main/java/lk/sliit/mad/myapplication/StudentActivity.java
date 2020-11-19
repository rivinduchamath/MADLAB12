package lk.sliit.mad.myapplication;

import android.content.Intent;
import android.os.Bundle;
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);
        listView = findViewById(R.id.listViewMessage);
        textViewStudent = findViewById(R.id.textViewStudent);

        intent = getIntent();
        textViewStudent.setText("Welcome " + intent.getStringExtra("Name"));

        FeedReaderDbHelper dbHandler = new FeedReaderDbHelper(getApplicationContext());

        List<Message> m = dbHandler.loadMessageData();
        List<Message> ma = new ArrayList<>();

        for (Message d: m) {
          String mss = d.getMessage();
          String sub  = d.getSubject();
           ma.add(new Message(
                   mss,
                   sub
           ));

        }

        ArrayAdapter<List> arrayAdapter =  new ArrayAdapter<List>(this, android.R.layout.simple_list_item_1,dbHandler.loadMessageData());
        listView.setAdapter(arrayAdapter);


    }
}