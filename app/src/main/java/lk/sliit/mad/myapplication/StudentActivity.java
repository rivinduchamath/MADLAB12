package lk.sliit.mad.myapplication;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import lk.sliit.mad.myapplication.database.FeedReaderDbHelper;
import lk.sliit.mad.myapplication.entity.Message;

public class StudentActivity extends AppCompatActivity {

    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);
        listView = findViewById(R.id.listViewMessage);

        FeedReaderDbHelper dbHandler = new FeedReaderDbHelper(getApplicationContext());


        ArrayAdapter<List> arrayAdapter =  new ArrayAdapter<List>(this, android.R.layout.simple_list_item_1,dbHandler.loadMessageData());


        listView.setAdapter(arrayAdapter);


    }
}