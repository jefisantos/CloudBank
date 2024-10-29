package com.example.cloudbank;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.cloudbank.databinding.ActivityAlterarBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class AlterarActivity extends AppCompatActivity {
    ActivityAlterarBinding binding;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAlterarBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnAlterar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Nome = binding.Nome.getText().toString();
                String NomeMae = binding.NomeMae.getText().toString();
                String NomePai = binding.NomePai.getText().toString();
                String Cpf = binding.Cpf.getText().toString();
                String Endereco = binding.Endereco.getText().toString();
                String Senha = binding.Senha.getText().toString();
                String Email = binding.Email.getText().toString();

                uptadeData(Cpf,Nome,Endereco,Senha,NomeMae,NomePai,Email);
            }
        });
    }

    private void uptadeData(String cpf, String nome, String endereco, String senha, String nomepai, String nomemae,String email) {
        HashMap Usuario = new HashMap();
        Usuario.put("etCpf",cpf);
        Usuario.put("etNome",nome);
        Usuario.put("etEndereco",endereco);
        Usuario.put("etSenha1",senha);
        Usuario.put("etNomeMae",nomemae);
        Usuario.put("etNomePai",nomepai);
        Usuario.put("etEmail",email);

        databaseReference = FirebaseDatabase.getInstance().getReference("Usuario");
        databaseReference.child(cpf).updateChildren(Usuario).addOnCompleteListener(new OnCompleteListener() {
            @Override
            public void onComplete(@NonNull Task task) {
                if(task.isSuccessful()){
                    binding.Cpf.setText("");
                    binding.Nome.setText("");
                    binding.Endereco.setText("");
                    binding.Senha.setText("");
                    binding.NomeMae.setText("");
                    binding.NomePai.setText("");
                    binding.Email.setText("");
                    Toast.makeText(AlterarActivity.this,"Sucesso no Update",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(AlterarActivity.this, "Falha no Update",Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}