package lk.sliit.mad.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import lk.sliit.mad.myapplication.database.FeedReaderContract;
import lk.sliit.mad.myapplication.database.FeedReaderDbHelper;

public class MessageView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_view);

        Intent intent =  getIntent();
        String subject =  intent.getStringExtra("Subject");
        FeedReaderDbHelper db  = new FeedReaderDbHelper(this);
        String message = db.loadSelectedMsg(subject);

        TextView sub = findViewById(R.id.textView2);
        sub.setText(subject);

        TextView msg =  findViewById(R.id.textView3);
        msg.setText(message);
    }
}