package com.vpos.server.business;

import com.vpos.server.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

@Service
public class BusinessServiceImpl implements BusinessService {

    private final BusinessRepository businessRepository;
    private final UserRepository userRepository;

    @Autowired
    public BusinessServiceImpl(BusinessRepository businessRepository, UserRepository userRepository) {
        this.businessRepository = businessRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<Business> getBusiness() {
        return businessRepository.findAll();
    }

    @Override
    public Business createBusiness(Business business) {

        Optional<Business> findBusiness = businessRepository.findBusinessByName(business.getName());

        if(findBusiness.isPresent()) {
            throw new IllegalStateException("The business name " + business.getName() + " already exist");
        }

       return businessRepository.save(business);

    }

    @Override
    public void deleteBusiness(Long id) {

        boolean exists = businessRepository.existsById(id);

        if(!exists) {
            throw new IllegalStateException("The business id " + id + " does not exists");
        }

        businessRepository.findBusinessByIdAndStatus(id);
    }

    @Transactional
    @Override
    public Business updateBusiness(Long busId, Business business) {

        Business _business = businessRepository.findById(busId).orElseThrow(() -> new IllegalStateException("The business id \" + busId + \" does not exists."));

        if(business != null) {
            if(!StringUtils.hasText(business.getName())){
                throw new IllegalStateException("Business name is required");
            }

            _business.setName(business.getName());
            _business.setAddress(business.getAddress());
            _business.setAddress1(business.getAddress1());
            _business.setIs_active(business.getIs_active());
        }

      return businessRepository.save(_business);

    }

}
