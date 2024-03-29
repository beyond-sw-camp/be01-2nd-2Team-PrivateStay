package com.test.service;

import java.util.List;

import com.test.entity.Business;

public interface BusinessService {

	Business saveBusiness(Business business); 						// 1. 사업자 가입(추가)하기
	Business getBusinessByCode(String bId);							// 2. 사업자 찾기
	Business updateBusiness(String bId, Business updatedBusiness); 	// 3. 사업자 수정하기
    void deleteBusiness(String bId);								// 4. 사업자 삭제하기
    boolean loginBusiness(String bId, String bpwd);  				// 5. 사업자 로그인
    
    // 포스트맨
    Business saveB(Business business);					// 사업자 추가
    Business getBByCode(String bcode); 					// 사업자 정보 보기
 	List<Business> getAllB();							// 전체 사업자 보기 
 	Business updateB(String bcode, Business business);	// 사업자 정보 업데이트
 	void deleteBByCode(String bcode); 					// 사업자 삭제
    
}


