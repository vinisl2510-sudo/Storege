package com.storege.backend.service;

import com.storege.backend.dto.CadastroRequest;
import com.storege.backend.model.Usuario;
import com.storege.backend.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Transactional
    public Usuario registrar(CadastroRequest req) {
        validarObrigatorio(req.nome(), "Nome");
        validarObrigatorio(req.email(), "Email");
        validarObrigatorio(req.cpf(), "CPF");
        validarObrigatorio(req.senha(), "Senha");

        if (usuarioRepository.existeEmail(req.email())) {
            throw new IllegalArgumentException("Email já cadastrado");
        }
        if (usuarioRepository.existeCpf(req.cpf())) {
            throw new IllegalArgumentException("CPF já cadastrado");
        }

        Usuario usuario = new Usuario(req.nome(), req.email(), req.cpf(),
                req.senha(), req.telefone());
        return usuarioRepository.save(usuario);
    }

    @Transactional(readOnly = true)
    public Usuario buscarPorEmail(String email) {
        return usuarioRepository.buscarPorEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado: " + email));
    }

    @Transactional
    public void deletarPorId(Long id) {
        if (!usuarioRepository.existsById(id)) {
            throw new IllegalArgumentException("Usuário não encontrado: " + id);
        }
        usuarioRepository.deleteById(id);
    }

    private void validarObrigatorio(String valor, String campo) {
        if (valor == null || valor.isBlank()) {
            throw new IllegalArgumentException(campo + " é obrigatório");
        }
    }

}