import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { DeviceComponentsPage, DeviceDeleteDialog, DeviceUpdatePage } from './device.page-object';

const expect = chai.expect;

describe('Device e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let deviceComponentsPage: DeviceComponentsPage;
  let deviceUpdatePage: DeviceUpdatePage;
  let deviceDeleteDialog: DeviceDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load Devices', async () => {
    await navBarPage.goToEntity('device');
    deviceComponentsPage = new DeviceComponentsPage();
    await browser.wait(ec.visibilityOf(deviceComponentsPage.title), 5000);
    expect(await deviceComponentsPage.getTitle()).to.eq('ifmSimple1App.device.home.title');
    await browser.wait(ec.or(ec.visibilityOf(deviceComponentsPage.entities), ec.visibilityOf(deviceComponentsPage.noResult)), 1000);
  });

  it('should load create Device page', async () => {
    await deviceComponentsPage.clickOnCreateButton();
    deviceUpdatePage = new DeviceUpdatePage();
    expect(await deviceUpdatePage.getPageTitle()).to.eq('ifmSimple1App.device.home.createOrEditLabel');
    await deviceUpdatePage.cancel();
  });

  it('should create and save Devices', async () => {
    const nbButtonsBeforeCreate = await deviceComponentsPage.countDeleteButtons();

    await deviceComponentsPage.clickOnCreateButton();

    await promise.all([deviceUpdatePage.setRegistrationIdInput('registrationId')]);

    expect(await deviceUpdatePage.getRegistrationIdInput()).to.eq(
      'registrationId',
      'Expected RegistrationId value to be equals to registrationId'
    );

    await deviceUpdatePage.save();
    expect(await deviceUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await deviceComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last Device', async () => {
    const nbButtonsBeforeDelete = await deviceComponentsPage.countDeleteButtons();
    await deviceComponentsPage.clickOnLastDeleteButton();

    deviceDeleteDialog = new DeviceDeleteDialog();
    expect(await deviceDeleteDialog.getDialogTitle()).to.eq('ifmSimple1App.device.delete.question');
    await deviceDeleteDialog.clickOnConfirmButton();

    expect(await deviceComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
