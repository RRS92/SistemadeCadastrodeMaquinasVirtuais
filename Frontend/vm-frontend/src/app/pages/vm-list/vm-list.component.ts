import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Router } from '@angular/router';
import { VirtualMachineService } from '../../services/virtual-machine.service';
import { VirtualMachine } from '../../models/virtual-machine.model';

@Component({
  selector: 'app-vm-list',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './vm-list.component.html'
})
export class VmListComponent implements OnInit {

  vms: VirtualMachine[] = [];

  constructor(
    private vmService: VirtualMachineService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.loadVms();
  }

  loadVms(): void {
    this.vmService.findAll().subscribe({
      next: (data: VirtualMachine[]) => this.vms = data,
      error: (err: Error) => console.error('Erro ao carregar VMs', err)
    });
  }

  editVm(id: number): void {
    this.router.navigate(['/vms/edit', id]);
  }

  deleteVm(id: number): void {
    if (confirm('Deseja realmente excluir esta máquina virtual?')) {
      this.vmService.delete(id).subscribe({
        next: () => this.loadVms(),
        error: err => console.error('Erro ao excluir VM', err)
      });
    }
  }

  /** Normaliza e verifica se a VM está ligada */
  isVmOn(vm: VirtualMachine): boolean {
    return vm.status?.toString().toUpperCase() === 'ON';
  }

  /** Alterna o status da VM */
  toggleStatus(vm: VirtualMachine): void {
    if (this.isVmOn(vm)) {
      this.vmService.stop(vm.id!).subscribe({
        next: () => this.loadVms(),
        error: err => console.error('Erro ao desligar VM', err)
      });
    } else {
      this.vmService.start(vm.id!).subscribe({
        next: () => this.loadVms(),
        error: err => console.error('Erro ao ligar VM', err)
      });
    }
  }
}
