import { element, by, ElementFinder } from 'protractor';

export class MessageComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('bpf-message div table .btn-danger'));
  title = element.all(by.css('bpf-message div h2#page-heading span')).first();
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

export class MessageUpdatePage {
  pageTitle = element(by.id('bpf-message-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));

  subjectInput = element(by.id('field_subject'));
  contentInput = element(by.id('field_content'));
  statusSelect = element(by.id('field_status'));

  operatorWorkShiftSelect = element(by.id('field_operatorWorkShift'));

  async getPageTitle(): Promise<string> {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setSubjectInput(subject: string): Promise<void> {
    await this.subjectInput.sendKeys(subject);
  }

  async getSubjectInput(): Promise<string> {
    return await this.subjectInput.getAttribute('value');
  }

  async setContentInput(content: string): Promise<void> {
    await this.contentInput.sendKeys(content);
  }

  async getContentInput(): Promise<string> {
    return await this.contentInput.getAttribute('value');
  }

  async setStatusSelect(status: string): Promise<void> {
    await this.statusSelect.sendKeys(status);
  }

  async getStatusSelect(): Promise<string> {
    return await this.statusSelect.element(by.css('option:checked')).getText();
  }

  async statusSelectLastOption(): Promise<void> {
    await this.statusSelect.all(by.tagName('option')).last().click();
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

export class MessageDeleteDialog {
  private dialogTitle = element(by.id('bpf-delete-message-heading'));
  private confirmButton = element(by.id('bpf-confirm-delete-message'));

  async getDialogTitle(): Promise<string> {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton(): Promise<void> {
    await this.confirmButton.click();
  }
}
