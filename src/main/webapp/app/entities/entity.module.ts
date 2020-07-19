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
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ]),
  ],
})
export class IfmSimple1EntityModule {}
