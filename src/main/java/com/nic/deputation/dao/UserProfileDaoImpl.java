package com.nic.deputation.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Repository;
import com.nic.deputation.model.UserProfile;

@Repository("userProfileDao")
public class UserProfileDaoImpl implements UserProfileDao {
	@Autowired
	DriverManagerDataSource dataSource;
	public UserProfile findById(int id) {
		String sql = "SELECT * FROM user_profile WHERE id = ?";

		Connection conn = null;
		UserProfile userProfile = new UserProfile();
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				userProfile.setId(rs.getInt("id"));
				userProfile.setType(rs.getString("type"));
			}
			rs.close();
			ps.close();
			return userProfile;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}
	}

	public UserProfile findByType(String type) {
		
		String sql = "SELECT * FROM user_profile WHERE type = ?";

		Connection conn = null;
		UserProfile userProfile = new UserProfile();
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, type);

			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				userProfile.setId(rs.getInt("id"));
				userProfile.setType(rs.getString("type"));
			}
			rs.close();
			ps.close();
			return userProfile;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}
	}

	public List<UserProfile> findAll() {
		
		return new ArrayList<UserProfile>();
	}

}
