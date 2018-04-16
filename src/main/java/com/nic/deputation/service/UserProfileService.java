package com.nic.deputation.service;

import java.util.List;

import com.nic.deputation.model.UserProfile;




public interface UserProfileService {

	UserProfile findById(int id);

	UserProfile findByType(String type);
	
	List<UserProfile> findAll();
	
}
