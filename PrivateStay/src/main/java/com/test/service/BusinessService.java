package com.test.service;

import com.test.entity.Business;

public interface BusinessService {

	Business saveBusiness(Business business);
	Business getBusinessById(Long businessId);
	
	Business updateBusiness(Long businessId, Business updatedBusiness);
    void deleteBusiness(Long businessId);
}
