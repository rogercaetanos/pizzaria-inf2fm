package com.itb.inf2fm.pizzaria.services;


import com.itb.inf2fm.pizzaria.exceptions.BadRequest;
import com.itb.inf2fm.pizzaria.exceptions.NotFound;
import com.itb.inf2fm.pizzaria.model.Produto;
import com.itb.inf2fm.pizzaria.repository.ProdutoRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoServiceImpl implements ProdutoService{

    private final ProdutoRepository produtoRepository;

    public ProdutoServiceImpl(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @Override
    @Transactional
    public Produto salvarProduto(Produto produto) {
        if(!produto.validarProduto()) {
            throw new BadRequest(produto.getMensagemErro());
        }
        return produtoRepository.save(produto);
    }

    @Override
    public Produto buscarProdutoPorId(Long id) {
        try {
            return produtoRepository.findById(id).get();
        }catch (Exception ex){
           throw new NotFound("Produto não encontrado com o id " + id);
        }
    }

    @Override
    @Transactional
    public Produto atualizarProduto(Produto produto, Long id) {
        try {
            if(!produto.validarProduto()) {
                throw new BadRequest(produto.getMensagemErro());
            }
            Produto produtoBd = produtoRepository.findById(id).get();
            // Prosseguindo na atualização
            produtoBd.setNome(produto.getNome());
            produtoBd.setDescricao(produto.getDescricao());
            produtoBd.setQuantidadeEstoque(produto.getQuantidadeEstoque());
            produtoBd.setPrecoVenda(produto.getPrecoVenda());
            produtoBd.setTipo(produto.getTipo());
            return produtoRepository.save(produtoBd);  // save : Update para objetos já existentes no Banco de dados

        }catch (Exception ex){
            throw new NotFound("Produto não encontrado com o id " + id);
        }

    }

    @Override
    public List<Produto> listarTodosProdutos() {
        return produtoRepository.findAll();
    }

    @Override
    @Transactional
    public boolean deletarProduto(Long id) {
        if(!produtoRepository.existsById(id)){
            throw new NotFound("Produto não encontrado com o id " + id);
        }
        produtoRepository.deleteById(id);
        return true;
    }
}
