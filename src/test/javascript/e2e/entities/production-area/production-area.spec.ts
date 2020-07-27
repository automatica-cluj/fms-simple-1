import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { ProductionAreaComponentsPage, ProductionAreaDeleteDialog, ProductionAreaUpdatePage } from './production-area.page-object';

const expect = chai.expect;

describe('ProductionArea e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let productionAreaComponentsPage: ProductionAreaComponentsPage;
  let productionAreaUpdatePage: ProductionAreaUpdatePage;
  let productionAreaDeleteDialog: ProductionAreaDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load ProductionAreas', async () => {
    await navBarPage.goToEntity('production-area');
    productionAreaComponentsPage = new ProductionAreaComponentsPage();
    await browser.wait(ec.visibilityOf(productionAreaComponentsPage.title), 5000);
    expect(await productionAreaComponentsPage.getTitle()).to.eq('ifmSimple1App.productionArea.home.title');
    await browser.wait(
      ec.or(ec.visibilityOf(productionAreaComponentsPage.entities), ec.visibilityOf(productionAreaComponentsPage.noResult)),
      1000
    );
  });

  it('should load create ProductionArea page', async () => {
    await productionAreaComponentsPage.clickOnCreateButton();
    productionAreaUpdatePage = new ProductionAreaUpdatePage();
    expect(await productionAreaUpdatePage.getPageTitle()).to.eq('ifmSimple1App.productionArea.home.createOrEditLabel');
    await productionAreaUpdatePage.cancel();
  });

  it('should create and save ProductionAreas', async () => {
    const nbButtonsBeforeCreate = await productionAreaComponentsPage.countDeleteButtons();

    await productionAreaComponentsPage.clickOnCreateButton();

    await promise.all([productionAreaUpdatePage.setNameInput('name'), productionAreaUpdatePage.operatorWorkShiftSelectLastOption()]);

    expect(await productionAreaUpdatePage.getNameInput()).to.eq('name', 'Expected Name value to be equals to name');

    await productionAreaUpdatePage.save();
    expect(await productionAreaUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await productionAreaComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last ProductionArea', async () => {
    const nbButtonsBeforeDelete = await productionAreaComponentsPage.countDeleteButtons();
    await productionAreaComponentsPage.clickOnLastDeleteButton();

    productionAreaDeleteDialog = new ProductionAreaDeleteDialog();
    expect(await productionAreaDeleteDialog.getDialogTitle()).to.eq('ifmSimple1App.productionArea.delete.question');
    await productionAreaDeleteDialog.clickOnConfirmButton();

    expect(await productionAreaComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
