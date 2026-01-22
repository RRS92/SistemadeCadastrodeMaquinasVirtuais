import { Component, OnInit } from '@angular/core';
import { VirtualMachineService } from '../../services/virtual-machine.service';
import { VirtualMachine } from '../../models/virtual-machine.model';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-vm-list',
  standalone: true,
  imports: [CommonModule],
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
  next: (data: VirtualMachine[]) => {
    this.vms = data;
  },
  error: (err: Error) => {
    console.error('Erro ao carregar VMs', err);
  }
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
}
