package entidades;

import java.util.Random;

public class Acionista extends Pessoa {

    private boolean em_atraso;
    private String tipo_acao;
    private int tempo_atraso;
    private double preco_acao;
    private Integer numero_registro;
    private int quantidade_dependentes;

    public Acionista(String nome, String endereco, String cpf, String data_nascimento, String tipo_acao, int quantidade_dependentes) {
        super(nome, endereco, cpf, data_nascimento);
        this.tipo_acao = tipo_acao;
        this.quantidade_dependentes = quantidade_dependentes;
        this.preco_acao = (this.tipo_acao.equals("Familiar")) ? 150.00 : 70.00;
        Random random = new Random();
        this.numero_registro = 5000 + random.nextInt(9000);
    }

    @Override
    public String toString() {
        String em_atraso = isEm_atraso() ? "Em Atraso" : "Pagamento em dia";
        return "Nome = " + getNome() + "\nCPF = " + getCpf() + "\nN° Registro = " + getNumero_registro() + "\nEm Atraso = " + em_atraso;
    }

    public boolean isEm_atraso() {
        return em_atraso;
    }

    public void setEm_atraso(boolean em_atraso) {
        this.em_atraso = em_atraso;
    }

    public int getTempo_atraso() {
        return tempo_atraso;
    }

    public void setTempo_atraso(int tempo_atraso) {
        this.tempo_atraso = tempo_atraso;
    }

    public String getTipo_acao() {
        return tipo_acao;
    }

    public void setTipo_acao(String tipo_acao) {
        this.tipo_acao = tipo_acao;
    }

    public double getPreco_acao() {
        return preco_acao;
    }

    public void setPreco_acao(double preco_acao) {
        this.preco_acao = preco_acao;
    }

    public Integer getNumero_registro() {
        return numero_registro;
    }

    public void fazer_pagamento(int quantidade_parcelas) {
        if (quantidade_parcelas == this.tempo_atraso) {
            this.em_atraso = false;
            this.tempo_atraso = 0;
        } else if (quantidade_parcelas < this.tempo_atraso) {
            this.em_atraso = true;
            this.tempo_atraso -= quantidade_parcelas;
        } else if (quantidade_parcelas > this.tempo_atraso) {
            this.em_atraso = false;
            this.tempo_atraso = 0;
        }
    }

    public Integer getQuantidade_dependentes() {
        return quantidade_dependentes;
    }

    public void setQuantidade_dependentes(Integer quantidade_dependentes) {
        this.quantidade_dependentes = quantidade_dependentes;
    }
}
