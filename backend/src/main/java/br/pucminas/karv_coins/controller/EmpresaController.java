package br.pucminas.karv_coins.controller;

import br.pucminas.karv_coins.dto.request.AtualizarEmpresaRequestDto;
import br.pucminas.karv_coins.dto.request.CriarEmpresaRequestDto;
import br.pucminas.karv_coins.dto.response.EmpresaResponseDto;
import br.pucminas.karv_coins.service.EmpresaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
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
@RequestMapping("/empresas")
@Tag(name = "Empresas", description = "Cadastro, consulta e atualização de empresas parceiras")
public class EmpresaController {

    private final EmpresaService empresaService;

    public EmpresaController(EmpresaService empresaService) {
        this.empresaService = empresaService;
    }


    @PostMapping
    @SecurityRequirements
    @Operation(summary = "Cadastrar nova empresa", description = "Endpoint público de cadastro de empresa.")
    public ResponseEntity<Object> criar(@Valid @RequestBody CriarEmpresaRequestDto request,
                                        BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String erros = bindingResult.getFieldErrors().stream()
                    .map(e -> e.getField() + ": " + e.getDefaultMessage())
                    .collect(Collectors.joining("; "));
            return ResponseEntity.badRequest().body(erros);
        }
        try {
            EmpresaResponseDto response = empresaService.criar(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }


    @GetMapping
    @Operation(summary = "Listar todas as empresas")
    public ResponseEntity<List<EmpresaResponseDto>> listarTodos() {
        return ResponseEntity.ok(empresaService.listarTodos());
    }


    @GetMapping("/{id}")
    @Operation(summary = "Buscar empresa por ID")
    public ResponseEntity<Object> buscarPorId(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(empresaService.buscarPorId(id));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }


    @GetMapping("/cnpj/{cnpj}")
    @Operation(summary = "Buscar empresa por CNPJ")
    public ResponseEntity<Object> buscarPorCnpj(@PathVariable String cnpj) {
        try {
            return ResponseEntity.ok(empresaService.buscarPorCnpj(cnpj));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }


    @PatchMapping("/{id}")
    @Operation(summary = "Atualizar dados da empresa", description = "Atualiza parcialmente os campos da empresa informada.")
    public ResponseEntity<Object> atualizar(@PathVariable Long id,
                                            @Valid @RequestBody AtualizarEmpresaRequestDto request,
                                            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String erros = bindingResult.getFieldErrors().stream()
                    .map(e -> e.getField() + ": " + e.getDefaultMessage())
                    .collect(Collectors.joining("; "));
            return ResponseEntity.badRequest().body(erros);
        }
        try {
            return ResponseEntity.ok(empresaService.atualizar(id, request));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }
}
