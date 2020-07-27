import { element, by, ElementFinder } from 'protractor';

export class OperatorWorkShiftComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('bpf-operator-work-shift div table .btn-danger'));
  title = element.all(by.css('bpf-operator-work-shift div h2#page-heading span')).first();
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

export class OperatorWorkShiftUpdatePage {
  pageTitle = element(by.id('bpf-operator-work-shift-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));

  startDateInput = element(by.id('field_startDate'));
  endDateInput = element(by.id('field_endDate'));
  statusSelect = element(by.id('field_status'));

  deviceSelect = element(by.id('field_device'));
  operatorSelect = element(by.id('field_operator'));

  async getPageTitle(): Promise<string> {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setStartDateInput(startDate: string): Promise<void> {
    await this.startDateInput.sendKeys(startDate);
  }

  async getStartDateInput(): Promise<string> {
    return await this.startDateInput.getAttribute('value');
  }

  async setEndDateInput(endDate: string): Promise<void> {
    await this.endDateInput.sendKeys(endDate);
  }

  async getEndDateInput(): Promise<string> {
    return await this.endDateInput.getAttribute('value');
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

  async deviceSelectLastOption(): Promise<void> {
    await this.deviceSelect.all(by.tagName('option')).last().click();
  }

  async deviceSelectOption(option: string): Promise<void> {
    await this.deviceSelect.sendKeys(option);
  }

  getDeviceSelect(): ElementFinder {
    return this.deviceSelect;
  }

  async getDeviceSelectedOption(): Promise<string> {
    return await this.deviceSelect.element(by.css('option:checked')).getText();
  }

  async operatorSelectLastOption(): Promise<void> {
    await this.operatorSelect.all(by.tagName('option')).last().click();
  }

  async operatorSelectOption(option: string): Promise<void> {
    await this.operatorSelect.sendKeys(option);
  }

  getOperatorSelect(): ElementFinder {
    return this.operatorSelect;
  }

  async getOperatorSelectedOption(): Promise<string> {
    return await this.operatorSelect.element(by.css('option:checked')).getText();
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

export class OperatorWorkShiftDeleteDialog {
  private dialogTitle = element(by.id('bpf-delete-operatorWorkShift-heading'));
  private confirmButton = element(by.id('bpf-confirm-delete-operatorWorkShift'));

  async getDialogTitle(): Promise<string> {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton(): Promise<void> {
    await this.confirmButton.click();
  }
}
