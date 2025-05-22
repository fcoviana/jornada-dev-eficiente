package br.com.fcoviana.apilojalivrosvirtual.core.repository;

import br.com.fcoviana.apilojalivrosvirtual.core.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LivroRepository extends JpaRepository<Livro, Long> {
}
