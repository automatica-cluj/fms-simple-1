import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IProductionArea, ProductionArea } from 'app/shared/model/production-area.model';
import { ProductionAreaService } from './production-area.service';
import { ProductionAreaComponent } from './production-area.component';
import { ProductionAreaDetailComponent } from './production-area-detail.component';
import { ProductionAreaUpdateComponent } from './production-area-update.component';

@Injectable({ providedIn: 'root' })
export class ProductionAreaResolve implements Resolve<IProductionArea> {
  constructor(private service: ProductionAreaService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IProductionArea> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((productionArea: HttpResponse<ProductionArea>) => {
          if (productionArea.body) {
            return of(productionArea.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new ProductionArea());
  }
}

export const productionAreaRoute: Routes = [
  {
    path: '',
    component: ProductionAreaComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'ifmSimple1App.productionArea.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: ProductionAreaDetailComponent,
    resolve: {
      productionArea: ProductionAreaResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'ifmSimple1App.productionArea.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: ProductionAreaUpdateComponent,
    resolve: {
      productionArea: ProductionAreaResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'ifmSimple1App.productionArea.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: ProductionAreaUpdateComponent,
    resolve: {
      productionArea: ProductionAreaResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'ifmSimple1App.productionArea.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
