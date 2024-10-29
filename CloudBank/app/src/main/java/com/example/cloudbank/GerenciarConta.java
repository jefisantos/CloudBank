package com.example.cloudbank;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.cloudbank.databinding.ActivityGerenciarContaBinding;
import com.example.cloudbank.databinding.ActivityLoginBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class GerenciarConta extends AppCompatActivity {
    ActivityGerenciarContaBinding binding;
    DatabaseReference reference;
    Button btVerConta;
    Button btAdicionarConta;
    Button btDeletarConta;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityGerenciarContaBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.btVerConta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = binding.etDigitarID.getText().toString();
                if(!id.isEmpty()){
                    lerconta(id);
                }else{
                    Toast.makeText(GerenciarConta.this,"Coloque o CPF",Toast.LENGTH_SHORT).show();
                }
            }
        });
        binding.btDeletarConta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = binding.etDigitarID.getText().toString();
                reference.child("Conta").child(id).removeValue();
                Toast.makeText(GerenciarConta.this,"Conta deletada com suceso",Toast.LENGTH_SHORT).show();
            }
        });
        btVerConta = (Button) findViewById(R.id.btVerConta);
        btAdicionarConta = (Button) findViewById(R.id.btAdicionarCompra);
        btDeletarConta = (Button) findViewById(R.id.btDeletarConta);
        reference = FirebaseDatabase.getInstance().getReference("Conta");
        btAdicionarConta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AdicionarCompra();
            }
        });
    }
    private void lerconta(String id) {
        reference = FirebaseDatabase.getInstance().getReference("Conta");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
              for (DataSnapshot snapshot1 : snapshot.getChildren()){
                  if(snapshot1.child("id").getValue().toString().equals(id)){
                      String agencia = String.valueOf(snapshot1.child("agencia").getValue());
                      String numero = String.valueOf(snapshot1.child("numero").getValue());
                      String banco = String.valueOf(snapshot1.child("banco").getValue());
                      String tipoConta = String.valueOf(snapshot1.child("tipo").getValue());
                      String cpf = String.valueOf(snapshot1.child("cpf").getValue());
                      binding.tvAgencia1.setText(agencia);
                      binding.tvNumero1.setText(numero);
                      binding.tvBanco1.setText(banco);
                      binding.tvTipoConta1.setText(tipoConta);
                      binding.tvCpfConta1.setText(cpf);
                  }
              }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    public void AdicionarCompra(){
        startActivity(new Intent(GerenciarConta.this, AdicionarCompra.class));
    }
}