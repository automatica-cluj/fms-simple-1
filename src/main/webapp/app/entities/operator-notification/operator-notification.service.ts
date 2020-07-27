import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IOperatorNotification } from 'app/shared/model/operator-notification.model';

type EntityResponseType = HttpResponse<IOperatorNotification>;
type EntityArrayResponseType = HttpResponse<IOperatorNotification[]>;

@Injectable({ providedIn: 'root' })
export class OperatorNotificationService {
  public resourceUrl = SERVER_API_URL + 'api/operator-notifications';

  constructor(protected http: HttpClient) {}

  create(operatorNotification: IOperatorNotification): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(operatorNotification);
    return this.http
      .post<IOperatorNotification>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(operatorNotification: IOperatorNotification): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(operatorNotification);
    return this.http
      .put<IOperatorNotification>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IOperatorNotification>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IOperatorNotification[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(operatorNotification: IOperatorNotification): IOperatorNotification {
    const copy: IOperatorNotification = Object.assign({}, operatorNotification, {
      receiveDate:
        operatorNotification.receiveDate && operatorNotification.receiveDate.isValid()
          ? operatorNotification.receiveDate.toJSON()
          : undefined,
      responseDate:
        operatorNotification.responseDate && operatorNotification.responseDate.isValid()
          ? operatorNotification.responseDate.toJSON()
          : undefined,
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.receiveDate = res.body.receiveDate ? moment(res.body.receiveDate) : undefined;
      res.body.responseDate = res.body.responseDate ? moment(res.body.responseDate) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((operatorNotification: IOperatorNotification) => {
        operatorNotification.receiveDate = operatorNotification.receiveDate ? moment(operatorNotification.receiveDate) : undefined;
        operatorNotification.responseDate = operatorNotification.responseDate ? moment(operatorNotification.responseDate) : undefined;
      });
    }
    return res;
  }
}
