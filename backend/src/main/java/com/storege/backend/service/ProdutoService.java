package com.storege.backend.service;

import com.storege.backend.dto.ProdutoRequest;
import com.storege.backend.model.Produto;
import com.storege.backend.repository.ProdutoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

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

    public Produto buscarPorId(Long id) {
        return produtoRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("Id: " + id + "do produto nao encontrado"));
    }

    @Transactional
    public Produto atualizarProduto(Long id, ProdutoRequest prequest) {
        Produto produtoNovo = buscarPorId(id);
        if (produtoRepository.findById(produtoNovo.getId()) == null) {
            throw new IllegalArgumentException("Produto nao existe");
        }

        validarProduto(prequest.nome(), "nome");
        validarProduto(prequest.categoria(), "categoria");
        validarProduto(prequest.descricao(), "descricao");
        validarPositivo(prequest.preco(), "preco");
        validarPositivo(prequest.estoque(), "estoque");

        produtoNovo.setNome(prequest.nome());
        produtoNovo.setCategoria(prequest.categoria());
        produtoNovo.setDescricao(prequest.descricao());
        produtoNovo.setPreco(prequest.preco());
        produtoNovo.setEstoque(prequest.estoque());

        return produtoRepository.save(produtoNovo);
    }


    public List<Produto> buscarTodos(){
        return produtoRepository.findAll();
    }

    @Transactional
    public void deletarProdutoPorId(Long id) {
        if (!produtoRepository.existsById(id)) {
            throw new IllegalArgumentException("Produto não encontrado: " + id);
        }
        produtoRepository.deleteById(id);
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

