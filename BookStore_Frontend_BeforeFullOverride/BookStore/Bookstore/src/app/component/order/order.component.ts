import { Component, OnInit } from '@angular/core';
import { HTTPService } from 'src/app/service/http.service';
import { Router } from '@angular/router';
import { DataService } from 'src/app/service/data.service';
import { OrderedBooks } from 'src/app/model/ordered-books';

interface quantity {
  value: Number;
  viewValue: Number;
}
@Component({
  selector: 'app-order',
  templateUrl: './order.component.html',
  styleUrls: ['./order.component.scss']
})


export class OrderComponent implements OnInit {
  public orderDetails:OrderedBooks[] = [];

  constructor(private httpService: HTTPService,
    private router: Router,
    private dataService: DataService) { }

  ngOnInit(): void {
    /** 
    this.httpService.getOrderDetails()
        .subscribe(data => {
          this.orderDetails = data.data;
          console.log(this.orderDetails);
        });
        */
  }
  quantities: quantity[] = [
    {value: 1, viewValue: 1},
    {value: 2, viewValue: 2},
    {value: 3, viewValue: 3},
    {value: 4, viewValue: 4},
    {value: 5, viewValue: 5}
  ];
  

}
