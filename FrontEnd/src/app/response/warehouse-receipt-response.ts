import { Employee } from "../model/employee";

export class WarehouseReceiptResponse {
    warehouseReceiptId!: string;
    dateOfIssue!: string;
    amount!: number;
    employee!: Employee;
}
