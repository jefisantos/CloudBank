package com.example.cloudbank;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.cloudbank.databinding.ActivityGerenciarContaBinding;
import com.example.cloudbank.databinding.ActivityVisualizarContasBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class VisualizarContas extends AppCompatActivity {
    DatabaseReference reference;
    Button btVerContas;
    Button btVisualizarContas;
    ActivityVisualizarContasBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityVisualizarContasBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        reference = FirebaseDatabase.getInstance().getReference("Conta");
        binding.btVerContas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cpf = binding.etVerCpfconta.getText().toString();
                if(!cpf.isEmpty()){
                    lerconta(cpf);
                }else{
                    Toast.makeText(VisualizarContas.this,"Coloque o CPF",Toast.LENGTH_SHORT).show();
                }
            }
        });
        btVerContas = (Button) findViewById(R.id.btVerContas);
        btVisualizarContas = (Button) findViewById(R.id.btVisualizarContas);
        btVisualizarContas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VisualizarContas();
            }
        });
    }
    private void lerconta(String cpf) {
        reference = FirebaseDatabase.getInstance().getReference("Conta");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<String> idlist = new ArrayList<String>();
                for (DataSnapshot snapshot1 : snapshot.getChildren()){
                    if(snapshot1.child("cpf").getValue().toString().equals(cpf)){
                        String id = String.valueOf(snapshot1.child("id").getValue().toString());
                        System.out.println(id);
                        idlist.add(String.valueOf(id) + ";");
                    }
                }
                binding.textView4.setText(idlist.toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    public void VisualizarContas(){
        startActivity(new Intent(VisualizarContas.this, GerenciarConta.class));
    }
}