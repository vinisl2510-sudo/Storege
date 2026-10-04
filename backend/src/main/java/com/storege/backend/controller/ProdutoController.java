package com.storege.backend.controller;

import com.storege.backend.dto.ProdutoRequest;
import com.storege.backend.dto.ProdutoResponse;
import com.storege.backend.model.Produto;
import com.storege.backend.service.ProdutoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {
    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @GetMapping
    public List<ProdutoResponse> listar() {
        return produtoService.buscarTodos().stream()
                .map(ProdutoResponse::de)
                .toList();
    }

    @GetMapping("/{id}")
    public ProdutoResponse buscar(@PathVariable Long id) {
        return ProdutoResponse.de(produtoService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<ProdutoResponse> cadastrar(@RequestBody ProdutoRequest req) {
        Produto p = produtoService.cadastrarProduto(req);
        return ResponseEntity.status(HttpStatus.CREATED).body(ProdutoResponse.de(p));
    }

    @PutMapping("/{id}")
    public ProdutoResponse atualizar(@PathVariable Long id, @RequestBody ProdutoRequest req) {
        return ProdutoResponse.de(produtoService.atualizarProduto(id, req));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        produtoService.deletarProdutoPorId(id);
        return ResponseEntity.noContent().build();
    }
}
