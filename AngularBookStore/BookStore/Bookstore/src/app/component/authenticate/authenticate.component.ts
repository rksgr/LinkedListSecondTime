import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { UserService } from 'src/app/service/user.service';

@Component({
  selector: 'app-authenticate',
  templateUrl: './authenticate.component.html',
  styleUrls: ['./authenticate.component.scss']
})
export class AuthenticateComponent implements OnInit {

  verifyUser!: FormGroup;
  constructor(private formBuilder: FormBuilder, private route:ActivatedRoute, private userService: UserService, private router:Router) { }
  email =""
  ngOnInit(): void {
   this.email =  this.route.snapshot.paramMap.get("email_id")||""
   console.log("emailid = ", this.email);
    this.verifyUser = this.formBuilder.group({
      d1:['', Validators.required],
      d2:['', Validators.required],
      d3:['', Validators.required],
      d4:['', Validators.required],
      d5:['', Validators.required],
      d6:['', Validators.required],
    })
  }
  otp = ""
/**
 * Verify the user's email and OTP
 */
  verify(){
    if(this.verifyUser.valid){
     this.otp = this.verifyUser.value.d1+this.verifyUser.value.d2+this.verifyUser.value.d3+this.verifyUser.value.d4+
        this.verifyUser.value.d5+this.verifyUser.value.d6
        const dto = {
          "email_id": this.email,
          "otp": this.otp
        }
        console.log("shjf ", dto)
        this.userService.verification(dto).subscribe(data =>{
          console.log(" resp", data)
          if(data.data!= 'Wrong OTP'){
            this.router.navigate([''])
          }
        })
    }
  }
  
}