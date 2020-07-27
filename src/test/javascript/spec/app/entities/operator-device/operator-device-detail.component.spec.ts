import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { IfmSimple1TestModule } from '../../../test.module';
import { OperatorDeviceDetailComponent } from 'app/entities/operator-device/operator-device-detail.component';
import { OperatorDevice } from 'app/shared/model/operator-device.model';

describe('Component Tests', () => {
  describe('OperatorDevice Management Detail Component', () => {
    let comp: OperatorDeviceDetailComponent;
    let fixture: ComponentFixture<OperatorDeviceDetailComponent>;
    const route = ({ data: of({ operatorDevice: new OperatorDevice(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [IfmSimple1TestModule],
        declarations: [OperatorDeviceDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(OperatorDeviceDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(OperatorDeviceDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load operatorDevice on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.operatorDevice).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
