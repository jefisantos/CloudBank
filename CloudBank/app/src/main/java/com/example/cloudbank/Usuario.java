package com.example.cloudbank;

public class Usuario {
    private String etNome;
    private String id;
    private String etEmail;
    private String etCpf;
    private String etNomePai;
    private String etNomeMae;
    private String etEndereco;
    private String etSenha1;

    public Usuario(String etNome, String etEmail, String etCpf, String etNomePai, String etNomeMae, String etEndereco, String etSenha1) {
        this.etNome = etNome;
        this.etEmail = etEmail;
        this.etCpf = etCpf;
        this.etNomePai = etNomePai;
        this.etNomeMae = etNomeMae;
        this.etEndereco = etEndereco;
        this.etSenha1 = etSenha1;
    }
    public Usuario() {

    }

    public String getId() {
        return id;
    }
    public void setId(String id){
        this.id = id;
    }

    public String getEtNome() {
        return etNome;
    }

    public void setEtNome(String etNome) {
        this.etNome = etNome;
    }

    public String getEtEmail() {
        return etEmail;
    }

    public void setEtEmail(String etEmail) {
        this.etEmail = etEmail;
    }

    public String getEtCpf() {
        return etCpf;
    }

    public void setEtCpf(String etCpf) {
        this.etCpf = etCpf;
    }

    public String getEtNomePai() {
        return etNomePai;
    }

    public void setEtNomePai(String etNomePai) {
        this.etNomePai = etNomePai;
    }

    public String getEtNomeMae() {
        return etNomeMae;
    }

    public void setEtNomeMae(String etNomeMae) {
        this.etNomeMae = etNomeMae;
    }

    public String getEtEndereco() {
        return etEndereco;
    }

    public void setEtEndereco(String etEndereco) {
        this.etEndereco = etEndereco;
    }

    public String getEtSenha1() {
        return etSenha1;
    }

    public void setEtSenha1(String etSenha1) {
        this.etSenha1 = etSenha1;
    }
    public void salvarDados(){
        //salvar dados database
    }

}
