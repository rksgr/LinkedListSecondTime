import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Person } from '../model/person';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class HttpService {
  private baseUrl: string = "http://localhost:8080/addressbookservice/";

  constructor(private httpClient: HttpClient) {}
    getAddressBookData(): Observable<any> {
      return this.httpClient.get(this.baseUrl + "get");
    }
    addPerson(body:any): Observable<any> {
      return this.httpClient.post(this.baseUrl +"create",body);
    }
    deletePersonData(person_id:number): Observable<any> {
      return this.httpClient.delete(this.baseUrl + "delete/"+ person_id);
    }
    updatePersonData(person_id:number,body:any): Observable<any> {
      return this.httpClient.put(this.baseUrl + "update/" + person_id,body);
    }
  }

