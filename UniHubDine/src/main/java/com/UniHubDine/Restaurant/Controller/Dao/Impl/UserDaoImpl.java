package com.UniHubDine.Restaurant.Controller.Dao.Impl;

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

import com.UniHubDine.Restaurant.Controller.Dao.UserDao;
import com.UniHubDine.Restaurant.Controller.bean.User;

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
		String sql  = "select user_pswd,user_email,firstname,lastname from user where user_id =? ";
		
		return getJdbcTemplate().queryForObject(sql, new Object[] {userId }, new RowMapper<User>() {
			
			@Override
			public User mapRow(ResultSet rs, int rowNum) throws SQLException {
				User user = new User();
				user.setUserId(userId);
				user.setPassword(rs.getString(1));
				user.setUseremail(rs.getString(2));
				user.setFirstName(rs.getString(3));
				user.setLastName(rs.getString(4));
				return user;
			}
		});
	}
	@Override
	public int createNewUser(User user) {
		// TODO Auto-generated method stub
		String sql = "insert into user(user_id, user_pswd,user_email,firstname, lastname) values(?,?,?,?,?);";
		
		return getJdbcTemplate().update(new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				// TODO Auto-generated method stub
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setString(1, user.getUserId());
				ps.setString(2, user.getPassword());
				ps.setString(3, user.getUseremail());
				ps.setString(4, user.getFirstName());
				ps.setString(5, user.getLastName());

				return ps;
			}
		});
	}

}
