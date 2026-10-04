package com.storege.backend.service;

import com.storege.backend.dto.CadastroRequest;
import com.storege.backend.model.Usuario;
import com.storege.backend.repository.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private UsuarioService usuarioService;

    @Test
    void deveSalvarCadaCampoNoLugarCerto() {
        // 1. Preparar
        CadastroRequest req = new CadastroRequest("Ana", "ana@email.com",
                "12345678900", "minhaSenha", "11999999999");
        when(usuarioRepository.save(any())).thenAnswer(inv -> inv.getArgument(0));

        // 2. Executar
        Usuario salvo = usuarioService.registrar(req);

        // 3. Verificar: CPF no CPF, senha na senha
        assertEquals("12345678900", salvo.getCpf());
        assertEquals("minhaSenha", salvo.getSenha());
    }

    @Test
    void naoDeveCadastrarEmailRepetido() {
        CadastroRequest req = new CadastroRequest("Ana", "ana@email.com",
                "12345678900", "minhaSenha", "11999999999");
        when(usuarioRepository.existsByEmail("ana@email.com")).thenReturn(true);

        IllegalArgumentException erro = assertThrows(IllegalArgumentException.class,
                () -> usuarioService.registrar(req));

        assertEquals("Email já cadastrado", erro.getMessage());
        verify(usuarioRepository, never()).save(any());
    }
}
