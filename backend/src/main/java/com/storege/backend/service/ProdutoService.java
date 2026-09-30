package com.storege.backend.service;

import com.storege.backend.dto.ProdutoRequest;
import com.storege.backend.model.Produto;
import com.storege.backend.repository.ProdutoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProdutoService {
    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @Transactional
    public Produto cadastrarProduto(ProdutoRequest prequest) {

        validarProduto(prequest.nome(), "nome");
        validarProduto(prequest.categoria(), "categoria");
        validarProduto(prequest.descricao(), "descricao");
        validarPositivo(prequest.preco(), "preco");
        validarPositivo(prequest.estoque(), "estoque");

        Produto produto = new Produto(prequest.nome(), prequest.categoria(),
                prequest.descricao(), prequest.preco(), prequest.estoque());
        return produtoRepository.save(produto);
    }



    public List<Produto> buscarTodos(){
        return produtoRepository.findAll();
    }


    private void validarProduto(String valor, String campo) {
        if (valor == null || valor.isBlank()) {
            throw new IllegalArgumentException(campo + " é obrigatório");
        }
    }

    private void validarPositivo(Number valor, String campo){
            if (valor == null || valor.doubleValue() <= 0) {
                throw new IllegalArgumentException(campo + " deve ser maior que zero");

        }



    }
}

