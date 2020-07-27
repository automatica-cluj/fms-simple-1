import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IProductionArea } from 'app/shared/model/production-area.model';

type EntityResponseType = HttpResponse<IProductionArea>;
type EntityArrayResponseType = HttpResponse<IProductionArea[]>;

@Injectable({ providedIn: 'root' })
export class ProductionAreaService {
  public resourceUrl = SERVER_API_URL + 'api/production-areas';

  constructor(protected http: HttpClient) {}

  create(productionArea: IProductionArea): Observable<EntityResponseType> {
    return this.http.post<IProductionArea>(this.resourceUrl, productionArea, { observe: 'response' });
  }

  update(productionArea: IProductionArea): Observable<EntityResponseType> {
    return this.http.put<IProductionArea>(this.resourceUrl, productionArea, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IProductionArea>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IProductionArea[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
