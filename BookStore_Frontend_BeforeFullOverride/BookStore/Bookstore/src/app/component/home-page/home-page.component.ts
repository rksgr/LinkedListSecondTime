import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { BookService } from 'src/app/service/book.service';
import { Book } from 'src/app/model/book';
import { CartService } from 'src/app/service/cart.service';

@Component({
  selector: 'app-home-page',
  templateUrl: './home-page.component.html',
  styleUrls: ['./home-page.component.scss']
})
export class HomePageComponent implements OnInit {
  public bookCount: number = 0;

  //public localStorage: Storage =null;
  public bookDetails: Book[] = [];

  constructor(private bookService: BookService,
              private cartService: CartService,
              private router: Router) { }

  public totBooks:number = 0;
  ngOnInit(): void {
    this.bookService.getAllbooks()
        .subscribe(data=> {
          this.bookDetails = data.data;
          this.bookCount = this.bookDetails.length;
          console.log(this.bookDetails) ;
        });
  }

  // function to return list of numbers from 0 to n-1
  numSequence(n:number): Array<number> {
    return Array(n);
  }

  addToCart(bookId: number): void{
    var cartDTO = {"userId":localStorage.getItem("token"),"bookId":bookId,"quantity":1};
    this.cartService.addToCart(cartDTO).subscribe()
    //console.log("you have added: "+ book.bookName);
  }
}

