package com.itb.inf2fm.pizzaria.model;



import jakarta.persistence.*;

import java.util.Objects;


@Entity
@Table(name = "Produto")
public class Produto {

    // Encapsulamento - Começamos prover o encapsulamento quando trabalhamos com os modificadores
    // de acesso
    // Temos três modificadores de acesso:
    // public : Acesso livre para todas as classes
    // private : Acesso permitido apenas dentro da própria classe
    // protected: Acesso permitido apenas para as classes filhas (Sistema de herança)

    // 1º Passo : Trabalhar os modificadores de acesso, ou seja, utilizar o private ou o protected (herança)
    // 2º Passo : Criar os métodos setter´s e getter´s para cada atributo
    // 3º Passo : Criar os métodos de validação contendo as regras necessárias

    @Id                                                   // PK
    @GeneratedValue(strategy = GenerationType.IDENTITY)   // AUTO INCREMENTO
    private Long id;

    @Column(nullable = false, length = 100)
    private String nome;
    @Column(nullable = true, length = 45)
    private String tipo;
    @Column(nullable = true, length = 255)
    private String descricao;
    @Column(nullable = true, columnDefinition = "DECIMAL(5,2)")
    private double precoCompra;
    @Column(nullable = true, columnDefinition = "DECIMAL(5,2)")
    private double precoVenda;
    @Column(nullable = true)
    private int quantidadeEstoque;
    private boolean codStatus;

    // Atributos de apoio

    @Transient                        // REPRESENTA O ATRIBUTO QUE NÃO É UMA COLUNA DA TABELA
    private String mensagemErro = "";
    @Transient
    private boolean isValid = true;

    // Metodos Setter´s e Getter´s ( Segundo passo para prover o encapsulamento )

    public void setId(Long id){
        this.id = id;
    }
    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getPrecoCompra() {
        return precoCompra;
    }

    public void setPrecoCompra(double precoCompra) {
        this.precoCompra = precoCompra;
    }

    public double getPrecoVenda() {
        return precoVenda;
    }

    public void setPrecoVenda(double precoVenda) {
        this.precoVenda = precoVenda;
    }

    public int getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    public void setQuantidadeEstoque(int quantidadeEstoque) {
        this.quantidadeEstoque = quantidadeEstoque;
    }

    public boolean getCodStatus() {
        return codStatus;
    }

    public void setCodStatus(boolean codStatus) {
        this.codStatus = codStatus;
    }

    public String getMensagemErro() {
        return mensagemErro;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Produto produto = (Produto) o;
        return Objects.equals(id, produto.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    // Criando um método para validação

    public boolean validarProduto(){
        if(precoVenda < 0 ){
            isValid = false;
            precoVenda = 0;
            mensagemErro += "O preço de venda do produto não pode ser negativo:";
        }
        if(precoCompra < 0 ){
            isValid = false;
            precoCompra = 0;
            mensagemErro += "O preço de compra do produto não pode ser negativo:";
        }
        return isValid;
    }


}
