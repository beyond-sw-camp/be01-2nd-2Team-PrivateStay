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
		if(businessRepository.findById(business.getBusiness_code()).isPresent()) {
			throw new RuntimeException("이미 존재하는 사업자코드입니다.");
		}
		return businessRepository.save(business);
	}

	@Override
	public Business getBusinessById(String businessId) {
		return businessRepository.findById(businessId).get();
	}

	@Override
	public Business updateBusiness(String businessId, Business updatedBusiness) {
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
	public void deleteBusiness(String businessId) {
        businessRepository.deleteById(businessId);		
	}

	@Override
	public boolean loginBusiness(String businessId, String business_pwd) {
		return businessRepository.findById(businessId)
	            .map(business -> business.getBusiness_pwd().equals(business_pwd))
	            .orElse(false);
	}

}
