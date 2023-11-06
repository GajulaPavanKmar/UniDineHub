package com.UniHubDine.Restaurant.Controller.Dao.Impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
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
		String sql  = "select user_pswd from user where user_id =? ";
		
		return getJdbcTemplate().queryForObject(sql, new Object[] {userId }, new RowMapper<User>() {
			
			@Override
			public User mapRow(ResultSet rs, int rowNum) throws SQLException {
				User user = new User();
				user.setUserId(userId);
				user.setPassword(rs.getString(1));
				return user;
			}
		});
	}

}
