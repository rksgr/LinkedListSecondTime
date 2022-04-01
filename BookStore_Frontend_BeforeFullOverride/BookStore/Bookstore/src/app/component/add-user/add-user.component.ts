import { state } from '@angular/animations';
import { Component, OnInit } from '@angular/core';
import { FormArray, FormBuilder, FormGroup, FormControl, Validators} from '@angular/forms';
import { User } from 'src/app/model/user';
import { HTTPService } from 'src/app/service/http.service';
import { Router } from '@angular/router';


@Component({
  selector: 'app-add-user',
  templateUrl: './add-user.component.html',
  styleUrls: ['./add-user.component.scss']
})

export class AddUserComponent implements OnInit {

  public user: User = new User();

  addUserForm:FormGroup;

  constructor(private formBuilder: FormBuilder,
              private httpService: HTTPService,
              private router: Router) {
    this.addUserForm = this.formBuilder.group({
      firstName: new FormControl('',[Validators.required,Validators.minLength(4)]),
      lastName: new FormControl('',[Validators.required,Validators.minLength(4)]),
      kyc: new FormControl('',[Validators.required]),
      dob: new FormControl('',[Validators.required]),
      password: new FormControl('',[Validators.required]),
      emailId: new FormControl('',[Validators.required])
    })
   };

  ngOnInit(): void {
    console.log(this.user);
  }

  onSubmit(){
    this.user = this.addUserForm.value;

    this.httpService.addUser(this.user).subscribe(response =>{
      console.log(response);
      //this.router.navigateByUrl("/home-page");
     });
  }


}
