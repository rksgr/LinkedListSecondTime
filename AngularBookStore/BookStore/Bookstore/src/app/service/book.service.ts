import { Injectable } from '@angular/core';
import { HttpClient, HttpParams, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Book } from '../../app/model/book';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class BookService {

  private bookSource = new BehaviorSubject(new Book());
  currentEmployee = this.bookSource.asObservable();

  private baseUrl: string = "http://localhost:8080/bookstore/";

  constructor(private httpClient: HttpClient) { }

/**
 * Get all books from the database
 * @returns An Observable of type Book[]
 */
  /*getAllbooks(): Observable<httpResponse>{
    return this.http.get<httpResponse>(`http://localhost:8080/bookstore/get`);
  }*/
  getAllbooks(): Observable<any> {
    return this.httpClient.get(this.baseUrl + "get");
  }

/**
 * Given a book id, return the book with that id
 * @param {number} book_id - The id of the book we want to get.
 * @returns An Observable of type Book[]
 */
  getBookById(book_id: number): Observable<any>{
    return this.httpClient.get<Book[]>(`http://localhost:8080/bookstore/get/${book_id}`);
  }

/**
 * Get all books with a given name
 * @param {String} book_name - The name of the book to be searched.
 * @returns An Observable of type Book[]
 */
  getBookByName(book_name: String): Observable<any>{
    return this.httpClient.get<Book[]>(`http://localhost:8080/bookstore/bookname/${book_name}`);
  }

/**
 * It sorts the books by the number of pages.
 * @param {number} num - number
 * @returns An Observable of type Book[]
 */
  sort(num: number): Observable<any>{
    return this.httpClient.get<Book[]>(`http://localhost:8080/bookstore/sort/${num}`);
  }

/**
 * It changes the quantity of a book in the database.
 * @param {number} book_id - The id of the book to change the quantity of.
 * @param {number} quantity - The new quantity of the book.
 * @returns An Observable of type any.
 */
  changeBookQuantity(book_id:number, quantity:number): Observable<any>{
    const token = localStorage.getItem("token") 
    const params = new HttpParams()
                .set('book_id', book_id)
                .set('quantity', quantity);
    return this.httpClient.post(`http://localhost:8080/book/changequantity/${token}`, params);
  }

}
