export interface IDevice {
  id?: number;
  registrationId?: string;
}

export class Device implements IDevice {
  constructor(public id?: number, public registrationId?: string) {}
}
