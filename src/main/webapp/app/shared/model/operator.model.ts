export interface IOperator {
  id?: number;
  externalId?: string;
  name?: string;
}

export class Operator implements IOperator {
  constructor(public id?: number, public externalId?: string, public name?: string) {}
}
