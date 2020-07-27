import { element, by, ElementFinder } from 'protractor';

export class OperatorDeviceComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('bpf-operator-device div table .btn-danger'));
  title = element.all(by.css('bpf-operator-device div h2#page-heading span')).first();
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

export class OperatorDeviceUpdatePage {
  pageTitle = element(by.id('bpf-operator-device-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));

  nameInput = element(by.id('field_name'));
  typeSelect = element(by.id('field_type'));
  registrationIdInput = element(by.id('field_registrationId'));

  async getPageTitle(): Promise<string> {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setNameInput(name: string): Promise<void> {
    await this.nameInput.sendKeys(name);
  }

  async getNameInput(): Promise<string> {
    return await this.nameInput.getAttribute('value');
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

  async setRegistrationIdInput(registrationId: string): Promise<void> {
    await this.registrationIdInput.sendKeys(registrationId);
  }

  async getRegistrationIdInput(): Promise<string> {
    return await this.registrationIdInput.getAttribute('value');
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

export class OperatorDeviceDeleteDialog {
  private dialogTitle = element(by.id('bpf-delete-operatorDevice-heading'));
  private confirmButton = element(by.id('bpf-confirm-delete-operatorDevice'));

  async getDialogTitle(): Promise<string> {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton(): Promise<void> {
    await this.confirmButton.click();
  }
}
