export interface IProductionArea {
  id?: number;
  name?: string;
  operatorWorkShiftId?: number;
}

export class ProductionArea implements IProductionArea {
  constructor(public id?: number, public name?: string, public operatorWorkShiftId?: number) {}
}
