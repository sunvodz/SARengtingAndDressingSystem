import { Component, OnInit} from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { PaymentService } from '../service/payment.service';


@Component({
  selector: 'app-payment',
  templateUrl: './payment.component.html',
  styleUrls: ['./payment.component.css']

})

export class PaymentComponent implements OnInit {


  CurrentDate = new Date();
  lease: Array<any>;
  selling: Array<any>;
  booking: Array<any>;
  userId: Array<any>;

  LeaseId: Array<any>;
  LeaseName: Array<any>;
  LeasePrice: Array<any>;
  LeaseStatus: Array<any>;

  SellingId: Array<any>;
  SellingName: Array<any>;
  SellingPrice: Array<any>;
  StatusSelling: Array<any>;

  Bookingid: Array<any>;
  BookingPrice: Array<any>;
  StatusBooking: Array<any>;

  userData = {
    userId : ''
  };

  leaseData = {
    LeaseId: '',
    LeaseName: '',
    LeasePrice: '',
    LeaseStatus: ''
  };

  sellingData = {
    SellingId: '',
    SellingName: '',
    SellingPrice: '',
    StatusSelling: ''
  };

  bookingData = {
    Bookingid: '',
    BookingPrice: '',
    StatusBooking: ''
  };
  payment: Array<any>;


  constructor(private paymentService: PaymentService, private httpClient: HttpClient ) { }

  ngOnInit() {

    this.paymentService.getCustomer().subscribe(data => {
      this.userId = data;
      console.log(this.userId);
    });
  }
  searchId() {
    this.httpClient.get('http://localhost:8080/lease/'),
    this.httpClient.get('http://localhost:8080/selling/'),
    this.httpClient.get('http://localhost:8080/booking/')
    .subscribe(
        data => {
          this.paymentService.getLease(), this.paymentService.getSelling();
            console.log('Search id successful', data);
        },
        error => {
            console.log('Error', error);
        }
    );
    this.paymentService.getLease().subscribe(data => {
      this.LeaseName = data;
      this.LeasePrice = data;
      this.LeaseStatus = data;
      console.log(this.LeaseName);
    });

    this.paymentService.getSelling().subscribe(data => {
      this.SellingName = data;
      this.SellingPrice = data;
      this.StatusSelling = data;
      console.log(this.SellingName);
    });

    this.paymentService.getBooking().subscribe(data => {
      this.Bookingid = data;
      this.BookingPrice = data;
      this.StatusBooking = data;
      console.log(this.Bookingid);
    });
  }
  payLeaseButtom() {
    if (this.leaseData.LeaseId === '' ||
    this.leaseData.LeasePrice ===  ''
    ) {
     alert('กรอกข้อมูลให้ครบถ้วน');
} else {
  this.httpClient.post('http://localhost:8080/payment/' + 'Lease' + '/' + 'paid' + '/' + this.userData.userId +
                                                    '/' + 0 + '/' + 0 + '/' + this.leaseData.LeaseId, this.payment)
  .subscribe(
    data => {
        console.log('Post successful', data);
        alert('สำเร็จ');
    },
    error => {
        console.log('Error', error);
    }
);
this.httpClient.put('http://localhost:8080/lease/' + this.leaseData.LeaseId + '/' + 'paid', this.lease)
    .subscribe(
      data => {
        if (data) {
          console.log('Success');
        }
      },
      error => {
      console.log('error', error);
    }
    );

  }
}
  paySellingButtom() {
    if (this.sellingData.SellingId === '' ||
    this.sellingData.SellingPrice ===  ''
    ) {
     alert('กรอกข้อมูลให้ครบถ้วน');
} else {
  this.httpClient.post('http://localhost:8080/payment/' + 'Selling' + '/' + 'paid' + '/' + this.userData.userId +
                                                    '/' + this.sellingData.SellingId + '/' + 0 + '/' + 0, this.payment)
  .subscribe(
    data => {
        console.log('Post successful', data);
        alert('สำเร็จ');
    },
    error => {
        console.log('Error', error);
    }
);
this.httpClient.put('http://localhost:8080/selling/' + this.sellingData.SellingId + '/' + 'paid', this.selling)
    .subscribe(
      data => {
        if (data) {
          console.log('Success');
        }
      },
      error => {
      console.log('error', error);
    }
    );
  }
  }
  payBookingButtom() {

    if (this.bookingData.Bookingid === '' ||
  this.bookingData.BookingPrice ===  ''
  ) {
   alert('กรอกข้อมูลให้ครบถ้วน');
} else {
this.httpClient.post('http://localhost:8080/payment/' + 'Booking' + '/' + 'paid' + '/' + this.userData.userId +
                                                  '/' + 0 + '/' + this.bookingData.Bookingid + '/' + 0, this.payment)
.subscribe(
  data => {
      console.log('Post successful', data);
      alert('สำเร็จ');
  },
  error => {
      console.log('Error', error);
  }
);
this.httpClient.put('http://localhost:8080/booking/' + this.bookingData.Bookingid + '/' + 'paid', this.booking)
    .subscribe(
      data => {
        if (data) {
          console.log('Success');
        }
      },
      error => {
      console.log('error', error);
    }
    );
}

}


}
