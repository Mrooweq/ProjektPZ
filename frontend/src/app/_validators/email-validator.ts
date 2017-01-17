export class EmailValidator {

  static emailValidator(control) {
    var EMAIL_REGEXP = /^[\w]+(?:\.[\w]+)*@(?:[a-zA-Z0-9-]+\.)+[a-zA-Z]{2,6}$/i;

    if (!EMAIL_REGEXP.test(control.value)) {
      return {invalidEmail: true};
    }
  }
}
