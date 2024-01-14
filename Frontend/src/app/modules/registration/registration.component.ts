import { Component } from '@angular/core';
import { FormBuilder, FormGroup, ValidationErrors, Validators } from "@angular/forms";
import { Router } from "@angular/router";
import { UserService } from "../../core/service/user.service";

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.scss']
})
export class RegistrationComponent {
  registrationForm: FormGroup;
  constructor(
    private fb: FormBuilder,
    private userService: UserService,
    private router: Router) {
    this.registrationForm = this.fb.group({
      name: ['', [Validators.required, Validators.minLength(2)]],
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(8)]],
      repeatPassword: ['', [Validators.required]]
    }, { validator: this.checkPasswords });
  }

  checkPasswords(group: FormGroup): ValidationErrors | null {
    const pass = group.get('password')?.value;
    const confirmPass = group.get('repeatPassword')?.value;

    const passControl = group.get('password');
    const confirmPassControl = group.get('repeatPassword');

    if (passControl && confirmPassControl) {
      if (pass !== confirmPass) {
        confirmPassControl.setErrors({ notSame: true });
        return { notSame: true };
      } else {
        confirmPassControl.setErrors(null);
      }
    }

    return null;
  }

  registerUser() {
    this.userService.signUp(this.registrationForm.value).subscribe(
      (response) => {
        console.log('User registered successfully:', response);
        this.router.navigate(['/login']);
      },
      (error) => {
        console.error('Error registering user:', error);
      }
    );
  }
}
