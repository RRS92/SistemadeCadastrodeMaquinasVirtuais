import { Routes } from '@angular/router';
import { VmListComponent } from './pages/vm-list/vm-list.component';
import { VmCreateComponent } from './pages/vm-create/vm-create.component';
import { VmEditComponent } from './pages/vm-edit/vm-edit.component';

export const routes: Routes = [
  { path: 'vms', component: VmListComponent },
  { path: 'vms/create', component: VmCreateComponent },
  { path: 'vms/edit/:id', component: VmEditComponent },
  { path: '', redirectTo: 'vms', pathMatch: 'full' }
];
