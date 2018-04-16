package com.nic.deputation.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Repository;

import com.nic.deputation.model.User;
import com.nic.deputation.model.UserProfile;

@Repository("userDao")
public class UserDaoImpl implements UserDao {

	static final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);
	@Autowired
	DriverManagerDataSource dataSource;

	public User findById(int id) {
		String sql = "SELECT * FROM app_user WHERE ID = ?";

		Connection conn = null;
		User user = new User();
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				user.setId(rs.getInt("id"));
				user.setFirstName(rs.getString("first_name"));
				user.setLastName(rs.getString("last_name"));
				user.setEmail(rs.getString("first_name"));
				user.setPassword(rs.getString("password"));
				user.setSsoId(rs.getString("sso_id"));
			}
			rs.close();
			ps.close();
			return user;
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

	public User findBySSO(String sso) {
		String sql = "SELECT * FROM app_user WHERE sso_id = ?";

		Connection conn = null;
		User user = new User();
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, sso);

			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				user.setId(rs.getInt("id"));
				user.setFirstName(rs.getString("first_name"));
				user.setLastName(rs.getString("last_name"));
				user.setEmail(rs.getString("first_name"));
				user.setPassword(rs.getString("password"));
				user.setSsoId(rs.getString("sso_id"));
				user.setUserProfiles(this.findUserProfile(sso));
			}
			rs.close();
			ps.close();
			return user;
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

	private Set<UserProfile> findUserProfile(String sso) {
		Set<UserProfile> userProfiles = new HashSet<>();
		String sql = "SELECT type FROM user_profile where id \r\n" + 
				"in (SELECT user_profile_id FROM app_user_user_profile where user_id \r\n" + 
				"in (SELECT id FROM app_user where sso_id=?))";

		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, sso);

			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				UserProfile userProfile = new UserProfile();
				userProfile.setType(rs.getString("type"));
				userProfiles.add(userProfile);
			}
			rs.close();
			ps.close();
			return userProfiles;
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

	public List<User> findAllUsers() {

		return new ArrayList<User>();
	}

	public void save(User user) {

	}

	public void deleteBySSO(String sso) {

	}

}
