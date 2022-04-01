import { Component, OnInit } from '@angular/core';
import { FormBuilder,FormGroup, FormControl,Validators } from '@angular/forms';
import { HTTPService } from 'src/app/service/http.service';
import { Router } from '@angular/router';
import { ResetPassword } from 'src/app/model/reset-password';

@Component({
  selector: 'app-reset-password',
  templateUrl: './reset-password.component.html',
  styleUrls: ['./reset-password.component.scss']
})

export class ResetPasswordComponent implements OnInit {

  public resetPwd: ResetPassword = new ResetPassword();

  resetPasswordForm : FormGroup;

  constructor(private formBuilder : FormBuilder,
              private httpService : HTTPService,
              private router : Router) 
  { 
    this.resetPasswordForm = this.formBuilder.group({
      token: new FormControl('',[Validators.required]),
      newPasswrd: new FormControl('', [Validators.required])
    })
    };
//resetPasswordForm
  ngOnInit(): void {
  }

  onSubmit(){
    this.resetPwd = this.resetPasswordForm.value;
    this.httpService.resetPassword(this.resetPwd).subscribe(Response =>{
      console.log(Response);
    });
  }
}