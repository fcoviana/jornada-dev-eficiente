package br.com.fcoviana.apilojalivrosvirtual.core.repository;

import br.com.fcoviana.apilojalivrosvirtual.core.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AutorRepository extends JpaRepository<Autor, Long> {
    Optional<Autor> findByEmail(String email);
}
