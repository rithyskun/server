package com.vpos.server.business;

import com.vpos.server.utils.AppUtils;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(path = "api/v1/business")
public class BusinessController {

    private final BusinessService businessService;

    @Autowired
    public BusinessController(BusinessServiceImpl businessServiceImpl) {
        this.businessService = businessServiceImpl;
    }

    @GetMapping
    public Collection<Business> getBusiness() {
        return businessService.getBusiness();
    }

    @PostMapping
    public ResponseEntity<Business> createBusiness(@Valid @RequestBody Business business) {
      Business _business = businessService.createBusiness(business);
      return ResponseEntity.ok(_business);
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<Map<String, Boolean>> deleteBusiness(@Valid @PathVariable("id") Long id) {
        businessService.deleteBusiness(id);

        Map<String, Boolean> response = new HashMap<>();
        response.put("The business id " + id + " has been removed", Boolean.TRUE);

        return ResponseEntity.ok().body(response);
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<Business> updateBusiness(@Valid @PathVariable("id") Long id, @RequestBody Business business) {
      Business _business = businessService.updateBusiness(id, business);

      return ResponseEntity.ok().body(_business);
    }

}
