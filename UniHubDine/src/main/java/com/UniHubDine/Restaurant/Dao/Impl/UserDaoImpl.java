package com.UniHubDine.Restaurant.Dao.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import com.UniHubDine.Restaurant.Dao.UserDao;
import com.UniHubDine.Restaurant.Model.User;

import jakarta.annotation.PostConstruct;

@Repository
public class UserDaoImpl extends JdbcDaoSupport implements UserDao {

	@Autowired
	DataSource datasource;
	
	@PostConstruct
	private void initialize() {
		setDataSource(datasource);
	}
	@Override
	public User getUserById(String userId) {
		String sql  = "select user_pswd,user_email,firstname,lastname,user_role from user where user_id =? ";
		
		return getJdbcTemplate().queryForObject(sql, new Object[] {userId }, new RowMapper<User>() {
			
			@Override	
			public User mapRow(ResultSet rs, int rowNum) throws SQLException {
				User user = new User();
				user.setUserId(userId);
				user.setPassword(rs.getString(1));
				user.setUseremail(rs.getString(2));
				user.setFirstName(rs.getString(3));
				user.setLastName(rs.getString(4));
				user.setUserRole(rs.getString(5));
				return user;
			}
		});
	}

	@Override
	public int createNewUser(User user) {
	    String sql = "INSERT INTO user(user_id, user_pswd, user_email, firstname, lastname, user_role) VALUES (?, ?, ?, ?, ?, ?);";

	    return getJdbcTemplate().update(new PreparedStatementCreator() {

	        @Override
	        public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
	            PreparedStatement ps = con.prepareStatement(sql);

	            ps.setString(1, user.getUserId());
	            ps.setString(2, user.getPassword());
	            ps.setString(3, user.getUseremail());
	            ps.setString(4, user.getFirstName());
	            ps.setString(5, user.getLastName());
	            ps.setString(6, "USER"); // Setting the default value for user_role

	            return ps;
	        }
	    });
	}


}
