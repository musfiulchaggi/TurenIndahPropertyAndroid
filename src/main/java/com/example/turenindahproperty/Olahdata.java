package com.example.turenindahproperty;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Olahdata extends AppCompatActivity {
    final String message = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        EditText etNama, etLt, etLb, etKm, etKt, etAlamat, etLong, etLat, etDeskripsi, etAgentName, etAgentPhone, etAgentEmail, etAgentWhatsApp, etImage;
        Button btnSave;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_olahdata);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference getReference;
        getReference = database.getReference();

        btnSave = findViewById(R.id.btnSave);
        etNama = findViewById(R.id.etNama);
        etLt = findViewById(R.id.etLt);
        etLb = findViewById(R.id.etLb);
        etKm = findViewById(R.id.etKm);
        etKt = findViewById(R.id.etKt);
        etAlamat = findViewById(R.id.etAlamat);
        etLong = findViewById(R.id.etLong);
        etLat = findViewById(R.id.etLat);
        etDeskripsi = findViewById(R.id.etDeskripsi);
        etAgentName = findViewById(R.id.etAgentName);
        etAgentEmail = findViewById(R.id.etAgentEmail);
        etAgentPhone = findViewById(R.id.etAgentPhone);
        etAgentWhatsApp = findViewById(R.id.etAgentWA);
        etImage = findViewById(R.id.etImage);

        btnSave.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String name = etNama.getText().toString();
                int lt, lb, km, kt;
                lt = lb = km = kt = 0;
                if(!isEmpty(etLt))
                    lt = Integer.parseInt(etLt.getText().toString());
                if(!isEmpty(etLb))
                    lb = Integer.parseInt(etLb.getText().toString());
                if(!isEmpty(etKm))
                    km = Integer.parseInt(etKm.getText().toString());
                if(!isEmpty(etKt))
                    kt = Integer.parseInt(etKt.getText().toString());
                String alamat = etAlamat.getText().toString();
                String maplong = etLong.getText().toString();
                String maplat = etLat.getText().toString();
                String deskripsi = etDeskripsi.getText().toString();
                String agent_name = etAgentName.getText().toString();
                String agent_phone = etAgentPhone.getText().toString();
                String agent_email = etAgentEmail.getText().toString();
                String agent_whatsapp = etAgentWhatsApp.getText().toString();
                String image = etImage.getText().toString();

                if(name.equals("")){
                    Toast.makeText(Olahdata.this, "Anda Harus Mengisi Nama", Toast.LENGTH_LONG).show();
                }else{
                    getReference.child("Property").push()
                            .setValue(new data_property(
                                    name, lt, lb, km, kt, alamat, maplong, maplat, deskripsi, agent_name, agent_email, agent_phone, agent_whatsapp, image
                            )).addOnSuccessListener(Olahdata.this, new OnSuccessListener() {
                                @Override
                                public void onSuccess(Object o) {
                                    Toast.makeText(Olahdata.this, "Sukses Mengisi Data", Toast.LENGTH_LONG).show();
                                }
                            });
                }
            }
        });

    }
    private boolean isEmpty(EditText etText) {
        if (etText.getText().toString().trim().length() > 0)
            return false;

        return true;
    }
}