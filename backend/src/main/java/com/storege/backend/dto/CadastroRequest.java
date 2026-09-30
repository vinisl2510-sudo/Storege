package com.storege.backend.dto;

public record CadastroRequest(String nome,
                              String email,
                              String cpf,
                              String senha,
                              String telefone) {}