package com.vpos.server.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/business")
public class BusinessController {

    private final BusinessService businessService;

    @Autowired
    public BusinessController(BusinessService businessService) {
        this.businessService = businessService;
    }

    @GetMapping
    public List<Business> getBusiness() {
        return businessService.getBusiness();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createBusiness(@RequestBody Business business) {
        businessService.createBusiness(business);
    }

    @DeleteMapping(path = "{busId}")
    @ResponseStatus(code = HttpStatus.OK)
    public void deleteBusiness(@PathVariable("busId") Long id) {
        businessService.deleteBusinessById(id);
    }

    @PutMapping(path = "{busId}")
    public void updateBusiness(@PathVariable("busId") Long id, @RequestBody Business business) {
        businessService.updateBusiness(id, business);
    }
}
