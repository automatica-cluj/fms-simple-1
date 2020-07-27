import { browser, ExpectedConditions as ec /* , protractor, promise */ } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import {
  FactoryEventComponentsPage,
  /* FactoryEventDeleteDialog, */
  FactoryEventUpdatePage,
} from './factory-event.page-object';

const expect = chai.expect;

describe('FactoryEvent e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let factoryEventComponentsPage: FactoryEventComponentsPage;
  let factoryEventUpdatePage: FactoryEventUpdatePage;
  /* let factoryEventDeleteDialog: FactoryEventDeleteDialog; */

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load FactoryEvents', async () => {
    await navBarPage.goToEntity('factory-event');
    factoryEventComponentsPage = new FactoryEventComponentsPage();
    await browser.wait(ec.visibilityOf(factoryEventComponentsPage.title), 5000);
    expect(await factoryEventComponentsPage.getTitle()).to.eq('ifmSimple1App.factoryEvent.home.title');
    await browser.wait(
      ec.or(ec.visibilityOf(factoryEventComponentsPage.entities), ec.visibilityOf(factoryEventComponentsPage.noResult)),
      1000
    );
  });

  it('should load create FactoryEvent page', async () => {
    await factoryEventComponentsPage.clickOnCreateButton();
    factoryEventUpdatePage = new FactoryEventUpdatePage();
    expect(await factoryEventUpdatePage.getPageTitle()).to.eq('ifmSimple1App.factoryEvent.home.createOrEditLabel');
    await factoryEventUpdatePage.cancel();
  });

  /* it('should create and save FactoryEvents', async () => {
        const nbButtonsBeforeCreate = await factoryEventComponentsPage.countDeleteButtons();

        await factoryEventComponentsPage.clickOnCreateButton();

        await promise.all([
            factoryEventUpdatePage.setSubjectInput('subject'),
            factoryEventUpdatePage.setBodyInput('body'),
            factoryEventUpdatePage.typeSelectLastOption(),
            factoryEventUpdatePage.setCreateDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
            factoryEventUpdatePage.setNotificationCountInput('5'),
            factoryEventUpdatePage.setNextNotificationDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
            factoryEventUpdatePage.processingStatusSelectLastOption(),
            factoryEventUpdatePage.productionAreaSelectLastOption(),
        ]);

        expect(await factoryEventUpdatePage.getSubjectInput()).to.eq('subject', 'Expected Subject value to be equals to subject');
        expect(await factoryEventUpdatePage.getBodyInput()).to.eq('body', 'Expected Body value to be equals to body');
        expect(await factoryEventUpdatePage.getCreateDateInput()).to.contain('2001-01-01T02:30', 'Expected createDate value to be equals to 2000-12-31');
        expect(await factoryEventUpdatePage.getNotificationCountInput()).to.eq('5', 'Expected notificationCount value to be equals to 5');
        expect(await factoryEventUpdatePage.getNextNotificationDateInput()).to.contain('2001-01-01T02:30', 'Expected nextNotificationDate value to be equals to 2000-12-31');

        await factoryEventUpdatePage.save();
        expect(await factoryEventUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

        expect(await factoryEventComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
    }); */

  /* it('should delete last FactoryEvent', async () => {
        const nbButtonsBeforeDelete = await factoryEventComponentsPage.countDeleteButtons();
        await factoryEventComponentsPage.clickOnLastDeleteButton();

        factoryEventDeleteDialog = new FactoryEventDeleteDialog();
        expect(await factoryEventDeleteDialog.getDialogTitle())
            .to.eq('ifmSimple1App.factoryEvent.delete.question');
        await factoryEventDeleteDialog.clickOnConfirmButton();

        expect(await factoryEventComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
    }); */

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
