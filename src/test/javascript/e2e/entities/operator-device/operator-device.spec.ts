import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { OperatorDeviceComponentsPage, OperatorDeviceDeleteDialog, OperatorDeviceUpdatePage } from './operator-device.page-object';

const expect = chai.expect;

describe('OperatorDevice e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let operatorDeviceComponentsPage: OperatorDeviceComponentsPage;
  let operatorDeviceUpdatePage: OperatorDeviceUpdatePage;
  let operatorDeviceDeleteDialog: OperatorDeviceDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load OperatorDevices', async () => {
    await navBarPage.goToEntity('operator-device');
    operatorDeviceComponentsPage = new OperatorDeviceComponentsPage();
    await browser.wait(ec.visibilityOf(operatorDeviceComponentsPage.title), 5000);
    expect(await operatorDeviceComponentsPage.getTitle()).to.eq('ifmSimple1App.operatorDevice.home.title');
    await browser.wait(
      ec.or(ec.visibilityOf(operatorDeviceComponentsPage.entities), ec.visibilityOf(operatorDeviceComponentsPage.noResult)),
      1000
    );
  });

  it('should load create OperatorDevice page', async () => {
    await operatorDeviceComponentsPage.clickOnCreateButton();
    operatorDeviceUpdatePage = new OperatorDeviceUpdatePage();
    expect(await operatorDeviceUpdatePage.getPageTitle()).to.eq('ifmSimple1App.operatorDevice.home.createOrEditLabel');
    await operatorDeviceUpdatePage.cancel();
  });

  it('should create and save OperatorDevices', async () => {
    const nbButtonsBeforeCreate = await operatorDeviceComponentsPage.countDeleteButtons();

    await operatorDeviceComponentsPage.clickOnCreateButton();

    await promise.all([
      operatorDeviceUpdatePage.setNameInput('name'),
      operatorDeviceUpdatePage.typeSelectLastOption(),
      operatorDeviceUpdatePage.setRegistrationIdInput('registrationId'),
    ]);

    expect(await operatorDeviceUpdatePage.getNameInput()).to.eq('name', 'Expected Name value to be equals to name');
    expect(await operatorDeviceUpdatePage.getRegistrationIdInput()).to.eq(
      'registrationId',
      'Expected RegistrationId value to be equals to registrationId'
    );

    await operatorDeviceUpdatePage.save();
    expect(await operatorDeviceUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await operatorDeviceComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last OperatorDevice', async () => {
    const nbButtonsBeforeDelete = await operatorDeviceComponentsPage.countDeleteButtons();
    await operatorDeviceComponentsPage.clickOnLastDeleteButton();

    operatorDeviceDeleteDialog = new OperatorDeviceDeleteDialog();
    expect(await operatorDeviceDeleteDialog.getDialogTitle()).to.eq('ifmSimple1App.operatorDevice.delete.question');
    await operatorDeviceDeleteDialog.clickOnConfirmButton();

    expect(await operatorDeviceComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
