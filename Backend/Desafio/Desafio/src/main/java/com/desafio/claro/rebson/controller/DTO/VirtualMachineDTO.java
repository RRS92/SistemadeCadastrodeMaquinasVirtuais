package com.desafio.claro.rebson.controller.DTO;

import com.desafio.claro.rebson.model.VirtualMachine;
import com.desafio.claro.rebson.model.VmStatus;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
	    name = "VirtualMachineDTO",
	    description = "Objeto de transferência de dados que representa uma máquina virtual"
	)
public record VirtualMachineDTO(

		@Schema(
	            description = "Identificador único da máquina virtual",
	            example = "1",
	            accessMode = Schema.AccessMode.READ_ONLY
	        )
        Long id,

        @Schema(
                description = "Nome da máquina virtual",
                example = "vm-producao-01"
            )
        @NotBlank(message = "O nome da máquina é obrigatório")
        String name,

        @Schema(
                description = "Quantidade de CPUs atribuídas à máquina virtual",
                example = "4",
                minimum = "1"
            )
        @NotNull
        @Min(value = 1, message = "CPU deve ser maior que zero")
        Integer cpu,

        @Schema(
                description = "Quantidade de memória RAM em megabytes",
                example = "8192",
                minimum = "1"
            )
        @NotNull
        @Min(value = 1, message = "Memória deve ser maior que zero")
        Integer memoryMb,

        @Schema(
                description = "Espaço em disco em gigabytes",
                example = "100",
                minimum = "1"
            )
        @NotNull
        @Min(value = 1, message = "Disco deve ser maior que zero")
        Integer diskGb,

        @Schema(
                description = "Status atual da máquina virtual",
                example = "OFF",
                accessMode = Schema.AccessMode.READ_ONLY
            )
        VmStatus status
) {

    public VirtualMachineDTO(VirtualMachine vm) {
        this(
            vm.getId(),
            vm.getName(),
            vm.getCpu(),
            vm.getMemoryMb(),
            vm.getDiskGb(),
            vm.getStatus()
        );
    }
}
