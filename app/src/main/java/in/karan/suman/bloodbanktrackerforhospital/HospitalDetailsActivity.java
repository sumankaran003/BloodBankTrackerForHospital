package in.karan.suman.bloodbanktrackerforhospital;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class HospitalDetailsActivity extends AppCompatActivity {

    EditText namef,phonef,latf,lonf,addf;
    String name,phone,lat,lon,add;
    Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital_details);

        namef=findViewById(R.id.name);
        phonef=findViewById(R.id.phone);
        latf=findViewById(R.id.lat);
        lonf=findViewById(R.id.lon);
        addf=findViewById(R.id.add);

        submit=findViewById(R.id.submit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sub();



            }
        });



    }

    private void sub() {

        name=namef.getText().toString().trim();
        phone=phonef.getText().toString().trim();
        lat=latf.getText().toString().trim();
        lon=lonf.getText().toString().trim();
        add=addf.getText().toString().trim();

        final String str=name;

        if(!TextUtils.isEmpty(name) && !TextUtils.isEmpty(phone) && !TextUtils.isEmpty(lat) && !TextUtils.isEmpty(lon) && !TextUtils.isEmpty(add))

        {
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference mDatabaseReference = database.getReference(str);

        mDatabaseReference.child("phone").setValue(phone);
        mDatabaseReference.child("lat").setValue(lat);
        mDatabaseReference.child("lon").setValue(lon);
        mDatabaseReference.child("add").setValue(add);
            Toast.makeText(HospitalDetailsActivity.this,"Data saved successfully",Toast.LENGTH_LONG).show();}
        else{
            Toast.makeText(HospitalDetailsActivity.this,"Please fill the details",Toast.LENGTH_LONG).show();
        }

    }
}
