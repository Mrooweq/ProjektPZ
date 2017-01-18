export class FirstnameValidator {

  static firstnameValidator(control) {
    var FIRSTNAME_REGEXP = /^[A-ZŁŻ][a-ząćęłńóśźżĄĘŁŃÓŚŹŻ]{1,20}$/;

    if (!FIRSTNAME_REGEXP.test(control.value)) {
      return {invalidFirstname: true};
    }
  }

}
