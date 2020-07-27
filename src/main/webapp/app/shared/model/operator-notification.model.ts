import { Moment } from 'moment';
import { NotificationResponse } from 'app/shared/model/enumerations/notification-response.model';

export interface IOperatorNotification {
  id?: number;
  receiveDate?: Moment;
  responseDate?: Moment;
  operatorResponse?: NotificationResponse;
  operatorWorkShiftId?: number;
  factoryEventId?: number;
}

export class OperatorNotification implements IOperatorNotification {
  constructor(
    public id?: number,
    public receiveDate?: Moment,
    public responseDate?: Moment,
    public operatorResponse?: NotificationResponse,
    public operatorWorkShiftId?: number,
    public factoryEventId?: number
  ) {}
}
