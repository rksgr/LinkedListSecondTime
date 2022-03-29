import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { UserService } from 'src/app/service/user.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {

  registerForm!: FormGroup;
  verifyUser!: FormGroup;
  constructor(private formBuilder: FormBuilder, private userService: UserService, private router:Router) { }

  ngOnInit(): void {
    this.registerForm = this.formBuilder.group({
      first_name: ['', Validators.required],
      last_name: ['', Validators.required],
      email_id: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(6)]],
      birth_date:[''] 
    });

  }

/**
 * This function is called when the user clicks on the submit button. 
 * It takes the form values and sends them to the userService.register function. 
 * If the user is successfully registered, the user is redirected to the verification page
 */
  onSubmit(){
    console.log("value() ",this.registerForm.value)
    this.userService.register(this.registerForm.value).subscribe(data =>{
      if(data.statusCode == 200){
        this.router.navigate(['verification', this.registerForm.value.email_id])
      }
    })
  }
}

