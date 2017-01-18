export class LastnameValidator {

  static lastnameValidator(control) {
    var LASTNAME_REGEXP = /^[A-ZĆŁŚŻŹ][a-ząćęłńóśźżĄĘŁŃÓŚŹŻ]{1,20}((-|\\s)?[A-ZĆŁŚŻŹ][a-ząćęłńóśźżĄĘŁŃÓŚŹŻ]{1,20})?$/;

    if (!LASTNAME_REGEXP.test(control.value)) {
      return {invalidLastname: true};
    }
  }

}
