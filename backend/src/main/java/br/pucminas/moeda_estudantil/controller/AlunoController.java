package br.pucminas.moeda_estudantil.controller;

import br.pucminas.moeda_estudantil.dto.request.AtualizarAlunoRequestDto;
import br.pucminas.moeda_estudantil.dto.request.CriarAlunoRequestDto;
import br.pucminas.moeda_estudantil.dto.response.AlunoResponseDto;
import br.pucminas.moeda_estudantil.service.AlunoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/alunos")
@Tag(name = "Alunos", description = "Cadastro, consulta, atualização e remoção de alunos")
public class AlunoController {

    private final AlunoService alunoService;

    public AlunoController(AlunoService alunoService) {
        this.alunoService = alunoService;
    }


    @PostMapping
    @SecurityRequirements
    @Operation(summary = "Cadastrar novo aluno", description = "Endpoint público de cadastro de aluno.")
    public ResponseEntity<Object> criar(@Valid @RequestBody CriarAlunoRequestDto request,
                                        BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String erros = bindingResult.getFieldErrors().stream()
                    .map(e -> e.getField() + ": " + e.getDefaultMessage())
                    .collect(Collectors.joining("; "));
            return ResponseEntity.badRequest().body(erros);
        }
        try {
            AlunoResponseDto response = alunoService.criar(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }


    @GetMapping
    @Operation(summary = "Listar todos os alunos")
    public ResponseEntity<List<AlunoResponseDto>> listarTodos() {
        return ResponseEntity.ok(alunoService.listarTodos());
    }


    @GetMapping("/{id}")
    @Operation(summary = "Buscar aluno por ID")
    public ResponseEntity<Object> buscarPorId(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(alunoService.buscarPorId(id));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }


    @GetMapping("/cpf/{cpf}")
    @Operation(summary = "Buscar aluno por CPF")
    public ResponseEntity<Object> buscarPorCpf(@PathVariable String cpf) {
        try {
            return ResponseEntity.ok(alunoService.buscarPorCpf(cpf));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }


    @PatchMapping("/{id}")
    @Operation(summary = "Atualizar dados do aluno", description = "Atualiza parcialmente os campos do aluno informado.")
    public ResponseEntity<Object> atualizar(@PathVariable Long id,
                                            @Valid @RequestBody AtualizarAlunoRequestDto request,
                                            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String erros = bindingResult.getFieldErrors().stream()
                    .map(e -> e.getField() + ": " + e.getDefaultMessage())
                    .collect(Collectors.joining("; "));
            return ResponseEntity.badRequest().body(erros);
        }
        try {
            return ResponseEntity.ok(alunoService.atualizar(id, request));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }


    @DeleteMapping("/{id}")
    @Operation(summary = "Remover aluno")
    public ResponseEntity<Object> deletar(@PathVariable Long id) {
        try {
            alunoService.deletar(id);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
