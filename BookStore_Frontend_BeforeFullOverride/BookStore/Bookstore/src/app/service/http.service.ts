import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { User } from '../model/user';
import { Observable } from 'rxjs';
import { ResetPassword } from '../model/reset-password';


@Injectable({
  providedIn: 'root'
})
export class HTTPService {

  private baseUrl1: string = "http://localhost:8080/userservice/";
  
  private baseUrl2: string = "http://localhost:8080/orderservice/";

  constructor(private httpClient:HttpClient) { }
    addUser(body:any): Observable<any> {
      return this.httpClient.post(this.baseUrl1+ "register", body);
    }

    loginUser(body:any): Observable<any> {
      return this.httpClient.post(this.baseUrl1 + "login", body);
    }
    forgotPassword(body:any): Observable<any> {
      return this.httpClient.post(this.baseUrl1 + "forgotPassword" , body);
    }

    resetPassword(body: any): Observable<any> {
      return this.httpClient.post(this.baseUrl1 + "resetPassword" , body);
    }
    getOrderDetails(): Observable<any> {
      return this.httpClient.get(this.baseUrl2 + "get");
    }
  }

