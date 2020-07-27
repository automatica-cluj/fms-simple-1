import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'operator',
        loadChildren: () => import('./operator/operator.module').then(m => m.IfmSimple1OperatorModule),
      },
      {
        path: 'operator-work-shift',
        loadChildren: () => import('./operator-work-shift/operator-work-shift.module').then(m => m.IfmSimple1OperatorWorkShiftModule),
      },
      {
        path: 'device',
        loadChildren: () => import('./device/device.module').then(m => m.IfmSimple1DeviceModule),
      },
      {
        path: 'message',
        loadChildren: () => import('./message/message.module').then(m => m.IfmSimple1MessageModule),
      },
      {
        path: 'factory-event',
        loadChildren: () => import('./factory-event/factory-event.module').then(m => m.IfmSimple1FactoryEventModule),
      },
      {
        path: 'production-area',
        loadChildren: () => import('./production-area/production-area.module').then(m => m.IfmSimple1ProductionAreaModule),
      },
      {
        path: 'operator-device',
        loadChildren: () => import('./operator-device/operator-device.module').then(m => m.IfmSimple1OperatorDeviceModule),
      },
      {
        path: 'operator-notification',
        loadChildren: () =>
          import('./operator-notification/operator-notification.module').then(m => m.IfmSimple1OperatorNotificationModule),
      },
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ]),
  ],
})
export class IfmSimple1EntityModule {}
