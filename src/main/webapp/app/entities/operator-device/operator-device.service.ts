import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IOperatorDevice } from 'app/shared/model/operator-device.model';

type EntityResponseType = HttpResponse<IOperatorDevice>;
type EntityArrayResponseType = HttpResponse<IOperatorDevice[]>;

@Injectable({ providedIn: 'root' })
export class OperatorDeviceService {
  public resourceUrl = SERVER_API_URL + 'api/operator-devices';

  constructor(protected http: HttpClient) {}

  create(operatorDevice: IOperatorDevice): Observable<EntityResponseType> {
    return this.http.post<IOperatorDevice>(this.resourceUrl, operatorDevice, { observe: 'response' });
  }

  update(operatorDevice: IOperatorDevice): Observable<EntityResponseType> {
    return this.http.put<IOperatorDevice>(this.resourceUrl, operatorDevice, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IOperatorDevice>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IOperatorDevice[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
