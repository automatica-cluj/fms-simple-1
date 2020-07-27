import { element, by, ElementFinder } from 'protractor';

export class FactoryEventComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('bpf-factory-event div table .btn-danger'));
  title = element.all(by.css('bpf-factory-event div h2#page-heading span')).first();
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

export class FactoryEventUpdatePage {
  pageTitle = element(by.id('bpf-factory-event-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));

  subjectInput = element(by.id('field_subject'));
  bodyInput = element(by.id('field_body'));
  typeSelect = element(by.id('field_type'));
  createDateInput = element(by.id('field_createDate'));
  notificationCountInput = element(by.id('field_notificationCount'));
  nextNotificationDateInput = element(by.id('field_nextNotificationDate'));
  processingStatusSelect = element(by.id('field_processingStatus'));

  productionAreaSelect = element(by.id('field_productionArea'));

  async getPageTitle(): Promise<string> {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setSubjectInput(subject: string): Promise<void> {
    await this.subjectInput.sendKeys(subject);
  }

  async getSubjectInput(): Promise<string> {
    return await this.subjectInput.getAttribute('value');
  }

  async setBodyInput(body: string): Promise<void> {
    await this.bodyInput.sendKeys(body);
  }

  async getBodyInput(): Promise<string> {
    return await this.bodyInput.getAttribute('value');
  }

  async setTypeSelect(type: string): Promise<void> {
    await this.typeSelect.sendKeys(type);
  }

  async getTypeSelect(): Promise<string> {
    return await this.typeSelect.element(by.css('option:checked')).getText();
  }

  async typeSelectLastOption(): Promise<void> {
    await this.typeSelect.all(by.tagName('option')).last().click();
  }

  async setCreateDateInput(createDate: string): Promise<void> {
    await this.createDateInput.sendKeys(createDate);
  }

  async getCreateDateInput(): Promise<string> {
    return await this.createDateInput.getAttribute('value');
  }

  async setNotificationCountInput(notificationCount: string): Promise<void> {
    await this.notificationCountInput.sendKeys(notificationCount);
  }

  async getNotificationCountInput(): Promise<string> {
    return await this.notificationCountInput.getAttribute('value');
  }

  async setNextNotificationDateInput(nextNotificationDate: string): Promise<void> {
    await this.nextNotificationDateInput.sendKeys(nextNotificationDate);
  }

  async getNextNotificationDateInput(): Promise<string> {
    return await this.nextNotificationDateInput.getAttribute('value');
  }

  async setProcessingStatusSelect(processingStatus: string): Promise<void> {
    await this.processingStatusSelect.sendKeys(processingStatus);
  }

  async getProcessingStatusSelect(): Promise<string> {
    return await this.processingStatusSelect.element(by.css('option:checked')).getText();
  }

  async processingStatusSelectLastOption(): Promise<void> {
    await this.processingStatusSelect.all(by.tagName('option')).last().click();
  }

  async productionAreaSelectLastOption(): Promise<void> {
    await this.productionAreaSelect.all(by.tagName('option')).last().click();
  }

  async productionAreaSelectOption(option: string): Promise<void> {
    await this.productionAreaSelect.sendKeys(option);
  }

  getProductionAreaSelect(): ElementFinder {
    return this.productionAreaSelect;
  }

  async getProductionAreaSelectedOption(): Promise<string> {
    return await this.productionAreaSelect.element(by.css('option:checked')).getText();
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

export class FactoryEventDeleteDialog {
  private dialogTitle = element(by.id('bpf-delete-factoryEvent-heading'));
  private confirmButton = element(by.id('bpf-confirm-delete-factoryEvent'));

  async getDialogTitle(): Promise<string> {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton(): Promise<void> {
    await this.confirmButton.click();
  }
}
