package com.itb.inf2fm.pizzaria.services;


import com.itb.inf2fm.pizzaria.exceptions.BadRequest;
import com.itb.inf2fm.pizzaria.exceptions.NotFound;
import com.itb.inf2fm.pizzaria.model.Categoria;
import com.itb.inf2fm.pizzaria.model.Produto;
import com.itb.inf2fm.pizzaria.repository.CategoriaRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaServiceImpl implements CategoriaService {

    private final CategoriaRepository categoriaRepository;

    public CategoriaServiceImpl(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    @Override
    @Transactional
    public Categoria salvarCategoria(Categoria categoria) {
        if(!categoria.validarCategoria()) {
            throw new BadRequest(categoria.getMensagemErro());
        }
        return categoriaRepository.save(categoria);
    }

    @Override
    public Categoria buscarCategoriaPorId(Long id) {
        try {
            return categoriaRepository.findById(id).get();
        }catch (Exception ex){
            throw new NotFound("Categoria não encontrada com o id " + id);
        }
    }

    @Override
    @Transactional
    public Categoria atualizarCategoria(Categoria categoria, Long id) {
        try {
            if(!categoria.validarCategoria()) {
                throw new BadRequest(categoria.getMensagemErro());
            }
            Categoria categoriaBd = categoriaRepository.findById(id).get();
            // Prosseguindo na atualização
            categoriaBd.setNome(categoria.getNome());
            categoriaBd.setDescricao(categoria.getDescricao());
            return categoriaRepository.save(categoriaBd);  // save : Update para objetos já existentes no Banco de dados

        }catch (Exception ex){
            throw new NotFound("Categoria não encontrada com o id " + id);
        }
    }

    @Override
    public List<Categoria> listarTodasCategorias() {
        return categoriaRepository.findAll();
    }

    @Override
    @Transactional
    public boolean deletarCategoria(Long id) {
        if(!categoriaRepository.existsById(id)){
            throw new NotFound("Categoria não encontrada com o id " + id);
        }
        categoriaRepository.deleteById(id);
        return true;
    }
}
