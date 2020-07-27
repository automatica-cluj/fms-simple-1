import { Moment } from 'moment';
import { IOperatorNotification } from 'app/shared/model/operator-notification.model';
import { IProductionArea } from 'app/shared/model/production-area.model';
import { WorkShiftStatus } from 'app/shared/model/enumerations/work-shift-status.model';

export interface IOperatorWorkShift {
  id?: number;
  startDate?: Moment;
  endDate?: Moment;
  status?: WorkShiftStatus;
  deviceId?: number;
  operatorId?: number;
  notifications?: IOperatorNotification[];
  productionAreas?: IProductionArea[];
}

export class OperatorWorkShift implements IOperatorWorkShift {
  constructor(
    public id?: number,
    public startDate?: Moment,
    public endDate?: Moment,
    public status?: WorkShiftStatus,
    public deviceId?: number,
    public operatorId?: number,
    public notifications?: IOperatorNotification[],
    public productionAreas?: IProductionArea[]
  ) {}
}
