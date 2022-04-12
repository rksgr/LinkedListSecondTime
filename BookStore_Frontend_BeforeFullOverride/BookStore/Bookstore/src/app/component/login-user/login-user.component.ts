import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup,Validators } from '@angular/forms';
import { UserLogin } from 'src/app/model/user-login';
import { HTTPService } from 'src/app/service/http.service';
import { Router } from '@angular/router';
import { UserService } from 'src/app/service/user.service';

@Component({
  selector: 'app-login-user',
  templateUrl: './login-user.component.html',
  styleUrls: ['./login-user.component.scss']
})
export class LoginUserComponent implements OnInit {

public userLogin : UserLogin = new UserLogin();

loginUserForm : FormGroup;

  constructor(private formBuilder : FormBuilder,
              private userService : UserService,
              private router : Router) 
  { 
    this.loginUserForm = this.formBuilder.group({
      emailId: new FormControl('',[Validators.required]),
      password: new FormControl('', [Validators.required])
    })
    };
//loginUserForm
  ngOnInit(): void {
    const token = localStorage.getItem("token");
  }

  onSubmit(){
    this.userLogin = this.loginUserForm.value;
    this.userService.loginUser(this.userLogin).subscribe(response=>{

      if (response.message == "User Login Success!"){
        localStorage.setItem("token",response.data);
        console.log(response.data);
        this.router.navigate(['/home-page']);
      }else{
        alert(" The email id and password combination is not correct.")
      }
    });                                                                     
  }
}
