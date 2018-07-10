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

public class MainActivity extends AppCompatActivity {

    Spinner stateField;
    EditText districtField;
    Button searchField;
    final int REQUEST_CODE = 6;

    String states[] = {"Andaman and Nicobar Islands","Andhra Pradesh","Arunachal Pradesh","Assam","Bihar",
            "Chandigarh","Chhattisgarh","Dadra and Nagar Haveli","Daman and Diu",
            "National Capital Territory of Delhi","Goa","Gujarat","Haryana","Himachal Pradesh","Jammu and Kashmir",
            "Jharkhand","Karnataka","Kerala","Lakshadweep","Madhya Pradesh","Maharashtra","Manipur","Meghalaya",
            "Mizoram","Nagaland","Odisha","Puducherry","Punjab","Rajasthan","Sikkim","Tamil Nadu",
            "Telangana","Tripura","Uttar Pradesh","Uttarakhand",
            "West Bengal"};

    String state,district;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        stateField= (Spinner) findViewById(R.id.spinner);
        districtField= (EditText) findViewById(R.id.editText);
        searchField = (Button) findViewById(R.id.search);


        ArrayAdapter adapter1 = new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,states);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        stateField.setAdapter(adapter1);
        stateField.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                state =states[position];

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });



        searchField.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               district=districtField.getText().toString().trim();


                if(district=="")
                {

                    Toast.makeText(MainActivity.this, "Please enter a district name", Toast.LENGTH_SHORT).show();
                }
                else{
                    Intent intent = new Intent(MainActivity.this,AddValueActivity.class);
                    intent.putExtra("state" , state);
                    intent.putExtra("district" , district);
                    startActivityForResult(intent , REQUEST_CODE);
                    startActivity(intent);
                }
            }
        });

    }
}
