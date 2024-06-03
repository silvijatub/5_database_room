package com.example.a5_database;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.List;


public class MainActivity extends AppCompatActivity {

    private AppDatabase db;
    private TextView personsListTextView;
    private Button button, deleteBtn;
    private EditText firstNameEditText;
    private EditText lastNameEditText;
    private EditText phoneNumberEditText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = AppActivity.getDatabase();
        personsListTextView = (TextView) findViewById(R.id.txt_list);
        firstNameEditText = (EditText) findViewById(R.id.edittextname);
        lastNameEditText = (EditText) findViewById(R.id.edittext_surname);
        phoneNumberEditText = (EditText) findViewById(R.id.edittext_phone);
        button = (Button) findViewById(R.id.button);
        deleteBtn = (Button) findViewById(R.id.buttonDelete);

        button.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_save, 0, 0, 0);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = firstNameEditText.getText().toString().trim();
                String surname = lastNameEditText.getText().toString().trim();
                String phoneNumber = phoneNumberEditText.getText().toString().trim();
                if (TextUtils.isEmpty(name) || TextUtils.isEmpty(surname)
                        || TextUtils.isEmpty (phoneNumber)) {
                    Toast.makeText(getApplicationContext(),"Name/Surname/Phone Number should not be empty",
                            Toast.LENGTH_SHORT).show();
                } else {
                    Person person = new Person();
                    person.setName(name);
                    person.setSurname (surname);
                    person.setPhoneNumber(phoneNumber);
                    db.personDAO().insert(person);
                    Toast.makeText(
                            getApplicationContext(), "Saved successfully",
                            Toast.LENGTH_SHORT).show();
                    firstNameEditText.setText("");
                    lastNameEditText.setText("");
                    phoneNumberEditText.setText("");
                    firstNameEditText.requestFocus();
                    getPersonList();
                }
            }
        });

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.personDAO().deleteLastRecord();
                getPersonList();

            }
        });
    }

    private void getPersonList(){
        personsListTextView.setText("");
        List<Person> personList = db.personDAO().getAllPersons();
        for(Person person:personList){
            personsListTextView.append(person.getName() + " " +
                    person.getSurname() +
                    " : " + person.getPhoneNumber());
            personsListTextView.append("\n");
        }
    }

}