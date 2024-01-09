package com.test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.entity.Business;
import com.test.repository.BusinessRepository;

@Service
public class BusinessServiceImpl implements BusinessService {

	@Autowired
	private BusinessRepository businessRepository;
	
	@Override
	public Business saveBusiness(Business business) {
		return businessRepository.save(business);
	}

	@Override
	public Business getBusinessById(Long businessId) {
		return businessRepository.findById(businessId).get();
	}

	@Override
	public Business updateBusiness(Long businessId, Business updatedBusiness) {
		Business existingBusiness = businessRepository.findById(businessId).orElse(null);
        if (existingBusiness != null) {
            existingBusiness.setBusiness_pwd(updatedBusiness.getBusiness_pwd());
            existingBusiness.setBusiness_name(updatedBusiness.getBusiness_name());
            existingBusiness.setBusiness_reg_date(updatedBusiness.getBusiness_reg_date());
            return businessRepository.save(existingBusiness);
        }
        return null;
	}

	@Override
	public void deleteBusiness(Long businessId) {
        businessRepository.deleteById(businessId);		
	}

}
