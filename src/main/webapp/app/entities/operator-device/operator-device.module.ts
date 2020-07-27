import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { IfmSimple1SharedModule } from 'app/shared/shared.module';
import { OperatorDeviceComponent } from './operator-device.component';
import { OperatorDeviceDetailComponent } from './operator-device-detail.component';
import { OperatorDeviceUpdateComponent } from './operator-device-update.component';
import { OperatorDeviceDeleteDialogComponent } from './operator-device-delete-dialog.component';
import { operatorDeviceRoute } from './operator-device.route';

@NgModule({
  imports: [IfmSimple1SharedModule, RouterModule.forChild(operatorDeviceRoute)],
  declarations: [
    OperatorDeviceComponent,
    OperatorDeviceDetailComponent,
    OperatorDeviceUpdateComponent,
    OperatorDeviceDeleteDialogComponent,
  ],
  entryComponents: [OperatorDeviceDeleteDialogComponent],
})
export class IfmSimple1OperatorDeviceModule {}
