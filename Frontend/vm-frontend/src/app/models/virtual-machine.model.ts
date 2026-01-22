export interface VirtualMachine {
  id?: number;
  name: string;
  cpu: number;
  memoryMb: number;
  diskGb: number;
  status?: 'ON' | 'OFF';
}
