export class ChatRequest {
    message!: string;
    userId!: string;
    toUserId!: string;
    employeeId!: string;
    time!: Date;

    constructor(message: string, userId: string, toUserId: string, employeeId: string, time: Date){
        this.message = message;
        this.userId = userId;
        this.toUserId = toUserId;
        this.employeeId = employeeId;
        this.time = time;
    }
}
