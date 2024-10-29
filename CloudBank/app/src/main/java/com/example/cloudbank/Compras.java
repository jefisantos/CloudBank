package com.example.cloudbank;

public class Compras {
    private Integer preco;
    private String cpf;
    private String data;
    private String descricao;
    private String tipo;
    private String idConta;
    private int idCompra;
    public Compras(int preco, String cpf, String data, String descricao, String tipo, String idConta, int idCompra) {
        this.preco = preco;
        this.cpf = cpf;
        this.data = data;
        this.descricao = descricao;
        this.tipo = tipo;
        this.idConta = idConta;
        this.idCompra = idCompra;
    }

    public Integer getPreco() {
        return preco;
    }

    public void setPreco(Integer preco) {
        this.preco = preco;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getIdConta() {
        return idConta;
    }

    public void setIdConta(String idConta) {
        this.idConta = idConta;
    }

    public int getIdCompra() {
        return idCompra;
    }

    public void setIdCompra(int idCompra) {
        this.idCompra = idCompra;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    public Compras(){

    }
}
