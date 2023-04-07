package com.vpos.server.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public ResponseEntity<Business> createBusiness(@RequestBody Business business) {
      Business _business = businessService.createBusiness(business);
      return ResponseEntity.ok(_business);
    }

    @DeleteMapping(path = "{busId}")
    public ResponseEntity<Map<String, Boolean>> deleteBusiness(@PathVariable("busId") Long id) {
        businessService.deleteBusinessById(id);

        Map<String, Boolean> response = new HashMap<>();
        response.put("The business id " + id + " has been removed", Boolean.TRUE);

        return ResponseEntity.ok(response);
    }

    @PutMapping(path = "{busId}")
    public ResponseEntity<Business> updateBusiness(@PathVariable("busId") Long id, @RequestBody Business business) {
      Business _business = businessService.updateBusiness(id, business);

      return ResponseEntity.ok(_business);
    }

    @GetMapping(path = "{busId}")
    public ResponseEntity<Business> findBusinessById(@PathVariable("busId") String id) {
        Business _business = businessService.findOneBusiness(id);

        return ResponseEntity.ok(_business);
    }
}
