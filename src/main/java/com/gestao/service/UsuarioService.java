package com.gestao.service;

import com.gestao.dto.UsuarioCreateDTO;
import com.gestao.dto.UsuarioResponseDTO;
import com.gestao.exception.RegraNegocioException;
import com.gestao.model.Usuario;
import com.gestao.repository.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    // CREATE
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

    
    public List<UsuarioResponseDTO> listarTodos() {
        return usuarioRepository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    
    public UsuarioResponseDTO buscarPorId(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RegraNegocioException("Usuário não encontrado"));
        return toResponseDTO(usuario);
    }

    
    public UsuarioResponseDTO atualizar(Long id, UsuarioCreateDTO dto) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RegraNegocioException("Usuário não encontrado"));

        
        if (!usuario.getCpf().equals(dto.getCpf()) && 
            usuarioRepository.findByCpf(dto.getCpf()).isPresent()) {
            throw new RegraNegocioException("CPF já cadastrado");
        }

        
        if (!usuario.getEmail().equals(dto.getEmail()) && 
            usuarioRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new RegraNegocioException("Email já cadastrado");
        }

        
        if (!usuario.getLogin().equals(dto.getLogin()) && 
            usuarioRepository.findByLogin(dto.getLogin()).isPresent()) {
            throw new RegraNegocioException("Login já cadastrado");
        }

        usuario.setNome(dto.getNome());
        usuario.setCpf(dto.getCpf());
        usuario.setEmail(dto.getEmail());
        usuario.setCargo(dto.getCargo());
        usuario.setLogin(dto.getLogin());
        
        if (dto.getSenha() != null && !dto.getSenha().isEmpty()) {
            usuario.setSenha(passwordEncoder.encode(dto.getSenha()));
        }
        
        usuario.setPerfil(dto.getPerfil());

        usuario = usuarioRepository.save(usuario);

        return toResponseDTO(usuario);
    }

    // DELETE
    public void deletar(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RegraNegocioException("Usuário não encontrado"));
        usuarioRepository.delete(usuario);
    }

    // HELPER
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