package com.example.cloudbank;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Random;

public class AdicionarCompra extends AppCompatActivity {

    private EditText etIdConta , etCpfConta , etPreco , etDescricao , etTipo , etData;
    FirebaseDatabase database;
    DatabaseReference reference;
    private Compras c;
    Button btConfirmar;
    Button btVoltar;
    long maxid1=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_compra);
        btConfirmar = (Button) findViewById(R.id.btConfirmar);
        etCpfConta = (EditText) findViewById(R.id.etCpfconta);
        etIdConta = (EditText) findViewById(R.id.etIdConta);
        etPreco = (EditText) findViewById(R.id.etPreco);
        etDescricao = (EditText) findViewById(R.id.etDescricao);
        etData = (EditText) findViewById(R.id.etData);
        etTipo = (EditText) findViewById(R.id.etTipo);
        database = FirebaseDatabase.getInstance();
        reference = database.getReference("Compras");
        c = new Compras();
        btConfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot snapshot) {
                        getValues();
                        reference.child(String.valueOf(maxid1+1)).setValue(c);
                    }

                    @Override
                    public void onCancelled(DatabaseError error) {

                    }
                });
            }
        });
        reference.addValueEventListener(new ValueEventListener() {
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
    }
    private void getValues(){
        Random random = new Random();
        double idaleatorio = Math.random() * 10;
        idaleatorio = idaleatorio * 10000000;
        int aleatorioInteiro = (int)idaleatorio;
        c.setCpf(etCpfConta.getText().toString());
        c.setData(etData.getText().toString());
        c.setDescricao(etDescricao.getText().toString());
        int preco = Integer.parseInt((etPreco.getText().toString().trim()));
        c.setPreco(preco);
        c.setTipo(etTipo.getText().toString());
        c.setIdConta(etIdConta.getText().toString());
        c.setIdCompra(aleatorioInteiro);

    }
    public void btnAdicionar(){
        startActivity(new Intent(AdicionarCompra.this, GerenciarConta.class));
    }
}