import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IOperatorWorkShift, OperatorWorkShift } from 'app/shared/model/operator-work-shift.model';
import { OperatorWorkShiftService } from './operator-work-shift.service';
import { OperatorWorkShiftComponent } from './operator-work-shift.component';
import { OperatorWorkShiftDetailComponent } from './operator-work-shift-detail.component';
import { OperatorWorkShiftUpdateComponent } from './operator-work-shift-update.component';

@Injectable({ providedIn: 'root' })
export class OperatorWorkShiftResolve implements Resolve<IOperatorWorkShift> {
  constructor(private service: OperatorWorkShiftService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IOperatorWorkShift> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((operatorWorkShift: HttpResponse<OperatorWorkShift>) => {
          if (operatorWorkShift.body) {
            return of(operatorWorkShift.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new OperatorWorkShift());
  }
}

export const operatorWorkShiftRoute: Routes = [
  {
    path: '',
    component: OperatorWorkShiftComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'ifmSimple1App.operatorWorkShift.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: OperatorWorkShiftDetailComponent,
    resolve: {
      operatorWorkShift: OperatorWorkShiftResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'ifmSimple1App.operatorWorkShift.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: OperatorWorkShiftUpdateComponent,
    resolve: {
      operatorWorkShift: OperatorWorkShiftResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'ifmSimple1App.operatorWorkShift.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: OperatorWorkShiftUpdateComponent,
    resolve: {
      operatorWorkShift: OperatorWorkShiftResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'ifmSimple1App.operatorWorkShift.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
