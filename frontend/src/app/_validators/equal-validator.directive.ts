import {Directive, forwardRef, Attribute} from '@angular/core';
import {Validator, AbstractControl, NG_VALIDATORS} from '@angular/forms';
@Directive({
  selector: '[validateEqual][formControlName],[validateEqual][formControl],[validateEqual][ngModel]',
  providers: [
    {provide: NG_VALIDATORS, useExisting: forwardRef(() => EqualValidator), multi: true}
  ]
})
export class EqualValidator implements Validator {
  constructor(@Attribute('validateEqual') public validateEqual: string,
              @Attribute('reverse') public reverse: string) {
  }

  private get isReverse() {
    if (!this.reverse) return false;
    return this.reverse === 'true';
  }

  validate(c: AbstractControl): {[key: string]: any} {
    let self_value = c.value;
    let control_value = c.root.get(this.validateEqual);
    if (control_value && self_value !== control_value.value && !this.isReverse)
      return {validateEqual: false};

    if (control_value && self_value === control_value.value && this.isReverse) {
      delete control_value.errors['validateEqual'];
      if (!Object.keys(control_value.errors).length) control_value.setErrors(null);
    }

    if (control_value && self_value !== control_value.value && this.isReverse) {
      control_value.setErrors({validateEqual: false});
    }
    return null;
  }
}
