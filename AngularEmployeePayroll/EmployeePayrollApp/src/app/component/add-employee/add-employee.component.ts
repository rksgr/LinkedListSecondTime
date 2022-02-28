import { Component, OnInit } from '@angular/core';
import { FormArray, FormBuilder,FormGroup,FormControl, Validators} from '@angular/forms';
import { Employee } from 'src/app/model/employee';
import { HttpService } from 'src/app/service/http.service';
import { MatCheckboxChange } from '@angular/material/checkbox';
import { DataService } from 'src/app/service/data.service';
import { Router } from '@angular/router';


@Component({
  selector: 'app-add-employee',
  templateUrl: './add-employee.component.html',
  styleUrls: ['./add-employee.component.scss']
})
export class AddEmployeeComponent implements OnInit {
  public employee:Employee = new Employee();

  addEmployeeForm:FormGroup;

  departments:Array<any> =[
    {
      id:1,
      name:"HR",
      value: "HR",
      checked:false
    },
    {
      id:2,
      name:"Finance",
      value: "Finance",
      checked:false
    },
    {
      id:3,
      name:"Engineering",
      value: "Engineering",
      checked:false
    },
  ]

  formatSalary(value:number){
    if(value>=1000){
      return Math.round(value/1000) + 'k';
    }
    return value;
  }

  constructor( private formBuilder:FormBuilder,
              private httpService: HttpService,
              private dataService: DataService,
              private router: Router){

 this.addEmployeeForm = this.formBuilder.group({
    name: new FormControl('',
                            [Validators.required,Validators.minLength(4)]), //Validators.pattern("[A-Z]{1}[a-zA-Z]{3,}")
    profilePic: new FormControl('',
                                  [Validators.required]),
    gender: new FormControl('',
                            [Validators.required]),
    department: this.formBuilder.array([], []),
    salary: new FormControl(''),
    startDate: new FormControl('',
                                Validators.required),
    note: new FormControl('',
                          [Validators.required,Validators.minLength(7)])
  })
}

  ngOnInit() {
    console.log(this.employee);

  }
  onCheckBoxChange(event: MatCheckboxChange){
    const department: FormArray = this.addEmployeeForm.get('department') as FormArray;

    if(event.checked){
      department.push(new FormControl(event.source.value));
      console.log(department);
    }else{
      const index = department.controls.findIndex(x => x.value === event.source.value);
      department.removeAt(index);
    }
  }

  onSubmit() {
    this.employee = this.addEmployeeForm.value;
    console.log(this.employee);
   
    this.httpService.addEmployee(this.employee).subscribe(response =>{
     console.log(response);
     this.router.navigateByUrl("/home-page");
    });
  }
}