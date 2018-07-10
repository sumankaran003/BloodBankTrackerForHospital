package in.karan.suman.bloodbanktrackerforhospital;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AddValueActivity extends AppCompatActivity {
    String bg;String state,district;
    int count;

    Spinner bgr;
    Button addone;
    Button subone;
    EditText hos;
    String hospital;
    String grp[] = {"A+","A-","B+","B-","O+","O-","AB+","AB-"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_value);

        Intent box = getIntent();
        Bundle b = box.getExtras();
        state=b.getString("state");
        district=b.getString("district");

        bgr= (Spinner) findViewById(R.id.spinner1);


        ArrayAdapter adapter1 = new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,grp);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        bgr.setAdapter(adapter1);
        bgr.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                bg =grp[position];

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });

        hos= (EditText) findViewById(R.id.editText);


        addone= (Button) findViewById(R.id.add);
        subone= (Button) findViewById(R.id.sub);

        addone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                add();

            }
        });

        subone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sub();
            }
        });




    }

    private void sub() {

        hospital=hos.getText().toString().trim();

        final String str=state+"/"+district+"/"+bg+"/"+hospital;


        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference mDatabaseReference = database.getReference(str);


        mDatabaseReference.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for(DataSnapshot data : dataSnapshot.getChildren()){


                     count = data.getValue(Integer.class);



                    if(count==0){

                        Toast.makeText(AddValueActivity.this,"There is no more blood of this type",Toast.LENGTH_LONG).show();
                        count=0;

                    }
                    else{
                        count=count-1;
                    }

                    // use this object and store it into an ArrayList<Template> to use it further

                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        mDatabaseReference.child("count").setValue(count);
        count=0;



    }

    private void add() {

        hospital=hos.getText().toString().trim();

        final String str=state+"/"+district+"/"+bg+"/"+hospital;
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference mDatabaseReference = database.getReference(str);


        mDatabaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for(DataSnapshot data : dataSnapshot.getChildren()){



                   count = data.getValue(Integer.class);
                    // use this object and store it into an ArrayList<Template> to use it further

                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        mDatabaseReference.child("count").setValue(++count);
        count=0;



    }
}
