import { element, by, ElementFinder } from 'protractor';

export class OperatorNotificationComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('bpf-operator-notification div table .btn-danger'));
  title = element.all(by.css('bpf-operator-notification div h2#page-heading span')).first();
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

export class OperatorNotificationUpdatePage {
  pageTitle = element(by.id('bpf-operator-notification-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));

  receiveDateInput = element(by.id('field_receiveDate'));
  responseDateInput = element(by.id('field_responseDate'));
  operatorResponseSelect = element(by.id('field_operatorResponse'));

  operatorWorkShiftSelect = element(by.id('field_operatorWorkShift'));
  factoryEventSelect = element(by.id('field_factoryEvent'));

  async getPageTitle(): Promise<string> {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setReceiveDateInput(receiveDate: string): Promise<void> {
    await this.receiveDateInput.sendKeys(receiveDate);
  }

  async getReceiveDateInput(): Promise<string> {
    return await this.receiveDateInput.getAttribute('value');
  }

  async setResponseDateInput(responseDate: string): Promise<void> {
    await this.responseDateInput.sendKeys(responseDate);
  }

  async getResponseDateInput(): Promise<string> {
    return await this.responseDateInput.getAttribute('value');
  }

  async setOperatorResponseSelect(operatorResponse: string): Promise<void> {
    await this.operatorResponseSelect.sendKeys(operatorResponse);
  }

  async getOperatorResponseSelect(): Promise<string> {
    return await this.operatorResponseSelect.element(by.css('option:checked')).getText();
  }

  async operatorResponseSelectLastOption(): Promise<void> {
    await this.operatorResponseSelect.all(by.tagName('option')).last().click();
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

  async factoryEventSelectLastOption(): Promise<void> {
    await this.factoryEventSelect.all(by.tagName('option')).last().click();
  }

  async factoryEventSelectOption(option: string): Promise<void> {
    await this.factoryEventSelect.sendKeys(option);
  }

  getFactoryEventSelect(): ElementFinder {
    return this.factoryEventSelect;
  }

  async getFactoryEventSelectedOption(): Promise<string> {
    return await this.factoryEventSelect.element(by.css('option:checked')).getText();
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

export class OperatorNotificationDeleteDialog {
  private dialogTitle = element(by.id('bpf-delete-operatorNotification-heading'));
  private confirmButton = element(by.id('bpf-confirm-delete-operatorNotification'));

  async getDialogTitle(): Promise<string> {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton(): Promise<void> {
    await this.confirmButton.click();
  }
}
