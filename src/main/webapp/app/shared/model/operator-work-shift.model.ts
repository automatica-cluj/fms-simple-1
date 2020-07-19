import { Moment } from 'moment';
import { IMessage } from 'app/shared/model/message.model';

export interface IOperatorWorkShift {
  id?: number;
  location?: string;
  startDate?: Moment;
  endDate?: Moment;
  deviceId?: number;
  operatorId?: number;
  messages?: IMessage[];
}

export class OperatorWorkShift implements IOperatorWorkShift {
  constructor(
    public id?: number,
    public location?: string,
    public startDate?: Moment,
    public endDate?: Moment,
    public deviceId?: number,
    public operatorId?: number,
    public messages?: IMessage[]
  ) {}
}
