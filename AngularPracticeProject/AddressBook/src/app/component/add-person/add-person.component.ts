import { state } from '@angular/animations';
import { Component, OnInit } from '@angular/core';
import { FormArray, FormBuilder,FormGroup,FormControl, Validators} from '@angular/forms';
import { Person } from 'src/app/model/person';
import { HttpService } from 'src/app/service/http.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-add-person',
  templateUrl: './add-person.component.html',
  styleUrls: ['./add-person.component.scss']
})
export class AddPersonComponent implements OnInit {
  public person: Person = new Person();

  addPersonForm:FormGroup;

  constructor(private formBuilder: FormBuilder,
              private httpService: HttpService,
              private router: Router) {
    this.addPersonForm = this.formBuilder.group({
      name: new FormControl('',[Validators.required,Validators.minLength(4)]),
      address: new FormControl('',[Validators.required,Validators.minLength(14)]),
      city: new FormControl('',),
      state: new FormControl('',),
      zip: new FormControl('',[Validators.required]),
      phone: new FormControl('',[Validators.required]),
      email: new FormControl('',[Validators.required])
    })
   };

  ngOnInit(): void {
    console.log(this.person);
  }

  onSubmit(){
    this.person = this.addPersonForm.value;
    console.log(this.person);

    this.httpService.addPerson(this.person).subscribe(response =>{
      console.log(response);
      this.router.navigateByUrl("/home-page");
     });
  }
}
