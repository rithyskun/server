package com.vpos.server.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

@Service
public class BusinessService {

    private final BusinessRepository businessRepository;

    @Autowired
    public BusinessService(BusinessRepository businessRepository) {
        this.businessRepository = businessRepository;
    }

    public List<Business> getBusiness() {
        return businessRepository.findAll();
    }


    public void createBusiness(Business business) {

        if(!StringUtils.hasText(business.getName())) {
            throw new IllegalStateException("Business name is required");
        }

        Optional<Business> findBusiness = businessRepository.findBusinessByName(business.getName());

        if(findBusiness.isPresent()) {
            throw new IllegalStateException("The business name " + business.getName() + " already exist");
        }

        businessRepository.save(business);
    }

    public void deleteBusinessById(Long id) {

        boolean exists = businessRepository.existsById(id);

        if(!exists) {
            throw new IllegalStateException("The business id " + id + " does not exists");
        }

//        businessRepository.deleteById(id);
        businessRepository.findBusinessByIdAndStatus(id);
    }

    @Transactional
    public void updateBusiness(Long busId, Business business) {

        Business _business = businessRepository.findById(busId).orElseThrow(() -> {
            throw new IllegalStateException("The business id \" + busId + \" does not exists.");
        });


        if(business != null) {
            if(!StringUtils.hasText(business.getName())){
                throw new IllegalStateException("Business name is required");
            }

            if(business.getUserId() == null) {
                throw new IllegalStateException("userId is required");
            }

            _business.setName(business.getName());
            _business.setAddress(business.getAddress());
            _business.setAddress1(business.getAddress1());
            _business.setIs_active(business.getIs_active());
            _business.setUserId(business.getUserId());
        }

        businessRepository.save(_business);

    }
}
