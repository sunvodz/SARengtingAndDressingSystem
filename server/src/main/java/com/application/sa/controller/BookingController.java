package com.application.sa.controller;

import com.application.sa.Entity.*;
import com.application.sa.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class BookingController {
    @Autowired
    private final BookingRepository bookingRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private StaffRepository staffRepository;
    @Autowired
    private StyleRepository styleRepository;
    @Autowired
    private PositionRepository positionRepository;

    public BookingController(BookingRepository bookingRepository, CustomerRepository customerRepository,
                             StaffRepository staffRepository, StyleRepository styleRepository,
                             PositionRepository positionRepository ) {
        this.bookingRepository = bookingRepository;
        this.positionRepository = positionRepository;
        this.staffRepository = staffRepository;
        this.customerRepository = customerRepository;
        this.styleRepository = styleRepository;
    }
    @RequestMapping("/booking")
    public Collection<Booking> booking() {
        return bookingRepository.findAll().stream()
                .collect(Collectors.toList());
    }

    @RequestMapping("/style")
    public Collection<Style> style() {
        return styleRepository.findAll().stream()
                .collect(Collectors.toList());
    }
    @PostMapping("/makeupBooking/{styleID}/{styleIDs}/{styleName}/{stylePrice}/{bookingDate}/{cusId}/{customerIDs}/{customerName}/{addressCustomer}/{stID}/{staffIDs}/{staffName}")
    public Booking newMakeupBooking(@PathVariable Long cusId, @PathVariable String customerIDs,
                                    @PathVariable String customerName, @PathVariable String addressCustomer,
                                    @PathVariable Long stID, @PathVariable String staffIDs,
                                    @PathVariable String staffName, @PathVariable String bookingDate,
                                    @PathVariable Long styleID, @PathVariable String styleIDs,
                                    @PathVariable String styleName, @PathVariable int stylePrice) {

        Booking newBooking = new Booking();

        String bDate1 = bookingDate;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd:MM:yyyy");
        LocalDate date = LocalDate.parse(bDate1, formatter);
        newBooking.setBookingDate(date);
        newBooking.setStatusBooking("not paid");

        Customer customer0 = customerRepository.findByCusId(cusId);
        newBooking.setCustomer(customer0);

        Staff staff0 = staffRepository.findByStaffId(stID);
        newBooking.setStaff(staff0);

        Style    style0 =  styleRepository.findBystyleID(styleID);
        newBooking.setStyle(style0);

        return bookingRepository.save(newBooking);
    }
}
