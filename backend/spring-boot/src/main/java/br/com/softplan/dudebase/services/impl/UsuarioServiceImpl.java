package br.com.softplan.dudebase.services.impl;

import br.com.softplan.dudebase.entities.UsuarioEntity;
import br.com.softplan.dudebase.repositories.UsuarioRepository;
import br.com.softplan.dudebase.services.UsuarioService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Log
@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UsuarioEntity persistir(UsuarioEntity usuarioEntity) {
        log.info("Savando usuario por email...");
        return this.usuarioRepository.save(usuarioEntity);
    }

    @Override
    public Optional<UsuarioEntity> buscarUsuarioPorEmail(String email) {
        log.info("Buscando usuario por email...");
        return usuarioRepository.findByEmail(email);
    }

    @Override
    public Optional<UsuarioEntity> buscarUsuarioPorID(long id) {
        log.info("Buscando usuario por ID...");
        return usuarioRepository.findById(id);
    }

    @Override
    public Page<UsuarioEntity> buscarUsuarioContendoNomeOuEmail(String nomeEmail, PageRequest pageRequest) {
        log.info("Buscando usuario contanto nome ou email...");
        return usuarioRepository.findByNomeContainsOrEmailContaining(nomeEmail, nomeEmail, pageRequest);
    }
}
