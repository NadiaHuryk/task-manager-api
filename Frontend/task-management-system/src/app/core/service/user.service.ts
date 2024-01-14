import { Injectable } from '@angular/core';
import { UserRegistrationRequest } from "../model/user-registration-request";
import { UserLoginResponse } from "../model/user-login-response";
import { UserResponse } from '../model/user-response';
import { Observable } from "rxjs";
import { HttpClient, HttpHeaders } from "@angular/common/http";
import { UserRequest } from "../model/user-request";

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json'})
};

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private readonly baseUrl = 'http://localhost:8080/users';

  constructor(private http: HttpClient) {}

  login(user: UserRegistrationRequest): Observable<UserLoginResponse> {
    return this.http.post<UserLoginResponse>(`${this.baseUrl}/login`, user, httpOptions);
  }
  signUp(user: UserRegistrationRequest): Observable<UserResponse> {
    return this.http.post<UserResponse>(`${this.baseUrl}/registration`, user, httpOptions);
  }

  getMe(): Observable<UserResponse> {
    return this.http.get<UserResponse>(`${this.baseUrl}/me`);
  }

  updateUser(user: UserRequest): Observable<UserResponse> {
    return this.http.patch<UserResponse>(`${this.baseUrl}/me`, user, httpOptions);
  }

  deleteUser(): Observable<any> {
    return this.http.delete(`${this.baseUrl}`);
  }

  uploadProfilePicture(formData: FormData): Observable<any> {
    return this.http.post(`${this.baseUrl}/upload-profile-picture`, formData);
  }

  getProfilePicture(): Observable<Blob> {
    return this.http.get(`${this.baseUrl}/me/profile-picture`, { responseType: 'blob' });
  }
}
