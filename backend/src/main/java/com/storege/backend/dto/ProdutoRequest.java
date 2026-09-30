package com.storege.backend.dto;

public record ProdutoRequest(String nome,
                             String descricao,
                             Double preco,
                             String categoria,
                             Integer estoque) {
}
