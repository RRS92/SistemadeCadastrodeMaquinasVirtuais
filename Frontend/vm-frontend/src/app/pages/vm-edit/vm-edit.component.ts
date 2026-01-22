import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ActivatedRoute, Router } from '@angular/router';
import { ReactiveFormsModule, FormBuilder, Validators } from '@angular/forms';
import { VirtualMachineService } from '../../services/virtual-machine.service';
import { VirtualMachine } from '../../models/virtual-machine.model';

@Component({
  selector: 'app-vm-edit',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './vm-edit.component.html'
})
export class VmEditComponent implements OnInit {

  vmId!: number;

  vmForm = this.fb.group({
    name: ['', Validators.required],
    cpu: [1, [Validators.required, Validators.min(1)]],
    memoryMb: [1, [Validators.required, Validators.min(1)]],
    diskGb: [1, [Validators.required, Validators.min(1)]],
  });

  constructor(
    private route: ActivatedRoute,
    private fb: FormBuilder,
    private vmService: VirtualMachineService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.vmId = Number(this.route.snapshot.paramMap.get('id'));
    this.loadVm();
  }

  loadVm(): void {
    this.vmService.findById(this.vmId).subscribe(vm => {
      this.vmForm.patchValue(vm);
    });
  }

  submit(): void {
    if (this.vmForm.invalid) {
      return;
    }

    const vm: VirtualMachine = {
      name: this.vmForm.value.name!,
      cpu: this.vmForm.value.cpu!,
      memoryMb: this.vmForm.value.memoryMb!,
      diskGb: this.vmForm.value.diskGb!
    };

    this.vmService.update(this.vmId, vm).subscribe({
      next: () => this.router.navigate(['/vms']),
      error: err => console.error(err)
    });
  }
}
