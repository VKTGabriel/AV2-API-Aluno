package br.dev.vktgabriel.av2.API_Aluno.api.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(length = 100, nullable = false)
    private String nome;

    @Column(nullable = false)
    private Integer cargaHoraria;

    @ManyToMany
    @JoinTable(
            name = "aluno_curso",
            joinColumns = @JoinColumn(name = "id_curso"),
            inverseJoinColumns = @JoinColumn(name = "id_aluno")
    )
    private List<Aluno> alunos;

    @CreatedDate
    private LocalDateTime DataCadastro;
}
