import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { IfmSimple1SharedModule } from 'app/shared/shared.module';
import { OperatorWorkShiftComponent } from './operator-work-shift.component';
import { OperatorWorkShiftDetailComponent } from './operator-work-shift-detail.component';
import { OperatorWorkShiftUpdateComponent } from './operator-work-shift-update.component';
import { OperatorWorkShiftDeleteDialogComponent } from './operator-work-shift-delete-dialog.component';
import { operatorWorkShiftRoute } from './operator-work-shift.route';

@NgModule({
  imports: [IfmSimple1SharedModule, RouterModule.forChild(operatorWorkShiftRoute)],
  declarations: [
    OperatorWorkShiftComponent,
    OperatorWorkShiftDetailComponent,
    OperatorWorkShiftUpdateComponent,
    OperatorWorkShiftDeleteDialogComponent,
  ],
  entryComponents: [OperatorWorkShiftDeleteDialogComponent],
})
export class IfmSimple1OperatorWorkShiftModule {}
