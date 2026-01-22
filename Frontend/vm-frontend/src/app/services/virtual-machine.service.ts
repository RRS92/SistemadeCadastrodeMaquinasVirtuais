import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { VirtualMachine } from '../models/virtual-machine.model';

@Injectable({
  providedIn: 'root'
})
export class VirtualMachineService {

  private readonly apiUrl = 'http://localhost:8080/vms';

  constructor(private http: HttpClient) {}

  create(vm: VirtualMachine): Observable<VirtualMachine> {
    return this.http.post<VirtualMachine>(this.apiUrl, vm);
  }

  findAll(): Observable<VirtualMachine[]> {
    return this.http.get<VirtualMachine[]>(this.apiUrl);
  }

  findById(id: number): Observable<VirtualMachine> {
    return this.http.get<VirtualMachine>(`${this.apiUrl}/${id}`);
  }

  update(id: number, vm: VirtualMachine): Observable<VirtualMachine> {
    return this.http.put<VirtualMachine>(`${this.apiUrl}/${id}`, vm);
  }

  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }

  start(id: number): Observable<void> {
    return this.http.patch<void>(`${this.apiUrl}/${id}/start`, {});
  }

  stop(id: number): Observable<void> {
    return this.http.patch<void>(`${this.apiUrl}/${id}/stop`, {});
  }
}
