package com.nic.deputation.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository("tokenRepositoryDao")
@Transactional
public class TokenRepositoryImpl implements PersistentTokenRepository {
	@Autowired
	DriverManagerDataSource dataSource;
	static final Logger logger = LoggerFactory.getLogger(TokenRepositoryImpl.class);

	@Override
	public void createNewToken(PersistentRememberMeToken token) {
		String insertTableSQL = "INSERT INTO PERSISTENT_LOGINS" + "(username, series, token, last_used) VALUES"
				+ "(?,?,?,?)";
		Connection conn = null;

		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(insertTableSQL);
			ps.setString(1, token.getUsername());
			ps.setString(2, token.getSeries());
			ps.setString(3, token.getTokenValue());
			ps.setDate(4, new java.sql.Date(token.getDate().getTime()));
			ps.executeUpdate();
			ps.close();
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

	@Override
	public PersistentRememberMeToken getTokenForSeries(String seriesId) {
		logger.info("Fetch Token if any for seriesId : {}", seriesId);
		String sql = "SELECT * FROM PERSISTENT_LOGINS WHERE series = ?";

		Connection conn = null;
		PersistentRememberMeToken persistentRememberMeToken = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, seriesId);

			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				persistentRememberMeToken = new PersistentRememberMeToken(rs.getString("username"),
						rs.getString("series"), rs.getString("token"), rs.getDate("last_used"));

			}
			rs.close();
			ps.close();
			return persistentRememberMeToken;
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

	@Override
	public void removeUserTokens(String username) {
		logger.info("Removing Token if any for user : {}", username);
		String sql = "DELETE  FROM persistent_logins WHERE username  = ?";

		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			int rowsDeleted = ps.executeUpdate();
			if (rowsDeleted > 0) {
				System.out.println("A user was deleted successfully!");
			}
			ps.close();
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

	@Override
	public void updateToken(String seriesId, String tokenValue, Date lastUsed) {
		logger.info("Updating Token for seriesId : {}", seriesId);
		String insertTableSQL = "UPDATE   PERSISTENT_LOGINS set   token=?, last_used=? where series=?";

		Connection conn = null;

		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(insertTableSQL);
			ps.setString(1, tokenValue);
			ps.setDate(2, new java.sql.Date(lastUsed.getTime()));
			ps.setString(3, seriesId);
			int rowsUpdated = ps.executeUpdate();
			if (rowsUpdated > 0) {
				System.out.println("An existing user was updated successfully!");
			}
			ps.close();
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

}
