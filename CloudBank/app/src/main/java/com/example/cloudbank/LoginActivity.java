package com.example.cloudbank;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.DragAndDropPermissions;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import com.example.cloudbank.databinding.ActivityLoginBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LoginActivity extends AppCompatActivity {
    //FirebaseAuth mAuth;
    ActivityLoginBinding binding;
    DatabaseReference reference;
    Button btAlterar;
    Button btBuscarCpf;
    Button btDeletar;
    Button btCriarConta;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.btBuscacpf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cpf = binding.etLercpf.getText().toString();
                if(!cpf.isEmpty()){
                    lercpf(cpf);
                }else{
                    Toast.makeText(LoginActivity.this,"Coloque o CPF",Toast.LENGTH_SHORT).show();
                }
            }
        });
        binding.btDeletar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cpf = binding.etLercpf.getText().toString();
                reference.child(cpf).removeValue();
                Toast.makeText(LoginActivity.this,"Conta deletada com suceso",Toast.LENGTH_SHORT).show();
            }
        });
        btAlterar = (Button) findViewById(R.id.btAlterar);
        btCriarConta = (Button) findViewById(R.id.btCriarConta);
        btBuscarCpf = (Button) findViewById(R.id.btBuscacpf);
        btDeletar = (Button) findViewById(R.id.btDeletar);
        reference = FirebaseDatabase.getInstance().getReference("Usuario");
        btAlterar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlterarActivity();
            }
        });
        btCriarConta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { EscolherConta(); }
        });

    }

    private void lercpf(String cpf) {
        reference = FirebaseDatabase.getInstance().getReference("Usuario");
        reference.child(cpf).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if(task.isSuccessful()){

                    if(task.getResult().exists()){
                        Toast.makeText(LoginActivity.this,"Sucesso ao achar CPF",Toast.LENGTH_SHORT).show();
                        DataSnapshot dataSnapshot = task.getResult();
                        String nome = String.valueOf(dataSnapshot.child("etNome").getValue());
                        String email = String.valueOf(dataSnapshot.child("etEmail").getValue());
                        String endereco = String.valueOf(dataSnapshot.child("etEndereco").getValue());
                        binding.tvEmail.setText(email);
                        binding.tvEndereco.setText(endereco);
                        binding.Nome.setText(nome);
                    }else{
                        Toast.makeText(LoginActivity.this, "usuario nao existe",Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(LoginActivity.this, "Falha ao achar CPF",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void AlterarActivity(){
        startActivity(new Intent(LoginActivity.this, AlterarActivity.class));
    }
    public void EscolherConta(){
        startActivity(new Intent(LoginActivity.this, EscolherConta.class));
    }
}