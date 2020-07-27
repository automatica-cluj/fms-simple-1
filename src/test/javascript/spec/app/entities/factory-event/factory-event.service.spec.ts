import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { FactoryEventService } from 'app/entities/factory-event/factory-event.service';
import { IFactoryEvent, FactoryEvent } from 'app/shared/model/factory-event.model';
import { EventType } from 'app/shared/model/enumerations/event-type.model';
import { ProcessingStatus } from 'app/shared/model/enumerations/processing-status.model';

describe('Service Tests', () => {
  describe('FactoryEvent Service', () => {
    let injector: TestBed;
    let service: FactoryEventService;
    let httpMock: HttpTestingController;
    let elemDefault: IFactoryEvent;
    let expectedResult: IFactoryEvent | IFactoryEvent[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(FactoryEventService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new FactoryEvent(0, 'AAAAAAA', 'AAAAAAA', EventType.MACHINE_EVENT, currentDate, 0, currentDate, ProcessingStatus.NEW);
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            createDate: currentDate.format(DATE_TIME_FORMAT),
            nextNotificationDate: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a FactoryEvent', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            createDate: currentDate.format(DATE_TIME_FORMAT),
            nextNotificationDate: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            createDate: currentDate,
            nextNotificationDate: currentDate,
          },
          returnedFromService
        );

        service.create(new FactoryEvent()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a FactoryEvent', () => {
        const returnedFromService = Object.assign(
          {
            subject: 'BBBBBB',
            body: 'BBBBBB',
            type: 'BBBBBB',
            createDate: currentDate.format(DATE_TIME_FORMAT),
            notificationCount: 1,
            nextNotificationDate: currentDate.format(DATE_TIME_FORMAT),
            processingStatus: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            createDate: currentDate,
            nextNotificationDate: currentDate,
          },
          returnedFromService
        );

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of FactoryEvent', () => {
        const returnedFromService = Object.assign(
          {
            subject: 'BBBBBB',
            body: 'BBBBBB',
            type: 'BBBBBB',
            createDate: currentDate.format(DATE_TIME_FORMAT),
            notificationCount: 1,
            nextNotificationDate: currentDate.format(DATE_TIME_FORMAT),
            processingStatus: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            createDate: currentDate,
            nextNotificationDate: currentDate,
          },
          returnedFromService
        );

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a FactoryEvent', () => {
        service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
