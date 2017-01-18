export class UsernameValidator {

  static usernameValidator(control) {
    var USERNAME_REGEXP = /^(?=.*[a-z])[\wąćęłńóśźżĄĘŁŃÓŚŹŻ\d]{3,20}$/;

    if (!USERNAME_REGEXP.test(control.value)) {
      return {invalidUsername: true};
    }
  }

}
