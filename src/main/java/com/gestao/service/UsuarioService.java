package com.gestao.service;

import com.gestao.dto.UsuarioCreateDTO;
import com.gestao.dto.UsuarioResponseDTO;
import com.gestao.exception.RegraNegocioException;
import com.gestao.model.Usuario;
import com.gestao.repository.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    
    public UsuarioResponseDTO criar(UsuarioCreateDTO dto) {

        
        if (usuarioRepository.findByCpf(dto.getCpf()).isPresent()) {
            throw new RegraNegocioException("CPF já cadastrado");
        }

        if (usuarioRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new RegraNegocioException("Email já cadastrado");
        }

        if (usuarioRepository.findByLogin(dto.getLogin()).isPresent()) {
            throw new RegraNegocioException("Login já cadastrado");
        }

        if (dto.getPerfil() == null) {
            throw new RegraNegocioException("Perfil é obrigatório");
        }

        
        Usuario usuario = new Usuario();
        usuario.setNome(dto.getNome());
        usuario.setCpf(dto.getCpf());
        usuario.setEmail(dto.getEmail());
        usuario.setCargo(dto.getCargo());
        usuario.setLogin(dto.getLogin());

        
        String senhaCriptografada = passwordEncoder.encode(dto.getSenha());
        usuario.setSenha(senhaCriptografada);

        usuario.setPerfil(dto.getPerfil());

        
        usuario = usuarioRepository.save(usuario);

        
        return toResponseDTO(usuario);
    }

    
    private UsuarioResponseDTO toResponseDTO(Usuario usuario) {
        UsuarioResponseDTO dto = new UsuarioResponseDTO();

        dto.setId(usuario.getId());
        dto.setNome(usuario.getNome());
        dto.setEmail(usuario.getEmail());
        dto.setLogin(usuario.getLogin());
        dto.setPerfil(usuario.getPerfil());
        dto.setCargo(usuario.getCargo());

        return dto;
    }
}