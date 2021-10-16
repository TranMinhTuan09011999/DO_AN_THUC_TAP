import { Customer } from "./customer";
import { Employee } from "./employee";

export class Chat {
    chatId!: number;
    message!: string;
    time!: Date;
    customerChat!: Customer;
    employeeChat!: Employee;
    customerToChat!: Customer;
}
