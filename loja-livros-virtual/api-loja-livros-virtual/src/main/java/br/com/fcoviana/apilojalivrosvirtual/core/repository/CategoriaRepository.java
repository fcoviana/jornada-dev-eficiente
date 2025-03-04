package br.com.fcoviana.apilojalivrosvirtual.core.repository;

import br.com.fcoviana.apilojalivrosvirtual.core.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    Optional<Categoria> findByNome(String nome);
}
