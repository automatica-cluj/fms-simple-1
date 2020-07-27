import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { OperatorNotificationService } from 'app/entities/operator-notification/operator-notification.service';
import { IOperatorNotification, OperatorNotification } from 'app/shared/model/operator-notification.model';
import { NotificationResponse } from 'app/shared/model/enumerations/notification-response.model';

describe('Service Tests', () => {
  describe('OperatorNotification Service', () => {
    let injector: TestBed;
    let service: OperatorNotificationService;
    let httpMock: HttpTestingController;
    let elemDefault: IOperatorNotification;
    let expectedResult: IOperatorNotification | IOperatorNotification[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(OperatorNotificationService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new OperatorNotification(0, currentDate, currentDate, NotificationResponse.ACCEPT);
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            receiveDate: currentDate.format(DATE_TIME_FORMAT),
            responseDate: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a OperatorNotification', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            receiveDate: currentDate.format(DATE_TIME_FORMAT),
            responseDate: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            receiveDate: currentDate,
            responseDate: currentDate,
          },
          returnedFromService
        );

        service.create(new OperatorNotification()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a OperatorNotification', () => {
        const returnedFromService = Object.assign(
          {
            receiveDate: currentDate.format(DATE_TIME_FORMAT),
            responseDate: currentDate.format(DATE_TIME_FORMAT),
            operatorResponse: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            receiveDate: currentDate,
            responseDate: currentDate,
          },
          returnedFromService
        );

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of OperatorNotification', () => {
        const returnedFromService = Object.assign(
          {
            receiveDate: currentDate.format(DATE_TIME_FORMAT),
            responseDate: currentDate.format(DATE_TIME_FORMAT),
            operatorResponse: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            receiveDate: currentDate,
            responseDate: currentDate,
          },
          returnedFromService
        );

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a OperatorNotification', () => {
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
