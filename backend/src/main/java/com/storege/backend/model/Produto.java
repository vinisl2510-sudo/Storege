package com.storege.backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "produtos")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String descricao;
    private Double preco;
    private String categoria;
    private int estoque;
    private String imagemUrl;

    public Produto(String nome,
                   String descricao,
                   String categoria,
                   Double preco,
                   int estoque) {
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.categoria = categoria;
        this.estoque = estoque;
    }
}
