import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { IfmSimple1TestModule } from '../../../test.module';
import { FactoryEventDetailComponent } from 'app/entities/factory-event/factory-event-detail.component';
import { FactoryEvent } from 'app/shared/model/factory-event.model';

describe('Component Tests', () => {
  describe('FactoryEvent Management Detail Component', () => {
    let comp: FactoryEventDetailComponent;
    let fixture: ComponentFixture<FactoryEventDetailComponent>;
    const route = ({ data: of({ factoryEvent: new FactoryEvent(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [IfmSimple1TestModule],
        declarations: [FactoryEventDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(FactoryEventDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(FactoryEventDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load factoryEvent on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.factoryEvent).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
