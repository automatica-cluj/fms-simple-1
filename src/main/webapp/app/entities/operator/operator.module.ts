import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { IfmSimple1SharedModule } from 'app/shared/shared.module';
import { OperatorComponent } from './operator.component';
import { OperatorDetailComponent } from './operator-detail.component';
import { OperatorUpdateComponent } from './operator-update.component';
import { OperatorDeleteDialogComponent } from './operator-delete-dialog.component';
import { operatorRoute } from './operator.route';

@NgModule({
  imports: [IfmSimple1SharedModule, RouterModule.forChild(operatorRoute)],
  declarations: [OperatorComponent, OperatorDetailComponent, OperatorUpdateComponent, OperatorDeleteDialogComponent],
  entryComponents: [OperatorDeleteDialogComponent],
})
export class IfmSimple1OperatorModule {}
