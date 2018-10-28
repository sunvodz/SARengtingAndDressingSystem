import { Component, OnInit } from '@angular/core';
import {ViewChild} from '@angular/core';
import {MatSort} from '@angular/material';
import { HttpClient} from '@angular/common/http';
import {SalaryService} from '../service/salary.service';

@Component({
  selector: 'app-salary',
  templateUrl: './salary.component.html',
  styleUrls: ['./salary.component.css']
})

export class SalaryComponent implements OnInit {
  displayedColumns1: string [] = [ 'staffId' , 'staffIds' , 'staffName' , 'staffSalary' , 'jobtypeName' , 'staffStatus' ,
    'payerName' ];
  staffId: Array<any>;
  Payers: Array<any>;
  Genders: Array<any>;
  payerName: Array<any>;
  positionName: Array<any>;
  Staffs: Array<any>;
  staffSalary: Array<any>;
  Educations: Array<any>;
  staffName: Array<any>;
  payer: Array<any>;
  staffIds: Array<any>;
  Positions: Array<any>;
  Jobtypes: Array<any>;
  jobtypeName: Array<any>;
  staffStatus: Array<any>;
  Staff = {
    staffId: '',
    staffName: ''
  };
  num = 2;
  @ViewChild(MatSort)
  sort: MatSort;

  constructor(private salaryservice: SalaryService, private httpClient: HttpClient ) {}

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

  save() {

      this.httpClient.put('http://localhost:8080/staffs/' + this.staffId + '/' + this.staffIds + '/' + this.staffName
        + '/' + this.staffSalary + '/' + this.jobtypeName + '/' + this.payerName + '/' + 'Paid' , this.Staffs)
        .subscribe(
          data => {
            console.log('PUT Request is successful', data);
            this.staffStatus = [];
            this.staffIds = [];
            this.jobtypeName = [];
            this.payerName = [];
            this.staffSalary = [];
            this.staffId = [];
            this.staffName = [];

            alert('จ่ายแล้ว');
          },
          error => {
            console.log('Error', error);

          }
        );
    }
  }

