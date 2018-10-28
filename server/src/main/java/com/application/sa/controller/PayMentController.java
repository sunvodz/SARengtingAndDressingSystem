package com.application.sa.controller;
import java.util.Collection;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.application.sa.Entity.*;
import com.application.sa.repository.*;
import java.util.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class PayMentController {
        
        @Autowired 
        private PayMentRepository paymentRepository;
        @Autowired
        private StaffRepository staffRepository;
        @Autowired
        private CustomerRepository customerRepository;
        @Autowired
        private BookingRepository bookingRepository;
        @Autowired
        private SellingRepository sellingRepository;
        @Autowired
        private LeaseRepository leaseRepository;

        public PayMentController(PayMentRepository paymentRepository,
                                 StaffRepository staffRepository,
                                 CustomerRepository customerRepository,
                                 BookingRepository bookingRepository,
                                 SellingRepository sellingRepository,
                                 LeaseRepository leaseRepository) {
            this.paymentRepository = paymentRepository;
            this.staffRepository = staffRepository;
            this.customerRepository = customerRepository;
            this.bookingRepository = bookingRepository;
            this.sellingRepository = sellingRepository;
            this.leaseRepository = leaseRepository;
        }
        
        @GetMapping("/payment")
        public Collection<PayMent> payment() {
            return paymentRepository.findAll().stream()
                    .filter(this::isPayMent)
                    .collect(Collectors.toList());
        }
        private boolean isPayMent(PayMent payMent){
            return payMent.getStatusPay().equals("paid");
        }

        @GetMapping("/pselling")
        public Collection<Selling> selling() {
            return sellingRepository.findAll().stream()
            .filter(this::isSelling)
            .collect(Collectors.toList());
        }
    private boolean isSelling(Selling selling){
        return selling.getStatusSelling().equals("not paid");
    }
        @GetMapping("/please")
        public Collection<Lease> lease() {
            return leaseRepository.findAll().stream()
            .filter(this::isLease)
            .collect(Collectors.toList());
        }
        private boolean isLease(Lease lease){
            return lease.getStatusLease().equals("not paid");
        }
        
        @GetMapping("/pbooking")
        public Collection<Booking> booking() {
            return bookingRepository.findAll().stream()
            .filter(this::isBooking)
            .collect(Collectors.toList());
        }
        private boolean isBooking(Booking booking){
            return booking.getStatusBooking().equals("not paid");
        }
        
        @GetMapping("/pstaff")
        public Collection<Staff> staff() {
            return staffRepository.findAll().stream()
                    .filter(this::isStaff)
                    .collect(Collectors.toList());
        }
            private boolean isStaff(Staff staff){
                return staff.getStaffName().equals("Admin");
        }

        @PostMapping("/payment/{typepay}/{statuspay}/{customer}/{selling}/{style}/{lease}")
        public PayMent newPayMent(@PathVariable String typepay,@PathVariable String statuspay,
                            @PathVariable String customer,@PathVariable Long selling,
                            @PathVariable Long style,@PathVariable Long lease){
        PayMent newPayMent = new PayMent();
        newPayMent.setStatusPay(statuspay);
        newPayMent.setTypePay(typepay);
        
        Date datePay = new Date();
        newPayMent.setDatePay(datePay);
       
        Customer customerid = customerRepository.findByCustomerIDs(customer);
        newPayMent.setCustomer(customerid);
        
        
        Selling sellingid = sellingRepository.findBySellingId(selling);
        newPayMent.setSelling(sellingid);
       
        Booking styleid = bookingRepository.findBybookingId(style);
        newPayMent.setBooking(styleid);
       
        Lease leaseid = leaseRepository.findByLeaseId(lease);
        newPayMent.setLease(leaseid);
      
        return paymentRepository.save(newPayMent); 
    }

    @PutMapping("/booking/{id}/{statusBooking}")
    Booking replaceBooking(Booking newBooking, @PathVariable String statusBooking, @PathVariable Long id){

        return bookingRepository.findById(id)
                    .map(booking ->{
                    booking.setStatusBooking(statusBooking);
                    return bookingRepository.save(booking);
                }
                ).orElseGet(() ->{
                    newBooking.setBookingId(id);
                    return bookingRepository.save(newBooking);
        });
}
    @PutMapping("/selling/{id}/{statusSelling}")
    Selling replaceSelling(Selling newSelling, @PathVariable String statusSelling, @PathVariable Long id){

    return sellingRepository.findById(id)
                .map(selling ->{
                selling.setStatusSelling(statusSelling);
                return sellingRepository.save(selling);
            }
            ).orElseGet(() ->{
                newSelling.setSellingId(id);
                return sellingRepository.save(newSelling);
    });
}
    @PutMapping("/lease/{id}/{statusLease}")
    Lease replaceLease(Lease newLease, @PathVariable String statusLease, @PathVariable Long id){

    return leaseRepository.findById(id)
                .map(lease ->{
                lease.setStatusLease(statusLease);
                return leaseRepository.save(lease);
            }
            ).orElseGet(() ->{
                newLease.setLeaseId(id);
                return leaseRepository.save(newLease);
    });
}


}