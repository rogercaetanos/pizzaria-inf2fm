package com.itb.inf2fm.pizzaria.controller;

// @Controller     : utilizado exclusivamente para sistemas WEB
// @RestController : utilizado exclusivamente para API'S
// @RequestMapping : representa a url principal do controlador (endereço)
// @GetMapping     : representa o complemento da url principal. USO EXCLUSIVO PARA REALIZAR CONSULTAS
// @PostMapping    : representa o complemento da url principal. USO EXCLUSIVO PARA CADASTROS (INSERT)
// @PutMapping     : representa o complemento da url principal. USO EXCLUSIVO PARA  ATUALIZAR (UPDATE)
// @DeleteMapping  : representa o complemento da url principal. USO EXCLUSIVO PARA EXCLUIR (DELETE)
// @PathVariable   : representa os parâmetros passados pela url "variáveis"


import com.itb.inf2fm.pizzaria.exceptions.BadRequest;
import com.itb.inf2fm.pizzaria.model.Categoria;
import com.itb.inf2fm.pizzaria.services.CategoriaService;
import com.itb.inf2fm.pizzaria.services.ProdutoService;
import jakarta.transaction.Transactional;
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

    @GetMapping("/categoria/{id}")
    public ResponseEntity<Categoria> listarCategoriaPorId(@PathVariable(value = "id") String id) {
        try {
            return ResponseEntity.ok().body(categoriaService.buscarCategoriaPorId(Long.parseLong(id)));

        }catch (NumberFormatException ex) {
            throw new BadRequest("'" + id + "' não é um número inteiro válido. Por favor, forneça um valor inteiro, como 30.");
        }

    }
    @PostMapping("/categoria")
    @Transactional
    public ResponseEntity<Categoria> salvarCategoria(@RequestBody Categoria categoria) {

        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/v1/funcionario/categoria").toUriString());
        return ResponseEntity.created(uri).body(categoriaService.salvarCategoria(categoria));
    }

    @PutMapping("/categoria/{id}")
    @Transactional
    public ResponseEntity<Categoria> atualizarCategoria(@RequestBody Categoria categoria, @PathVariable (value = "id") String id) {
        try {
            return ResponseEntity.ok().body(categoriaService.atualizarCategoria(categoria, Long.parseLong(id)));

        }catch (NumberFormatException ex) {
            throw new BadRequest("'" + id + "' não é um número inteiro válido. Por favor, forneça um valor inteiro, como 30.");
        }
    }

    // Object : Representa qualquer tipo de Classe (objeto)
    @DeleteMapping("/categoria/{id}")
    @Transactional
    public ResponseEntity<Object> deletarCategoria(@PathVariable(value = "id") String id) {
        try{
            if(categoriaService.deletarCategoria(Long.parseLong(id))) {
                return ResponseEntity.ok().body("Categoria com o id " + id + " excluída com sucesso");
            }
        }catch (NumberFormatException ex) {

            throw new BadRequest("'" + id + "' não é um número inteiro válido. Por favor, forneça um valor inteiro, como 30.");
        }

        return ResponseEntity.ok().body("Não foi possível a exclusão da categoria com o id " + id);
    }


}
