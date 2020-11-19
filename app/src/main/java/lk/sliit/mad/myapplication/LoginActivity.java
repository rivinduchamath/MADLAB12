package lk.sliit.mad.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import lk.sliit.mad.myapplication.database.FeedReaderContract;
import lk.sliit.mad.myapplication.database.FeedReaderDbHelper;

public class LoginActivity extends AppCompatActivity {

    private Button registerButton, btnLogin;
    private EditText etNameRegister, etPasswordRegister;
    private FeedReaderDbHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        registerButton = findViewById(R.id.btnRegisterLogin);
        btnLogin = findViewById(R.id.btnLogin);
        etNameRegister = findViewById(R.id.etNameRegister);
        etPasswordRegister = findViewById(R.id.etPasswordRegister);

        db = new FeedReaderDbHelper(this);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              String password = etPasswordRegister.getText().toString().trim();
              String name = etNameRegister.getText().toString().trim();
              if(password.equals("") || name.equals("")){
                  Toast.makeText(LoginActivity.this, "Input Empty", Toast.LENGTH_SHORT).show();
              }else {
                  Cursor cursor = db.loadSelectedData(etNameRegister.getText().toString(),etPasswordRegister.getText().toString());
                  boolean b = cursor.moveToFirst();

                  if(b){
                      String type =  cursor.getString(cursor.getColumnIndexOrThrow(FeedReaderContract.User.COLUMN_TYPE));

                      if(type.equals("Student")){
                          Intent intent = new Intent(LoginActivity.this,StudentActivity.class);
                          intent.putExtra("Name",etNameRegister.getText().toString());
                          Toast.makeText(LoginActivity.this, "Student Login Successful", Toast.LENGTH_SHORT).show();
                          startActivity(intent);

                      }else {
                          Intent intent = new Intent(LoginActivity.this,TeacherActivity.class);
                          intent.putExtra("Name",etNameRegister.getText().toString());
                          Toast.makeText(LoginActivity.this, "Teacher Login Successful", Toast.LENGTH_SHORT).show();
                          startActivity(intent);

                      }
                  }
              }


            }
        });

    }
}