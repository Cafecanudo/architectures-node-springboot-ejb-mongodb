package br.com.softplan.dudebase.repositories;

import br.com.softplan.dudebase.entities.UsuarioEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional(readOnly = true)
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {

    Optional<UsuarioEntity> findByEmail(final String email);

    Page<UsuarioEntity> findByNomeContainsOrEmailContaining(final String nome, final String email, Pageable pageable);

}
