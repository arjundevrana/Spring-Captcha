package com.nic.deputation.dao;

import java.util.List;

import com.nic.deputation.model.UserProfile;




public interface UserProfileDao {

	List<UserProfile> findAll();
	
	UserProfile findByType(String type);
	
	UserProfile findById(int id);
}
