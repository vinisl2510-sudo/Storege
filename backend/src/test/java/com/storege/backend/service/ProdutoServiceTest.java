package com.storege.backend.service;

import com.storege.backend.dto.ProdutoRequest;
import com.storege.backend.model.Produto;
import com.storege.backend.repository.ProdutoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProdutoServiceTest {

    @Mock
    private ProdutoRepository produtoRepository;

    @InjectMocks
    private ProdutoService produtoService;

    @Test
    void naoDeveCadastrarProdutoComPrecoZero() {
        // 1. Preparar
        ProdutoRequest req = new ProdutoRequest("Celular", "Tela 6 pol", 0.0,
                "Eletronicos", 10);

        // 2. Executar e 3. Verificar: tem que dar erro
        assertThrows(IllegalArgumentException.class,
                () -> produtoService.cadastrarProduto(req));

        // e o produto NÃO pode ter sido salvo
        verify(produtoRepository, never()).save(any());
    }

    @Test
    void naoDeveCadastrarProdutoVazio(){

    }


}