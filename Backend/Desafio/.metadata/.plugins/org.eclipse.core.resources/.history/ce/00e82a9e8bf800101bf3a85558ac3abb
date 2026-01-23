package com.desafio.claro.rebson.controller;

import com.desafio.claro.rebson.controller.DTO.VirtualMachineDTO;
import com.desafio.claro.rebson.model.VirtualMachine;
import com.desafio.claro.rebson.service.VirtualMachineService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;


import java.util.List;

@Tag(name = "Virtual Machines", description = "Gerenciamento de máquinas virtuais")
@RestController
@RequestMapping("/vms")
@CrossOrigin(origins = "*")
public class VirtualMachineController {

    @Autowired
    private VirtualMachineService virtualMachineService;

    @Operation(summary = "Criar uma nova máquina virtual",
            description = "Cria uma VM com status inicial OFF")
    @PostMapping
    @Transactional
    public ResponseEntity<VirtualMachineDTO> create(
            @RequestBody @Valid VirtualMachineDTO dto) {

        VirtualMachine vm = virtualMachineService.create(dto);
        return ResponseEntity.ok(new VirtualMachineDTO(vm));
    }

    @Operation(
    	    summary = "Listar máquinas virtuais",
    	    description = "Retorna a lista de todas as máquinas virtuais cadastradas")
    @GetMapping
    public ResponseEntity<List<VirtualMachineDTO>> findAll() {

        List<VirtualMachineDTO> vms = virtualMachineService.findAll()
                .stream()
                .map(VirtualMachineDTO::new)
                .toList();

        return ResponseEntity.ok(vms);
    }

    @Operation(
    	    summary = "Buscar máquina virtual por ID",
    	    description = "Retorna os dados de uma máquina virtual a partir do seu ID")
    @GetMapping("/{id}")
    public ResponseEntity<VirtualMachineDTO> findById(@PathVariable Long id) {

        VirtualMachine vm = virtualMachineService.findById(id);
        return ResponseEntity.ok(new VirtualMachineDTO(vm));
    }

    @Operation(
    	    summary = "Atualizar máquina virtual",
    	    description = "Atualiza os dados de uma máquina virtual existente"
    	)
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<VirtualMachineDTO> update(
            @PathVariable Long id,
            @RequestBody @Valid VirtualMachineDTO dto) {

        VirtualMachine vm = virtualMachineService.update(id, dto);
        return ResponseEntity.ok(new VirtualMachineDTO(vm));
    }

    @Operation(
    	    summary = "Remover máquina virtual",
    	    description = "Remove uma máquina virtual a partir do seu ID"
    	)
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        virtualMachineService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(
    	    summary = "Ligar máquina virtual",
    	    description = "Altera o status da máquina virtual para ON"
    	)
    @PatchMapping("/{id}/start")
    @Transactional
    public ResponseEntity<VirtualMachineDTO> start(@PathVariable Long id) {

        VirtualMachine vm = virtualMachineService.start(id);
        return ResponseEntity.ok(new VirtualMachineDTO(vm));
    }

    @Operation(
    	    summary = "Desligar máquina virtual",
    	    description = "Altera o status da máquina virtual para OFF"
    	)
    @PatchMapping("/{id}/stop")
    @Transactional
    public ResponseEntity<VirtualMachineDTO> stop(@PathVariable Long id) {

        VirtualMachine vm = virtualMachineService.stop(id);
        return ResponseEntity.ok(new VirtualMachineDTO(vm));
    }
}
