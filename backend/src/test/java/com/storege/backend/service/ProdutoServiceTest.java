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
        // nome em branco
        ProdutoRequest req = new ProdutoRequest("  ", "Tela 6 pol", 1500.0,
                "Eletronicos", 10);

        IllegalArgumentException erro = assertThrows(IllegalArgumentException.class,
                () -> produtoService.cadastrarProduto(req));

        assertEquals("nome é obrigatório", erro.getMessage());
        verify(produtoRepository, never()).save(any());
    }

    @Test
    void deveSalvarCadaCampoNoLugarCerto() {
        // 1. Preparar: o save do mock devolve o próprio produto que recebeu
        ProdutoRequest req = new ProdutoRequest("Celular", "Tela 6 pol", 1500.0,
                "Eletronicos", 10);
        when(produtoRepository.save(any())).thenAnswer(inv -> inv.getArgument(0));

        // 2. Executar
        Produto salvo = produtoService.cadastrarProduto(req);

        // 3. Verificar: cada valor tem que estar no campo certo
        assertEquals("Celular", salvo.getNome());
        assertEquals("Tela 6 pol", salvo.getDescricao());
        assertEquals("Eletronicos", salvo.getCategoria());
        assertEquals(1500.0, salvo.getPreco());
        assertEquals(10, salvo.getEstoque());
    }

}