import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Person } from 'src/app/model/person';
import { HttpClient } from '@angular/common/http';
import { HttpService } from 'src/app/service/http.service';
import { DataService } from 'src/app/service/data.service';

@Component({
  selector: 'app-home-page',
  templateUrl: './home-page.component.html',
  styleUrls: ['./home-page.component.scss']
})
export class HomePageComponent implements OnInit {
  public personDetails:Person[] = [];


  constructor(private httpService: HttpService,
              private router: Router,
              private dataService: DataService) { }

  ngOnInit(): void {
    this.httpService.getAddressBookData()
        .subscribe(data =>  {
          this.personDetails = data.data;
          console.log(this.personDetails);
        });
  }
  remove(personId:number): void{
    console.log(personId);
    this.httpService.deletePersonData(personId)
        .subscribe(response =>{
          console.log(response);
          this.ngOnInit();
        });
  }

  update(person: Person,personId = person.personId): void{
    console.log("home page ts file update method");
      //personId = person.personId;
      this.dataService.changePerson(person);
      //this.router.navigateByUrl("/add-person");
      this.httpService.updatePersonData(person.personId, person)
            .subscribe(response =>{
              console.log(response);
              this.ngOnInit();
      });
  }
}
