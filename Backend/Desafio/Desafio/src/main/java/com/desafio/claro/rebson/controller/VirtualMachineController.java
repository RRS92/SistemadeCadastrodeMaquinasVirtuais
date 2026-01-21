package com.desafio.claro.rebson.controller;

import com.desafio.claro.rebson.controller.DTO.VirtualMachineDTO;
import com.desafio.claro.rebson.model.VirtualMachine;
import com.desafio.claro.rebson.service.VirtualMachineService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vms")
@CrossOrigin(origins = "*")
public class VirtualMachineController {

    @Autowired
    private VirtualMachineService virtualMachineService;

    @PostMapping
    @Transactional
    public ResponseEntity<VirtualMachineDTO> create(
            @RequestBody @Valid VirtualMachineDTO dto) {

        VirtualMachine vm = virtualMachineService.create(dto);
        return ResponseEntity.ok(new VirtualMachineDTO(vm));
    }

    @GetMapping
    public ResponseEntity<List<VirtualMachineDTO>> findAll() {

        List<VirtualMachineDTO> vms = virtualMachineService.findAll()
                .stream()
                .map(VirtualMachineDTO::new)
                .toList();

        return ResponseEntity.ok(vms);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VirtualMachineDTO> findById(@PathVariable Long id) {

        VirtualMachine vm = virtualMachineService.findById(id);
        return ResponseEntity.ok(new VirtualMachineDTO(vm));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<VirtualMachineDTO> update(
            @PathVariable Long id,
            @RequestBody @Valid VirtualMachineDTO dto) {

        VirtualMachine vm = virtualMachineService.update(id, dto);
        return ResponseEntity.ok(new VirtualMachineDTO(vm));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        virtualMachineService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/start")
    @Transactional
    public ResponseEntity<VirtualMachineDTO> start(@PathVariable Long id) {

        VirtualMachine vm = virtualMachineService.start(id);
        return ResponseEntity.ok(new VirtualMachineDTO(vm));
    }

    @PatchMapping("/{id}/stop")
    @Transactional
    public ResponseEntity<VirtualMachineDTO> stop(@PathVariable Long id) {

        VirtualMachine vm = virtualMachineService.stop(id);
        return ResponseEntity.ok(new VirtualMachineDTO(vm));
    }
}
