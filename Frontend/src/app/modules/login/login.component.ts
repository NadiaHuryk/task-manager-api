import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { Router } from "@angular/router";
import { HttpClient } from "@angular/common/http";
import  {UserService } from "../../core/service/user.service";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent {
  loginForm: FormGroup;
  constructor(
    private fb: FormBuilder,
    private userService: UserService,
    private router: Router,
    private http: HttpClient) {

    this.loginForm = this.fb.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required]]
    });
  }

  onLogin() {
    if (this.loginForm.invalid) {
      return;
    }

    this.http.post<any>('http://localhost:8080/users/login', this.loginForm.value).subscribe(
      data => {
        localStorage.setItem('currentUser', JSON.stringify(data));
        this.router.navigate(['/me']);
      },
      error => {
        console.error('Login failed', error);
      }
    );
  }
}
