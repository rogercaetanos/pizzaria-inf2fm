package com.itb.inf2fm.pizzaria.services;

import com.itb.inf2fm.pizzaria.model.Categoria;

import java.util.List;

public interface CategoriaService {

    public Categoria salvarCategoria(Categoria categoria);
    public Categoria buscarCategoriaPorId(Long id);
    public Categoria atualizarCategoria(Categoria categoria, Long id);
    public List<Categoria> listarTodasCategorias();
    public boolean deletarCategoria(Long id);

}
