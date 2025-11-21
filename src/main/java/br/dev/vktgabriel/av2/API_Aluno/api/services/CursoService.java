package br.dev.vktgabriel.av2.API_Aluno.api.services;

import br.dev.vktgabriel.av2.API_Aluno.api.controllers.dto.request.RequestCursoDTO;
import br.dev.vktgabriel.av2.API_Aluno.api.models.Aluno;
import br.dev.vktgabriel.av2.API_Aluno.api.models.Curso;
import br.dev.vktgabriel.av2.API_Aluno.api.repositories.CurosRepository;
import br.dev.vktgabriel.av2.API_Aluno.api.services.mappers.CursoMapper;
import br.dev.vktgabriel.av2.API_Aluno.exception.OperacaoNaoAutorizada;
import br.dev.vktgabriel.av2.API_Aluno.exception.RegistroDuplicadoException;
import br.dev.vktgabriel.av2.API_Aluno.exception.RegistroNaoEncontradoException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CursoService {
    private final CurosRepository curosRepository;
    private final CursoMapper cursoMapper;
    private final AlunoService alunoService;

    @Transactional
    public Curso save(RequestCursoDTO requestCursoDTO) throws RegistroDuplicadoException {
        validar(requestCursoDTO);
        Curso curso = cursoMapper.toEntity(requestCursoDTO);
        return curosRepository.save(curso);
    }

    public List<Curso> getAll() {
        return curosRepository.findAll();
    }

    public Curso getById(String id) throws RegistroNaoEncontradoException {
        UUID uuid = UUID.fromString(id);
        Curso curso =  curosRepository.findById(uuid).orElse(null);
        if (curso == null) {
            throw new RegistroNaoEncontradoException("Curso", "id", id);
        }
        return curso;
    }

    @Transactional
    public Curso update(String id, RequestCursoDTO requestCursoDTO) throws RegistroDuplicadoException, RegistroNaoEncontradoException {
        Curso curso = getById(id);
        validarUpdate(curso, requestCursoDTO);

        curso.setNome(requestCursoDTO.nome());
        curso.setCargaHoraria(Integer.parseInt(requestCursoDTO.cargaHoraria()));

        return curosRepository.save(curso);
    }

    @Transactional
    public void delete(String id) {
        Curso curso = getById(id);
        curosRepository.delete(curso);
    }

    @Transactional
    public void addAluno(String idCurso, String idAluno) throws RegistroNaoEncontradoException, OperacaoNaoAutorizada {
        Curso curso = getById(idCurso);
        Aluno aluno = alunoService.getById(idAluno);
        if (curso.getAlunos().contains(aluno)) {
            throw new OperacaoNaoAutorizada(String.format("Esse aluno(a) já está cadastrado no curso %s", curso.getNome()));
        }
        curso.getAlunos().add(aluno);
        curosRepository.save(curso);
    }

    @Transactional
    public void remove(String idCurso, String idAluno) throws RegistroNaoEncontradoException, OperacaoNaoAutorizada {
        Curso curso = getById(idCurso);
        Aluno aluno = alunoService.getById(idAluno);
        if (!curso.getAlunos().contains(aluno)) {
            throw new OperacaoNaoAutorizada(String.format("Esse aluno(a) não está cadastrado(a) no curso %s", curso.getNome()));
        }
        curso.getAlunos().remove(aluno);
        curosRepository.save(curso);
    }

    private void validar(RequestCursoDTO requestCursoDTO) throws RegistroDuplicadoException {
        Curso curso = curosRepository.findByNome(requestCursoDTO.nome()).orElse(null);
        if (curso != null) {
            throw new RegistroDuplicadoException("Curso", "Nome", requestCursoDTO.nome());
        }
    }

    private void validarUpdate(Curso curso, RequestCursoDTO requestCursoDTO) throws RegistroDuplicadoException {
        Curso cursoUpdate = curosRepository.findByNome(requestCursoDTO.nome()).orElse(null);
        if (cursoUpdate != null && cursoUpdate != curso) {
            throw new RegistroDuplicadoException("Curso", "Nome", requestCursoDTO.nome());
        }
    }
}
