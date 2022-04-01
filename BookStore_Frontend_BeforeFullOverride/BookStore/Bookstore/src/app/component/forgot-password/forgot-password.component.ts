import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { HTTPService } from 'src/app/service/http.service';
@Component({
  selector: 'app-forgot-password',
  templateUrl: './forgot-password.component.html',
  styleUrls: ['./forgot-password.component.scss']
})
export class ForgotPasswordComponent implements OnInit {

  forgotPasswordForm: FormGroup;

  constructor(private httpService: HTTPService,
              private formBuilder: FormBuilder) {
    this.forgotPasswordForm = this.formBuilder.group({
      emailId: new FormControl('', [Validators.minLength(5)])
    })
   };

  ngOnInit(): void {
  }
  onSubmit(){
    this.httpService.forgotPassword(this.forgotPasswordForm.value)
        .subscribe(response=>{
          console.log(response);
        })
  };
}
