package com.example.cloudbank;

public class Conta {
    private int id;
    private String cpf;
    private String tipo;
    private int agencia;
    private int banco;
    private int numero;

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Conta(int id, String cpf, int agencia, int banco, int numero, String tipo){
        this.id = id;
        this.cpf = cpf;
        this.tipo = tipo;
        this.agencia = agencia;
        this.banco = banco;
        this.numero = numero;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public int getAgencia() {
        return agencia;
    }

    public void setAgencia(int agencia) {
        this.agencia = agencia;
    }

    public int getBanco() {
        return banco;
    }

    public void setBanco(int banco) {
        this.banco = banco;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public Conta(){

    }

}
