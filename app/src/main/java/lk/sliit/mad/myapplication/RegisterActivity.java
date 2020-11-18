package lk.sliit.mad.myapplication;



import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;


import org.w3c.dom.ls.LSOutput;

import lk.sliit.mad.myapplication.database.FeedReaderDbHelper;

public class RegisterActivity extends AppCompatActivity {

    private Button btnRegister;
    private EditText etName,etPassword;
    private RadioButton rStudent,rTeacher;
    private String type;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        btnRegister = findViewById(R.id.btnRegister);
        etName = findViewById(R.id.etNameRegister);
        etPassword = findViewById(R.id.etPasswordRegister);
        rStudent = findViewById(R.id.radioButtonStudent);
        rTeacher = findViewById(R.id.radioButtonTeacher);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rStudent.isChecked()) {
                    type = "Student";
                } else {
                    type = "Teacher";
                }


                FeedReaderDbHelper dbHandler = new FeedReaderDbHelper(getApplicationContext());
                long newID = dbHandler.addUser(etName.getText().toString(), etPassword.getText().toString(), type);


                Toast.makeText(RegisterActivity.this, "User Added. User ID: " + newID, Toast.LENGTH_SHORT).show();

                Intent i = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(i);
                etName.setText(null);
                etPassword.setText(null);
                rStudent.setChecked(true);
                rTeacher.setChecked(false);
            }
        });
    }
}