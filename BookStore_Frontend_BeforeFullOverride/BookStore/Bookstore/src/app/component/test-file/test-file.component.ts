import { Component, OnInit } from '@angular/core';


interface Food {
  value: string;
  viewValue: string;
}
/**
 * Title: Basic select
 */
@Component({
  selector: 'app-test-file',
  templateUrl: './test-file.component.html',
  styleUrls: ['./test-file.component.scss']
})
export class TestFileComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }
  foods: Food[] = [
    {value: 'steak-0', viewValue: 'Steak'},
    {value: 'pizza-1', viewValue: 'Pizza'},
    {value: 'tacos-2', viewValue: 'Tacos'},
  ];

}
