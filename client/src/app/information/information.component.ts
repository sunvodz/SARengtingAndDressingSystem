import { Component, OnInit } from '@angular/core';
import {ViewChild} from '@angular/core';
import {MatSort} from '@angular/material';
import { HttpClient} from '@angular/common/http';
import {SalaryService} from '../service/salary.service';


@Component({
  selector: 'app-information',
  templateUrl: './information.component.html',
  styleUrls: ['./information.component.css']
})

export class InformationComponent implements OnInit {
  displayedColumns1: string [] =["staffId","staffIds","staffName","genderName"
    ,"educationName","staffPhone" ,"jobtypeName","staffSalary"
    ,"positionName"];

  Positions: Array<any>;
  positionName: Array<any>;
  gender: Array<any>;
  staffSalary:  Array<any>;
  staffPhone: Array<any>;
  Staffs: Array<any>;
  education: Array<any>;
  jobtype: Array<any>;
  staffId: Array<any>;
  staffName: Array<any>;
  Payers: Array<any>;
  payerName: Array<any>;
  Genders: Array<any>;
  genderName: Array<any>;
  Educations: Array<any>;
  educationName: Array<any>;
  Jobtypes: Array<any>;
  jobtypeName: Array<any>;
  staffIds : Array<any>;
  Staff={
    staffIds: 'St'+this.staffId
};
@ViewChild(MatSort)
  sort: MatSort;

  constructor(private salaryservice: SalaryService,private httpClient: HttpClient ) {}

  ngOnInit() {
    this.salaryservice.getPayer().subscribe(data => {
      this.Payers = data;
      console.log(this.Payers);
    });
    this.salaryservice.getPosition().subscribe(data => {
      this.Positions = data;
      console.log(this.Positions);
    });
    this.salaryservice.getStaff().subscribe(data => {
      this.Staffs = data;
      console.log(this.Staffs);
    });
    this.salaryservice.getGender().subscribe(data => {
      this.Genders = data;
      console.log(this.Genders);
    });
    this.salaryservice.getJobtype().subscribe(data => {
      this.Jobtypes = data;
      console.log(this.Jobtypes);
    });
    this.salaryservice.getEducation().subscribe(data => {
      this.Educations = data;
      console.log(this.Educations);
    });
  }

  Post() {
    this.httpClient.post('http://localhost:8080/staffs/'  + this.staffName + '/' + this.genderName
      + '/' + this.educationName + '/' + this.staffPhone + '/' + this.jobtypeName + '/' + "BigBoss" +'/'+ this.staffSalary + '/'
      + this.positionName +'/'+ "Un Paid", this.Staffs)

      .subscribe(
        data => {
          console.log('POST Request is successful', data);
          this.staffName = [];
          this.genderName = [];
          this.educationName = [];
          this.staffPhone = [];
          this.jobtypeName = [];
          this.staffSalary = [];
          this.positionName = [];
          alert('บันทึกข้อมูลเสร็จสิ้น');
        },
        error => {
          console.log('ข้อมูลผิดพลาดโปรตรวจสอบข้อมูลอีกครั้ง', error);
        }

      );
  }
}
