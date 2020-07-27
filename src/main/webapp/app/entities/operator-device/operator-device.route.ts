import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IOperatorDevice, OperatorDevice } from 'app/shared/model/operator-device.model';
import { OperatorDeviceService } from './operator-device.service';
import { OperatorDeviceComponent } from './operator-device.component';
import { OperatorDeviceDetailComponent } from './operator-device-detail.component';
import { OperatorDeviceUpdateComponent } from './operator-device-update.component';

@Injectable({ providedIn: 'root' })
export class OperatorDeviceResolve implements Resolve<IOperatorDevice> {
  constructor(private service: OperatorDeviceService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IOperatorDevice> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((operatorDevice: HttpResponse<OperatorDevice>) => {
          if (operatorDevice.body) {
            return of(operatorDevice.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new OperatorDevice());
  }
}

export const operatorDeviceRoute: Routes = [
  {
    path: '',
    component: OperatorDeviceComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'ifmSimple1App.operatorDevice.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: OperatorDeviceDetailComponent,
    resolve: {
      operatorDevice: OperatorDeviceResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'ifmSimple1App.operatorDevice.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: OperatorDeviceUpdateComponent,
    resolve: {
      operatorDevice: OperatorDeviceResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'ifmSimple1App.operatorDevice.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: OperatorDeviceUpdateComponent,
    resolve: {
      operatorDevice: OperatorDeviceResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'ifmSimple1App.operatorDevice.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
