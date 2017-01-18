export class PasswordValidator {

  static passwordValidator(control) {
    var PASSWORD_REGEXP = /^(?=.*[A-Z])(?=.*[0-9]).{6,20}$/i;

    if (!PASSWORD_REGEXP.test(control.value)) {
      return {invalidPassword: true};
    }
  }
}
