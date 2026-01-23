package com.desafio.claro.rebson.controller;

import com.desafio.claro.rebson.controller.DTO.VirtualMachineDTO;
import com.desafio.claro.rebson.model.VirtualMachine;
import com.desafio.claro.rebson.model.VmStatus;
import com.desafio.claro.rebson.service.VirtualMachineService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class VirtualMachineControllerTest {

    private MockMvc mockMvc;

    @Mock
    private VirtualMachineService virtualMachineService;

    @InjectMocks
    private VirtualMachineController controller;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(controller)
                .build();
    }

    @Test
    void deveCriarMaquinaVirtual() throws Exception {

        VirtualMachine vm = new VirtualMachine();
        vm.setId(1L);
        vm.setName("VM Test");
        vm.setCpu(2);
        vm.setMemoryMb(2048);
        vm.setDiskGb(50);
        vm.setStatus(VmStatus.Off);

        when(virtualMachineService.create(any(VirtualMachineDTO.class)))
                .thenReturn(vm);

        String json = """
                {
                  "name": "VM Test",
                  "cpu": 2,
                  "memoryMb": 2048,
                  "diskGb": 50
                }
                """;

        mockMvc.perform(post("/vms")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk());
    }

    @Test
    void deveListarMaquinasVirtuais() throws Exception {

        when(virtualMachineService.findAll())
                .thenReturn(java.util.List.of());

        mockMvc.perform(get("/vms"))
                .andExpect(status().isOk());
    }

    @Test
    void deveBuscarMaquinaVirtualPorId() throws Exception {

        VirtualMachine vm = new VirtualMachine();
        vm.setId(1L);

        when(virtualMachineService.findById(1L))
                .thenReturn(vm);

        mockMvc.perform(get("/vms/1"))
                .andExpect(status().isOk());
    }

    @Test
    void deveLigarMaquinaVirtual() throws Exception {

        VirtualMachine vm = new VirtualMachine();
        vm.setStatus(VmStatus.On);

        when(virtualMachineService.start(anyLong()))
                .thenReturn(vm);

        mockMvc.perform(patch("/vms/1/start"))
                .andExpect(status().isOk());
    }

    @Test
    void deveDesligarMaquinaVirtual() throws Exception {

        VirtualMachine vm = new VirtualMachine();
        vm.setStatus(VmStatus.Off);

        when(virtualMachineService.stop(anyLong()))
                .thenReturn(vm);

        mockMvc.perform(patch("/vms/1/stop"))
                .andExpect(status().isOk());
    }

    @Test
    void deveDeletarMaquinaVirtual() throws Exception {

        mockMvc.perform(delete("/vms/1"))
                .andExpect(status().isOk());
    }
}
