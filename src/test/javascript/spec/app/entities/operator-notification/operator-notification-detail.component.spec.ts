import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { IfmSimple1TestModule } from '../../../test.module';
import { OperatorNotificationDetailComponent } from 'app/entities/operator-notification/operator-notification-detail.component';
import { OperatorNotification } from 'app/shared/model/operator-notification.model';

describe('Component Tests', () => {
  describe('OperatorNotification Management Detail Component', () => {
    let comp: OperatorNotificationDetailComponent;
    let fixture: ComponentFixture<OperatorNotificationDetailComponent>;
    const route = ({ data: of({ operatorNotification: new OperatorNotification(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [IfmSimple1TestModule],
        declarations: [OperatorNotificationDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(OperatorNotificationDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(OperatorNotificationDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load operatorNotification on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.operatorNotification).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
