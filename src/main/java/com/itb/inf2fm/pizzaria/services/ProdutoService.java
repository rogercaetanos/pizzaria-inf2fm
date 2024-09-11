package com.itb.inf2fm.pizzaria.services;

import com.itb.inf2fm.pizzaria.model.Produto;

import java.util.List;

public interface ProdutoService {

    public Produto salvarProduto(Produto produto);
    public Produto buscarProdutoPorId(Long id);
    public Produto atualizarProduto(Produto produto, Long id);
    public List<Produto> listarTodosProdutos();
    public boolean deletarProduto(Long id);



}
