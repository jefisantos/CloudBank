package com.example.cloudbank;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.cloudbank.databinding.ActivityEscolherContaBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Random;

public class EscolherConta extends AppCompatActivity {
    ActivityEscolherContaBinding binding;
    DatabaseReference ref;
    DatabaseReference reference;
    FirebaseDatabase database;
    Button btContaCorrente;
    Button btContaPoupanca;
    private EditText etVerifCpf;
    private Conta conta;
    public double maxid;
    long maxid1 = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEscolherContaBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        etVerifCpf = (EditText) findViewById(R.id.etVerifCpf);
        btContaPoupanca = (Button) findViewById(R.id.btContaPoupanca);
        database = FirebaseDatabase.getInstance();
        ref = FirebaseDatabase.getInstance().getReference().child("Conta");
        conta = new Conta();
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    maxid1=(snapshot.getChildrenCount());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        binding.btContaPoupanca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cpf = binding.etVerifCpf.getText().toString();
                if(!cpf.isEmpty()){
                    getValuesPoupanca();
                    ref.child(String.valueOf(maxid1+1)).setValue(conta);
                    GerenciarConta();
                }else{
                    Toast.makeText(EscolherConta.this,"Coloque o CPF",Toast.LENGTH_SHORT).show();
                }
            }
        });
        binding.btContaCorrente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cpf = binding.etVerifCpf.getText().toString();
                if(!cpf.isEmpty()){
                    getValuesCorrente();
                    ref.child(String.valueOf(maxid1+1)).setValue(conta);
                    GerenciarConta();
                }else{
                    Toast.makeText(EscolherConta.this,"Coloque o CPF",Toast.LENGTH_SHORT).show();
                }
            }
        });
        btContaCorrente = (Button) findViewById(R.id.btContaCorrente);
        btContaPoupanca = (Button) findViewById(R.id.btContaPoupanca);

    }
    private void getValuesCorrente(){
        String cpfconta = etVerifCpf.getText().toString();
        Random random = new Random();
        double aleatorio = Math.random() * 10;
        aleatorio = aleatorio * 1000;
        int aleatorioInteiro = (int)aleatorio;
        double aleatorio01 = Math.random() * 1;
        aleatorio01 = aleatorio01 * 100;
        int aleatorio01Inteiro = (int)aleatorio01;
        double aleatorio02 = Math.random() * 10;
        aleatorio02 = aleatorio02 * 10000000;
        int aleatorio02Inteiro = (int)aleatorio02;
        double maxid = Math.random() * 10;
        maxid = maxid * 10000000;
        int maxidInteiro = (int)maxid;
        conta.setId(maxidInteiro);
        conta.setTipo("Conta Corrente");
        conta.setCpf(cpfconta);
        conta.setAgencia(aleatorioInteiro);
        conta.setBanco(aleatorio01Inteiro);
        conta.setNumero(aleatorio02Inteiro);
    }
    private void getValuesPoupanca(){
        String cpfconta = etVerifCpf.getText().toString();
        Random random = new Random();
        double aleatorio = Math.random() * 10;
        aleatorio = aleatorio * 1000;
        int aleatorioInteiro = (int)aleatorio;
        double aleatorio01 = Math.random() * 1;
        aleatorio01 = aleatorio01 * 100;
        int aleatorio01Inteiro = (int)aleatorio01;
        double aleatorio02 = Math.random() * 10;
        aleatorio02 = aleatorio02 * 10000000;
        int aleatorio02Inteiro = (int)aleatorio02;
        double maxid = Math.random() * 10;
        maxid = maxid * 10000000;
        int maxidInteiro = (int)maxid;
        conta.setId(maxidInteiro);
        conta.setTipo("Conta Poupança");
        conta.setCpf(cpfconta);
        conta.setAgencia(aleatorioInteiro);
        conta.setBanco(aleatorio01Inteiro);
        conta.setNumero(aleatorio02Inteiro);
    }
    public void GerenciarConta(){
        startActivity(new Intent(EscolherConta.this, VisualizarContas.class));
    }
    public void ContaCorrente(){
        startActivity(new Intent(EscolherConta.this, GerenciarConta.class));
    }
}