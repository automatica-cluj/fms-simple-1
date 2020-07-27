import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { IfmSimple1SharedModule } from 'app/shared/shared.module';
import { OperatorNotificationComponent } from './operator-notification.component';
import { OperatorNotificationDetailComponent } from './operator-notification-detail.component';
import { OperatorNotificationUpdateComponent } from './operator-notification-update.component';
import { OperatorNotificationDeleteDialogComponent } from './operator-notification-delete-dialog.component';
import { operatorNotificationRoute } from './operator-notification.route';

@NgModule({
  imports: [IfmSimple1SharedModule, RouterModule.forChild(operatorNotificationRoute)],
  declarations: [
    OperatorNotificationComponent,
    OperatorNotificationDetailComponent,
    OperatorNotificationUpdateComponent,
    OperatorNotificationDeleteDialogComponent,
  ],
  entryComponents: [OperatorNotificationDeleteDialogComponent],
})
export class IfmSimple1OperatorNotificationModule {}
