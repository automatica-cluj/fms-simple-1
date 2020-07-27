import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { IfmSimple1SharedModule } from 'app/shared/shared.module';
import { ProductionAreaComponent } from './production-area.component';
import { ProductionAreaDetailComponent } from './production-area-detail.component';
import { ProductionAreaUpdateComponent } from './production-area-update.component';
import { ProductionAreaDeleteDialogComponent } from './production-area-delete-dialog.component';
import { productionAreaRoute } from './production-area.route';

@NgModule({
  imports: [IfmSimple1SharedModule, RouterModule.forChild(productionAreaRoute)],
  declarations: [
    ProductionAreaComponent,
    ProductionAreaDetailComponent,
    ProductionAreaUpdateComponent,
    ProductionAreaDeleteDialogComponent,
  ],
  entryComponents: [ProductionAreaDeleteDialogComponent],
})
export class IfmSimple1ProductionAreaModule {}
