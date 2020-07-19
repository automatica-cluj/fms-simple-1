import { MessageStatus } from 'app/shared/model/enumerations/message-status.model';

export interface IMessage {
  id?: number;
  subject?: string;
  content?: string;
  status?: MessageStatus;
  operatorWorkShiftId?: number;
}

export class Message implements IMessage {
  constructor(
    public id?: number,
    public subject?: string,
    public content?: string,
    public status?: MessageStatus,
    public operatorWorkShiftId?: number
  ) {}
}
