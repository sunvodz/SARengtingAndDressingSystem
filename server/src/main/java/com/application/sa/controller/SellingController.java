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

public class SellingController {
    @Autowired
    private SellingRepository sellingRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private StaffRepository staffRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private  StatusRepository statusRepository;

    public SellingController(StatusRepository statusRepository ,SellingRepository sellingRepository,CustomerRepository customerRepository,StaffRepository staffRepository, ProductRepository productRepository) {
        this.sellingRepository = sellingRepository;
        this.customerRepository = customerRepository;
        this.staffRepository = staffRepository;
        this.statusRepository = statusRepository;
        this.productRepository = productRepository;
    }
    @GetMapping("/selling")
    public Collection<Selling> selling() {
        return sellingRepository.findAll().stream()
                .collect(Collectors.toList());
    }

    @GetMapping("/productSelling")
    public Collection<Product> product() {
        return productRepository.findAll().stream()
                .filter(this::isProduct)
                .collect(Collectors.toList());
    }
    private boolean isProduct(Product product){

        return product.getStatus().getStatusProduct().equals("Selling");
    }

    @RequestMapping("/staffSeller")
    public Collection<Staff> staff() {
        return staffRepository.findAll().stream()
                .filter(this::isStaff)
                .collect(Collectors.toList());
    }
    private boolean isStaff(Staff staff){
        return staff.getPosition().getPositionName().equals("Seller");
    }


    @PostMapping("/sell/{productID}/{productName}/{productPrice}/{customerID}/{staffIds}/{sellingDate}")
        public Selling newSSel(@PathVariable String productID,
                               @PathVariable String productName,
                               @PathVariable Integer productPrice,
                               @PathVariable String customerID,
                               @PathVariable String staffIds,
                               @PathVariable String sellingDate){
            Selling newSelling = new Selling();
            Product product = productRepository.findByProductIds(productID);
            Customer customer = customerRepository.findByCustomerIDs(customerID);
            Staff staff = staffRepository.findByStaffIds(staffIds);

        String sDate1 = sellingDate;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd:MM:yyyy");
        LocalDate date = LocalDate.parse(sDate1,formatter);

        newSelling.setProduct(product);
        newSelling.setCustomer(customer);
        newSelling.setStaff(staff);
        newSelling.setSellingDate(date);
        newSelling.setStatusSelling("not paid");
        System.out.println(date);
        return sellingRepository.save(newSelling);
        }


}
