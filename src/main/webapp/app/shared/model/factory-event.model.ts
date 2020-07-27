import { Moment } from 'moment';
import { IOperatorNotification } from 'app/shared/model/operator-notification.model';
import { EventType } from 'app/shared/model/enumerations/event-type.model';
import { ProcessingStatus } from 'app/shared/model/enumerations/processing-status.model';

export interface IFactoryEvent {
  id?: number;
  subject?: string;
  body?: string;
  type?: EventType;
  createDate?: Moment;
  notificationCount?: number;
  nextNotificationDate?: Moment;
  processingStatus?: ProcessingStatus;
  productionAreaId?: number;
  notifications?: IOperatorNotification[];
}

export class FactoryEvent implements IFactoryEvent {
  constructor(
    public id?: number,
    public subject?: string,
    public body?: string,
    public type?: EventType,
    public createDate?: Moment,
    public notificationCount?: number,
    public nextNotificationDate?: Moment,
    public processingStatus?: ProcessingStatus,
    public productionAreaId?: number,
    public notifications?: IOperatorNotification[]
  ) {}
}
