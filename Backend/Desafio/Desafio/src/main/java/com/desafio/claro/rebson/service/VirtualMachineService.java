package com.desafio.claro.rebson.service;

import com.desafio.claro.rebson.controller.DTO.VirtualMachineDTO;
import com.desafio.claro.rebson.model.VirtualMachine;
import com.desafio.claro.rebson.model.VmStatus;
import com.desafio.claro.rebson.repository.VirtualMachineRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VirtualMachineService {

    @Autowired
    private VirtualMachineRepository repository;

    public VirtualMachine create(VirtualMachineDTO dto) {

        VirtualMachine vm = new VirtualMachine();
        vm.setName(dto.name());
        vm.setCpu(dto.cpu());
        vm.setMemoryMb(dto.memoryMb());
        vm.setDiskGb(dto.diskGb());
        vm.setStatus(VmStatus.Off);

        return repository.save(vm);
    }

    public List<VirtualMachine> findAll() {
        return repository.findAll();
    }

    public VirtualMachine findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Máquina virtual não encontrada"));
    }

    public VirtualMachine update(Long id, VirtualMachineDTO dto) {

        VirtualMachine vm = findById(id);

        vm.setName(dto.name());
        vm.setCpu(dto.cpu());
        vm.setMemoryMb(dto.memoryMb());
        vm.setDiskGb(dto.diskGb());

        return repository.save(vm);
    }

    public void delete(Long id) {
        VirtualMachine vm = findById(id);
        repository.delete(vm);
    }

    public VirtualMachine start(Long id) {

        VirtualMachine vm = findById(id);

        if (vm.getStatus() == VmStatus.On) {
            throw new IllegalStateException("A máquina virtual já está ligada");
        }

        vm.setStatus(VmStatus.On);
        return repository.save(vm);
    }

    public VirtualMachine stop(Long id) {

        VirtualMachine vm = findById(id);

        if (vm.getStatus() == VmStatus.Off) {
            throw new IllegalStateException("A máquina virtual já está desligada");
        }

        vm.setStatus(VmStatus.Off);
        return repository.save(vm);
    }
}
