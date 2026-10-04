package com.storege.backend.dto;

import com.storege.backend.model.Produto;

public record ProdutoResponse(
        Long id,
        String nome,
        String descricao,
        Double preco,
        String categoria,
        int estoque,
        String imagemUrl
) {
    public static ProdutoResponse de(Produto produto) {
        return new ProdutoResponse(
                produto.getId(),
                produto.getNome(),
                produto.getDescricao(),
                produto.getPreco(),
                produto.getCategoria(),
                produto.getEstoque(),
                produto.getImagemUrl()
        );
    }
}
