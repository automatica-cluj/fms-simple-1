import { browser, ExpectedConditions as ec, protractor, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import {
  OperatorWorkShiftComponentsPage,
  OperatorWorkShiftDeleteDialog,
  OperatorWorkShiftUpdatePage,
} from './operator-work-shift.page-object';

const expect = chai.expect;

describe('OperatorWorkShift e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let operatorWorkShiftComponentsPage: OperatorWorkShiftComponentsPage;
  let operatorWorkShiftUpdatePage: OperatorWorkShiftUpdatePage;
  let operatorWorkShiftDeleteDialog: OperatorWorkShiftDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load OperatorWorkShifts', async () => {
    await navBarPage.goToEntity('operator-work-shift');
    operatorWorkShiftComponentsPage = new OperatorWorkShiftComponentsPage();
    await browser.wait(ec.visibilityOf(operatorWorkShiftComponentsPage.title), 5000);
    expect(await operatorWorkShiftComponentsPage.getTitle()).to.eq('ifmSimple1App.operatorWorkShift.home.title');
    await browser.wait(
      ec.or(ec.visibilityOf(operatorWorkShiftComponentsPage.entities), ec.visibilityOf(operatorWorkShiftComponentsPage.noResult)),
      1000
    );
  });

  it('should load create OperatorWorkShift page', async () => {
    await operatorWorkShiftComponentsPage.clickOnCreateButton();
    operatorWorkShiftUpdatePage = new OperatorWorkShiftUpdatePage();
    expect(await operatorWorkShiftUpdatePage.getPageTitle()).to.eq('ifmSimple1App.operatorWorkShift.home.createOrEditLabel');
    await operatorWorkShiftUpdatePage.cancel();
  });

  it('should create and save OperatorWorkShifts', async () => {
    const nbButtonsBeforeCreate = await operatorWorkShiftComponentsPage.countDeleteButtons();

    await operatorWorkShiftComponentsPage.clickOnCreateButton();

    await promise.all([
      operatorWorkShiftUpdatePage.setLocationInput('location'),
      operatorWorkShiftUpdatePage.setStartDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      operatorWorkShiftUpdatePage.setEndDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      operatorWorkShiftUpdatePage.deviceSelectLastOption(),
      operatorWorkShiftUpdatePage.operatorSelectLastOption(),
    ]);

    expect(await operatorWorkShiftUpdatePage.getLocationInput()).to.eq('location', 'Expected Location value to be equals to location');
    expect(await operatorWorkShiftUpdatePage.getStartDateInput()).to.contain(
      '2001-01-01T02:30',
      'Expected startDate value to be equals to 2000-12-31'
    );
    expect(await operatorWorkShiftUpdatePage.getEndDateInput()).to.contain(
      '2001-01-01T02:30',
      'Expected endDate value to be equals to 2000-12-31'
    );

    await operatorWorkShiftUpdatePage.save();
    expect(await operatorWorkShiftUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await operatorWorkShiftComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last OperatorWorkShift', async () => {
    const nbButtonsBeforeDelete = await operatorWorkShiftComponentsPage.countDeleteButtons();
    await operatorWorkShiftComponentsPage.clickOnLastDeleteButton();

    operatorWorkShiftDeleteDialog = new OperatorWorkShiftDeleteDialog();
    expect(await operatorWorkShiftDeleteDialog.getDialogTitle()).to.eq('ifmSimple1App.operatorWorkShift.delete.question');
    await operatorWorkShiftDeleteDialog.clickOnConfirmButton();

    expect(await operatorWorkShiftComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
