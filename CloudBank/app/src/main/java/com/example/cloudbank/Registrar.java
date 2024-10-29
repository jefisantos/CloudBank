package com.example.cloudbank;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Registrar extends AppCompatActivity {
    FirebaseAuth mAuth;
    private EditText etNome, etCpf, etEmail, etNomePai, etNomeMae , etEndereco, etSenha1;
    Button btRegistrar;
    Button btVoltar;
    FirebaseDatabase database;
    DatabaseReference ref;
    private Usuario u;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);

        mAuth = FirebaseAuth.getInstance();
        etNome = (EditText) findViewById(R.id.etNome);
        etCpf = (EditText) findViewById(R.id.etCpf);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etNomePai = (EditText) findViewById(R.id.etNomePai);
        etNomeMae = (EditText) findViewById(R.id.etNomeMae);
        etEndereco = (EditText) findViewById(R.id.etEndereco);
        etSenha1 = (EditText) findViewById(R.id.etSenha1);
        btVoltar = (Button) findViewById(R.id.btVoltar);
        btRegistrar = (Button) findViewById(R.id.btnRegistrar);
        btVoltar.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VoltarActivity();
            }
        }));
        btRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnRegistrar();
                LoginActivity();
            }
        });
        database = FirebaseDatabase.getInstance();
        ref = database.getReference("Usuario");
        u = new Usuario();

    }

    private void getValues(){
        u.setEtNome(etNome.getText().toString());
        u.setEtCpf(etCpf.getText().toString());
        u.setEtEmail(etEmail.getText().toString());
        u.setEtNomePai(etNomePai.getText().toString());
        u.setEtNomeMae(etNomeMae.getText().toString());
        u.setEtEndereco(etEndereco.getText().toString());
        u.setEtSenha1(etSenha1.getText().toString());
    }

        public void VoltarActivity(){
        startActivity(new Intent(Registrar.this, MainActivity.class));
        }
        public void LoginActivity(){
        startActivity(new Intent(Registrar.this, LoginActivity.class));
        }
        public void btnRegistrar(){
            ref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot snapshot) {
                    getValues();
                    if(etCpf.length()==11 && etNome.length() > 2 && etEmail.length() > 7 && etSenha1.length() > 3) {
                        ref.child(etCpf.getText().toString()).setValue(u);
                        Toast.makeText(Registrar.this, "Dados inseridos com sucesso", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(Registrar.this, "CPF , Nome , Email e senha são obrigatorios", Toast.LENGTH_SHORT).show();
                    }

                }

                @Override
                public void onCancelled(DatabaseError error) {

                }
            });
        }
}