package lk.sliit.mad.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class TeacherActivity extends AppCompatActivity {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher);

        TextView teacherName = findViewById(R.id.textViewTeacher);
        Intent intent = getIntent();
        teacherName.setText("Welcome "+intent.getStringExtra("Name"));

    }
}