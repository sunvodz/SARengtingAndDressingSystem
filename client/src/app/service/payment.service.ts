import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

@Injectable()
export class PaymentService {

  constructor(private http: HttpClient) {}

getStaff(): Observable<any> {
    return this.http.get('//localhost:8080/pstaff');
}
getCustomer(): Observable<any> {
    return this.http.get('//localhost:8080/customer');
}
 getPayment(): Observable<any> {
   return this.http.get('//localhost:8080/payment');
 }
getSelling(): Observable<any> {
  return this.http.get('//localhost:8080/pselling');
}
getBooking(): Observable<any> {
  return this.http.get('//localhost:8080/pbooking');
}

 getLease(): Observable<any> {
  return this.http.get('//localhost:8080/please' );
}

}


