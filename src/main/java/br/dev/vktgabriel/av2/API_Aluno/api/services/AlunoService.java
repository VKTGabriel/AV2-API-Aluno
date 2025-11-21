package br.dev.vktgabriel.av2.API_Aluno.api.services;

import br.dev.vktgabriel.av2.API_Aluno.api.controllers.dto.request.RequestAlunoDTO;
import br.dev.vktgabriel.av2.API_Aluno.api.controllers.dto.request.RequestUpdateAlunoDTO;
import br.dev.vktgabriel.av2.API_Aluno.api.models.Aluno;
import br.dev.vktgabriel.av2.API_Aluno.api.repositories.AlunoRepository;
import br.dev.vktgabriel.av2.API_Aluno.api.services.mappers.AlunoMapper;
import br.dev.vktgabriel.av2.API_Aluno.exception.RegistroDuplicadoException;
import br.dev.vktgabriel.av2.API_Aluno.exception.RegistroNaoEncontradoException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AlunoService {
    private final AlunoRepository alunoRepository;
    private final AlunoMapper alunoMapper;

    @Transactional
    public Aluno save(RequestAlunoDTO requestDTO) throws RegistroDuplicadoException {
        validar(requestDTO);
        Aluno aluno = alunoMapper.toEntity(requestDTO);
        aluno.setMatricula(gerarMatricula());
        return alunoRepository.save(aluno);
    }

    public List<Aluno> getAll() {
        return alunoRepository.findAll();
    }

    public Aluno getById(String id) throws RegistroNaoEncontradoException {
        UUID uuid = UUID.fromString(id);
        Aluno aluno =  alunoRepository.findById(uuid).orElse(null);
        if (aluno == null) {
            throw new RegistroNaoEncontradoException("Aluno", "id", id);
        }
        return aluno;
    }

    public Aluno update(String id, RequestUpdateAlunoDTO requestUpdateAlunoDTO) throws RegistroNaoEncontradoException, RegistroDuplicadoException {
        Aluno aluno = getById(id);
        validarUpdate(aluno, requestUpdateAlunoDTO);

        aluno.setEmail(requestUpdateAlunoDTO.email());
        aluno.setNome(requestUpdateAlunoDTO.nome());
        aluno.setDataNascimento(LocalDate.parse(requestUpdateAlunoDTO.dataNascimento(), DateTimeFormatter.ofPattern("dd/MM/yyyy")));

        return alunoRepository.save(aluno);
    }

    public void delete(String id) throws RegistroNaoEncontradoException {
        Aluno aluno = getById(id);
        alunoRepository.delete(aluno);
    }

    private void validar(RequestAlunoDTO requestDTO) throws RegistroDuplicadoException {
        Aluno aluno = alunoRepository.findByEmail(requestDTO.email()).orElse(null);
        if (aluno != null) {
            throw new RegistroDuplicadoException("Aluno", "email", requestDTO.email());
        }

        aluno = alunoRepository.findByCpf(requestDTO.cpf()).orElse(null);
        if (aluno != null) {
            throw new RegistroDuplicadoException("Aluno", "cpf", requestDTO.cpf());
        }
    }

    private void validarUpdate(Aluno aluno, RequestUpdateAlunoDTO requestUpdateAlunoDTO) throws RegistroDuplicadoException {
        Aluno alunoUpdate = alunoRepository.findByEmail(requestUpdateAlunoDTO.email()).orElse(null);
        if (aluno != null && alunoUpdate != aluno) {
            throw new RegistroDuplicadoException("Aluno", "email", requestUpdateAlunoDTO.email());
        }

    }

    private String gerarMatricula() {
        Random random = new Random();
        String matricula = String.format("%d%d", LocalDate.now().getYear(), random.nextInt(100, 999));
        Aluno aluno = alunoRepository.findByMatricula(matricula).orElse(null);

        if (aluno != null) {
            return gerarMatricula();
        }

        return matricula;
    }
}
