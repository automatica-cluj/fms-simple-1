import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { IfmSimple1SharedModule } from 'app/shared/shared.module';
import { FactoryEventComponent } from './factory-event.component';
import { FactoryEventDetailComponent } from './factory-event-detail.component';
import { FactoryEventUpdateComponent } from './factory-event-update.component';
import { FactoryEventDeleteDialogComponent } from './factory-event-delete-dialog.component';
import { factoryEventRoute } from './factory-event.route';

@NgModule({
  imports: [IfmSimple1SharedModule, RouterModule.forChild(factoryEventRoute)],
  declarations: [FactoryEventComponent, FactoryEventDetailComponent, FactoryEventUpdateComponent, FactoryEventDeleteDialogComponent],
  entryComponents: [FactoryEventDeleteDialogComponent],
})
export class IfmSimple1FactoryEventModule {}
