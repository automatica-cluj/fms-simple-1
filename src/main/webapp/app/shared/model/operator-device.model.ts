import { DeviceType } from 'app/shared/model/enumerations/device-type.model';

export interface IOperatorDevice {
  id?: number;
  name?: string;
  type?: DeviceType;
  registrationId?: string;
}

export class OperatorDevice implements IOperatorDevice {
  constructor(public id?: number, public name?: string, public type?: DeviceType, public registrationId?: string) {}
}
