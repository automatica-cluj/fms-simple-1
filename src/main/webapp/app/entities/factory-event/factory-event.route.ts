import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IFactoryEvent, FactoryEvent } from 'app/shared/model/factory-event.model';
import { FactoryEventService } from './factory-event.service';
import { FactoryEventComponent } from './factory-event.component';
import { FactoryEventDetailComponent } from './factory-event-detail.component';
import { FactoryEventUpdateComponent } from './factory-event-update.component';

@Injectable({ providedIn: 'root' })
export class FactoryEventResolve implements Resolve<IFactoryEvent> {
  constructor(private service: FactoryEventService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IFactoryEvent> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((factoryEvent: HttpResponse<FactoryEvent>) => {
          if (factoryEvent.body) {
            return of(factoryEvent.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new FactoryEvent());
  }
}

export const factoryEventRoute: Routes = [
  {
    path: '',
    component: FactoryEventComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'ifmSimple1App.factoryEvent.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: FactoryEventDetailComponent,
    resolve: {
      factoryEvent: FactoryEventResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'ifmSimple1App.factoryEvent.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: FactoryEventUpdateComponent,
    resolve: {
      factoryEvent: FactoryEventResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'ifmSimple1App.factoryEvent.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: FactoryEventUpdateComponent,
    resolve: {
      factoryEvent: FactoryEventResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'ifmSimple1App.factoryEvent.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
