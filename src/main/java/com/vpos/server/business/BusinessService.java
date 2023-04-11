package com.vpos.server.business;

/*
 * @created 11/04/2023 - 9:17 AM
 * @project server
 * @author Rithy SKUN
 */

import java.util.Collection;

public interface BusinessService {

    Business createBusiness(Business business);
    void deleteBusiness(Long id);

    Business updateBusiness(Long id, Business business);

    Collection<Business> getBusiness();

}
