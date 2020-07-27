import { browser, ExpectedConditions as ec, protractor, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import {
  OperatorNotificationComponentsPage,
  OperatorNotificationDeleteDialog,
  OperatorNotificationUpdatePage,
} from './operator-notification.page-object';

const expect = chai.expect;

describe('OperatorNotification e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let operatorNotificationComponentsPage: OperatorNotificationComponentsPage;
  let operatorNotificationUpdatePage: OperatorNotificationUpdatePage;
  let operatorNotificationDeleteDialog: OperatorNotificationDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load OperatorNotifications', async () => {
    await navBarPage.goToEntity('operator-notification');
    operatorNotificationComponentsPage = new OperatorNotificationComponentsPage();
    await browser.wait(ec.visibilityOf(operatorNotificationComponentsPage.title), 5000);
    expect(await operatorNotificationComponentsPage.getTitle()).to.eq('ifmSimple1App.operatorNotification.home.title');
    await browser.wait(
      ec.or(ec.visibilityOf(operatorNotificationComponentsPage.entities), ec.visibilityOf(operatorNotificationComponentsPage.noResult)),
      1000
    );
  });

  it('should load create OperatorNotification page', async () => {
    await operatorNotificationComponentsPage.clickOnCreateButton();
    operatorNotificationUpdatePage = new OperatorNotificationUpdatePage();
    expect(await operatorNotificationUpdatePage.getPageTitle()).to.eq('ifmSimple1App.operatorNotification.home.createOrEditLabel');
    await operatorNotificationUpdatePage.cancel();
  });

  it('should create and save OperatorNotifications', async () => {
    const nbButtonsBeforeCreate = await operatorNotificationComponentsPage.countDeleteButtons();

    await operatorNotificationComponentsPage.clickOnCreateButton();

    await promise.all([
      operatorNotificationUpdatePage.setReceiveDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      operatorNotificationUpdatePage.setResponseDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      operatorNotificationUpdatePage.operatorResponseSelectLastOption(),
      operatorNotificationUpdatePage.operatorWorkShiftSelectLastOption(),
      operatorNotificationUpdatePage.factoryEventSelectLastOption(),
    ]);

    expect(await operatorNotificationUpdatePage.getReceiveDateInput()).to.contain(
      '2001-01-01T02:30',
      'Expected receiveDate value to be equals to 2000-12-31'
    );
    expect(await operatorNotificationUpdatePage.getResponseDateInput()).to.contain(
      '2001-01-01T02:30',
      'Expected responseDate value to be equals to 2000-12-31'
    );

    await operatorNotificationUpdatePage.save();
    expect(await operatorNotificationUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await operatorNotificationComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last OperatorNotification', async () => {
    const nbButtonsBeforeDelete = await operatorNotificationComponentsPage.countDeleteButtons();
    await operatorNotificationComponentsPage.clickOnLastDeleteButton();

    operatorNotificationDeleteDialog = new OperatorNotificationDeleteDialog();
    expect(await operatorNotificationDeleteDialog.getDialogTitle()).to.eq('ifmSimple1App.operatorNotification.delete.question');
    await operatorNotificationDeleteDialog.clickOnConfirmButton();

    expect(await operatorNotificationComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
