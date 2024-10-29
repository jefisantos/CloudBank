package com.example.cloudbank;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.cloudbank.databinding.ActivityMainBinding;
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

public class MainActivity extends AppCompatActivity {
    Button btRegistro;
    Button btLogin;
    EditText etNome, etCpf, etEmail, etNomePai, etNomeMae , etEndereco, etSenha1;
    Usuario u;
    ActivityMainBinding binding;
    DatabaseReference reference;
    DatabaseReference ref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        etEmail = findViewById(R.id.etEmail);
        etSenha1 = findViewById(R.id.etSenha1);
        btRegistro = (Button) findViewById(R.id.btRegistro);
        btLogin = (Button) findViewById(R.id.btLogin);
        reference = FirebaseDatabase.getInstance().getReference("Usuario");
        btRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RegistrarActivity();
            }
        });
        binding.btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = binding.etEmail.getText().toString();
                String senha = binding.etSenha.getText().toString();
                if(!email.isEmpty() && !senha.isEmpty()){
                    logar(email,senha);
                }else{
                    Toast.makeText(MainActivity.this,"Coloque o E-mail e a Senha",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void logar(String email , String senha){

        reference = FirebaseDatabase.getInstance().getReference("Usuario");
        reference.child(email).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if(task.isSuccessful()){
                    if(task.getResult().exists()){
                        Toast.makeText(MainActivity.this,"Sucesso ao achar conta",Toast.LENGTH_SHORT).show();
                        DataSnapshot dataSnapshot = task.getResult();
                        String senha1 = String.valueOf(dataSnapshot.child("etSenha1").getValue());
                        if(senha1.equals(senha)){
                            startActivity(new Intent(MainActivity.this, LoginActivity.class));
                        }else {
                            Toast.makeText(MainActivity.this, "Senha incorreta", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(MainActivity.this, "Usuario nao existe",Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(MainActivity.this, "Falha ao achar conta",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void RegistrarActivity(){
        startActivity(new Intent(MainActivity.this, Registrar.class));
    }
}
