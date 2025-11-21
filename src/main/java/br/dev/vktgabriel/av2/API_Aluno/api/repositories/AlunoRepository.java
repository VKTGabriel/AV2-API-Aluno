package br.dev.vktgabriel.av2.API_Aluno.api.repositories;

import br.dev.vktgabriel.av2.API_Aluno.api.models.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface AlunoRepository extends JpaRepository<Aluno, UUID> {
    Optional<Aluno> findByEmail(String email);
    Optional<Aluno> findByCpf(String cpf);
    Optional<Aluno> findByMatricula(String matricula);
}
