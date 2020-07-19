import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IOperatorWorkShift } from 'app/shared/model/operator-work-shift.model';

type EntityResponseType = HttpResponse<IOperatorWorkShift>;
type EntityArrayResponseType = HttpResponse<IOperatorWorkShift[]>;

@Injectable({ providedIn: 'root' })
export class OperatorWorkShiftService {
  public resourceUrl = SERVER_API_URL + 'api/operator-work-shifts';

  constructor(protected http: HttpClient) {}

  create(operatorWorkShift: IOperatorWorkShift): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(operatorWorkShift);
    return this.http
      .post<IOperatorWorkShift>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(operatorWorkShift: IOperatorWorkShift): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(operatorWorkShift);
    return this.http
      .put<IOperatorWorkShift>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IOperatorWorkShift>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IOperatorWorkShift[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(operatorWorkShift: IOperatorWorkShift): IOperatorWorkShift {
    const copy: IOperatorWorkShift = Object.assign({}, operatorWorkShift, {
      startDate: operatorWorkShift.startDate && operatorWorkShift.startDate.isValid() ? operatorWorkShift.startDate.toJSON() : undefined,
      endDate: operatorWorkShift.endDate && operatorWorkShift.endDate.isValid() ? operatorWorkShift.endDate.toJSON() : undefined,
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.startDate = res.body.startDate ? moment(res.body.startDate) : undefined;
      res.body.endDate = res.body.endDate ? moment(res.body.endDate) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((operatorWorkShift: IOperatorWorkShift) => {
        operatorWorkShift.startDate = operatorWorkShift.startDate ? moment(operatorWorkShift.startDate) : undefined;
        operatorWorkShift.endDate = operatorWorkShift.endDate ? moment(operatorWorkShift.endDate) : undefined;
      });
    }
    return res;
  }
}
