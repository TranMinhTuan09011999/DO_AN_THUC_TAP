export class ChatMessageDto {
    user: string;
    message: string;
    dateTime: number;

    constructor(user: string, message: string, dateTime: number){
        this.user = user;
        this.message = message;
        this.dateTime = dateTime;
    }

}
