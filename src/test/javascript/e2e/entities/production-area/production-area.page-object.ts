import { element, by, ElementFinder } from 'protractor';

export class ProductionAreaComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('bpf-production-area div table .btn-danger'));
  title = element.all(by.css('bpf-production-area div h2#page-heading span')).first();
  noResult = element(by.id('no-result'));
  entities = element(by.id('entities'));

  async clickOnCreateButton(): Promise<void> {
    await this.createButton.click();
  }

  async clickOnLastDeleteButton(): Promise<void> {
    await this.deleteButtons.last().click();
  }

  async countDeleteButtons(): Promise<number> {
    return this.deleteButtons.count();
  }

  async getTitle(): Promise<string> {
    return this.title.getAttribute('jhiTranslate');
  }
}

export class ProductionAreaUpdatePage {
  pageTitle = element(by.id('bpf-production-area-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));

  nameInput = element(by.id('field_name'));

  operatorWorkShiftSelect = element(by.id('field_operatorWorkShift'));

  async getPageTitle(): Promise<string> {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setNameInput(name: string): Promise<void> {
    await this.nameInput.sendKeys(name);
  }

  async getNameInput(): Promise<string> {
    return await this.nameInput.getAttribute('value');
  }

  async operatorWorkShiftSelectLastOption(): Promise<void> {
    await this.operatorWorkShiftSelect.all(by.tagName('option')).last().click();
  }

  async operatorWorkShiftSelectOption(option: string): Promise<void> {
    await this.operatorWorkShiftSelect.sendKeys(option);
  }

  getOperatorWorkShiftSelect(): ElementFinder {
    return this.operatorWorkShiftSelect;
  }

  async getOperatorWorkShiftSelectedOption(): Promise<string> {
    return await this.operatorWorkShiftSelect.element(by.css('option:checked')).getText();
  }

  async save(): Promise<void> {
    await this.saveButton.click();
  }

  async cancel(): Promise<void> {
    await this.cancelButton.click();
  }

  getSaveButton(): ElementFinder {
    return this.saveButton;
  }
}

export class ProductionAreaDeleteDialog {
  private dialogTitle = element(by.id('bpf-delete-productionArea-heading'));
  private confirmButton = element(by.id('bpf-confirm-delete-productionArea'));

  async getDialogTitle(): Promise<string> {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton(): Promise<void> {
    await this.confirmButton.click();
  }
}
