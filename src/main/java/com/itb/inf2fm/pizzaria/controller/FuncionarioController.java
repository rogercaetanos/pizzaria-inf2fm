package com.itb.inf2fm.pizzaria.controller;

// @Controller     : utilizado exclusivamente para sistemas WEB
// @RestController : utilizado exclusivamente para API'S
// @RequestMapping : representa a url principal do controlador (endereço)
// @GetMapping     : representa o complemento da url principal. USO EXCLUSIVO PARA REALIZAR CONSULTAS
// @PostMapping    : representa o complemento da url principal. USO EXCLUSIVO PARA CADASTROS (INSERT)


import com.itb.inf2fm.pizzaria.model.Categoria;
import com.itb.inf2fm.pizzaria.services.CategoriaService;
import com.itb.inf2fm.pizzaria.services.ProdutoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/funcionario")
public class FuncionarioController {

    private final CategoriaService categoriaService;
    private final ProdutoService produtoService;

    public FuncionarioController(CategoriaService categoriaService, ProdutoService produtoService) {
        this.categoriaService = categoriaService;
        this.produtoService = produtoService;
    }

    @GetMapping("/categoria")
    public ResponseEntity<List<Categoria>> listarTodasCategorias() {
        return ResponseEntity.ok().body(categoriaService.listarTodasCategorias());
    }

    @PostMapping("/categoria")
    public ResponseEntity<Categoria> salvarCategoria(@RequestBody Categoria categoria) {

        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/v1/funcionario/categoria").toUriString());
        return ResponseEntity.created(uri).body(categoriaService.salvarCategoria(categoria));
    }

}
