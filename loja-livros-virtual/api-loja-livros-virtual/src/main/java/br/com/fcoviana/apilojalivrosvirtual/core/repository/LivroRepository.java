package br.com.fcoviana.apilojalivrosvirtual.core.repository;

import br.com.fcoviana.apilojalivrosvirtual.core.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LivroRepository extends JpaRepository<Livro, Long> {
    List<Livro> findByIdIn(List<Long> ids);
}
