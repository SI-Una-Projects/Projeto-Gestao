package com.gestao.service;

import com.gestao.dto.ProjetoCreateDTO;
import com.gestao.dto.ProjetoResponseDTO;
import com.gestao.exception.RegraNegocioException;
import com.gestao.model.Projeto;
import com.gestao.model.StatusProjeto;
import com.gestao.model.Usuario;
import com.gestao.model.Perfil;
import com.gestao.repository.ProjetoRepository;
import com.gestao.repository.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjetoService {

    @Autowired
    private ProjetoRepository projetoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    // ✅ Criar projeto
    public ProjetoResponseDTO criar(ProjetoCreateDTO dto) {

        // 🔍 Buscar gerente
        Usuario gerente = usuarioRepository.findById(dto.getGerenteId())
                .orElseThrow(() -> new RegraNegocioException("Gerente não encontrado"));

        // 🔥 REGRA IMPORTANTE
        if (!gerente.getPerfil().equals(Perfil.GERENTE)) {
            throw new RegraNegocioException("Usuário informado não é um GERENTE");
        }

        // 🔨 Montar projeto
        Projeto projeto = new Projeto();
        projeto.setNome(dto.getNome());
        projeto.setDescricao(dto.getDescricao());
        projeto.setDataInicio(dto.getDataInicio());
        projeto.setDataFimPrevista(dto.getDataFimPrevista());

        // Se não vier status, define padrão
        if (dto.getStatus() == null) {
            projeto.setStatus(StatusProjeto.PLANEJADO);
        } else {
            projeto.setStatus(dto.getStatus());
        }

        projeto.setGerente(gerente);

        // 💾 Salvar
        projeto = projetoRepository.save(projeto);

        return toResponseDTO(projeto);
    }

    // 🔄 Converter para DTO
    private ProjetoResponseDTO toResponseDTO(Projeto projeto) {

        ProjetoResponseDTO dto = new ProjetoResponseDTO();

        dto.setId(projeto.getId());
        dto.setNome(projeto.getNome());
        dto.setDescricao(projeto.getDescricao());
        dto.setDataInicio(projeto.getDataInicio());
        dto.setDataFimPrevista(projeto.getDataFimPrevista());
        dto.setStatus(projeto.getStatus());

        // info do gerente
        dto.setGerenteId(projeto.getGerente().getId());
        dto.setGerenteNome(projeto.getGerente().getNome());

        return dto;
    }
}