import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { IfmSimple1SharedModule } from 'app/shared/shared.module';
import { MessageComponent } from './message.component';
import { MessageDetailComponent } from './message-detail.component';
import { MessageUpdateComponent } from './message-update.component';
import { MessageDeleteDialogComponent } from './message-delete-dialog.component';
import { messageRoute } from './message.route';

@NgModule({
  imports: [IfmSimple1SharedModule, RouterModule.forChild(messageRoute)],
  declarations: [MessageComponent, MessageDetailComponent, MessageUpdateComponent, MessageDeleteDialogComponent],
  entryComponents: [MessageDeleteDialogComponent],
})
export class IfmSimple1MessageModule {}
