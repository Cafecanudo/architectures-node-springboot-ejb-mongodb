package br.com.softplan.dudebase.services;

import br.com.softplan.dudebase.entities.UsuarioEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.Optional;

public interface UsuarioService {

    Optional<UsuarioEntity> buscarUsuarioPorEmail(final String email);

    Optional<Page<UsuarioEntity>> buscarUsuarioContendoNomeOuEmail(final String nomeEmail, PageRequest pageRequest);

}
