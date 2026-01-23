import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule, FormBuilder, Validators } from '@angular/forms';
import { RouterModule, Router } from '@angular/router';
import { VirtualMachineService } from '../../services/virtual-machine.service';
import { VirtualMachine } from '../../models/virtual-machine.model';

@Component({
  selector: 'app-vm-create',
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule,
    RouterModule
  ],
  templateUrl: './vm-create.component.html'
})
export class VmCreateComponent {

  vmForm = this.fb.group({
    name: ['', Validators.required],
    cpu: [1, [Validators.required, Validators.min(1)]],
    memoryMb: [1, [Validators.required, Validators.min(1)]],
    diskGb: [1, [Validators.required, Validators.min(1)]],
  });

  constructor(
    private fb: FormBuilder,
    private vmService: VirtualMachineService,
    private router: Router
  ) {}

  submit(): void {
    if (this.vmForm.invalid) return;

    const vm: VirtualMachine = {
      name: this.vmForm.value.name!,
      cpu: this.vmForm.value.cpu!,
      memoryMb: this.vmForm.value.memoryMb!,
      diskGb: this.vmForm.value.diskGb!
    };

    this.vmService.create(vm).subscribe({
      next: () => this.router.navigate(['/vms']),
      error: err => console.error('Erro ao criar VM', err)
    });
  }
}
