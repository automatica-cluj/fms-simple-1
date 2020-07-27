export interface IOperator {
  id?: number;
  name?: string;
}

export class Operator implements IOperator {
  constructor(public id?: number, public name?: string) {}
}
