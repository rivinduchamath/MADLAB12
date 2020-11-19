package lk.sliit.mad.myapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import lk.sliit.mad.myapplication.database.FeedReaderDbHelper;

public class TeacherActivity extends AppCompatActivity {

    private Button btnSend;
    private EditText etSubjectTeacher, etSendMailTeacher;
    private TextView teacherName;
    private Intent intent;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher);

        btnSend = findViewById(R.id.btnSendMsg);
        etSendMailTeacher = findViewById(R.id.etSendMailTeacher);
        etSubjectTeacher = findViewById(R.id.etSubjectTeacher);
        teacherName = findViewById(R.id.textViewTeacher);

        intent = getIntent();
        teacherName.setText("Welcome " + intent.getStringExtra("Name"));

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FeedReaderDbHelper dbHandler = new FeedReaderDbHelper(getApplicationContext());
                long newID = dbHandler.addMessage(intent.getStringExtra("Name"), etSubjectTeacher.getText().toString(), etSendMailTeacher.getText().toString());
                if (newID > 0) {
                    Toast.makeText(TeacherActivity.this, "Message Send Success ", Toast.LENGTH_SHORT).show();

                    etSendMailTeacher.setText(null);
                    etSubjectTeacher.setText(null);
                }else {
                    Toast.makeText(TeacherActivity.this, "Message Send Fail ", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}