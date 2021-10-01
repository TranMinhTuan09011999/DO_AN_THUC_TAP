import { Employee } from "../model/employee";

export class OrderResponse {
    orderId!: string;
    dateOfOrder!: string;
    firstnameOfReveiver!: string;
    lastnameOfReveiver!: string;
    addressOfReceiver!: string;
    phoneOfReceiver!: string;
    status!: number;
    deliveryDate!: string;
    employee!: Employee;
    employeeDelivery!: Employee;
    payment!: number;
}
