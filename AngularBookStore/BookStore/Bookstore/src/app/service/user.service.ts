import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { User } from '../model/user';
import { Observable } from 'rxjs';
import { ResetPassword } from '../model/reset-password';


@Injectable({
  providedIn: 'root'
})
export class UserService {

  private baseUrl: string = "http://localhost:8080/userservice/";

  constructor(private httpClient:HttpClient) { }
    addUser(body:any): Observable<any> {
      return this.httpClient.post(this.baseUrl+ "register", body);
    }

    loginUser(body:any): Observable<any> {
      return this.httpClient.post(this.baseUrl + "login", body);
    }
    forgotPassword(body:any): Observable<any> {
      return this.httpClient.post(this.baseUrl + "forgotPassword" , body);
    }

    resetPassword(body: any): Observable<any> {
      return this.httpClient.post(this.baseUrl + "resetPassword" , body);
    }
  }