import {FormGroup} from "@angular/forms";

export class MatchingValidator {
  static matchValues(key: string, confirmationKey: string) {
    return (group: FormGroup) => {
      let input = group.controls[key];
      let confirmationInput = group.controls[confirmationKey];
      if (input.value !== confirmationInput.value) {
        return confirmationInput.setErrors({notEquivalent: true})
      }
    }
  }
}
