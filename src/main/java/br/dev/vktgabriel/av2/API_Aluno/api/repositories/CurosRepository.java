package br.dev.vktgabriel.av2.API_Aluno.api.repositories;

import br.dev.vktgabriel.av2.API_Aluno.api.models.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CurosRepository extends JpaRepository<Curso, UUID> {
    Optional<Curso> findByNome(String nome);
}
