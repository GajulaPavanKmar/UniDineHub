package com.UniHubDine.Restaurant.Controller.Dao.Impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import com.UniHubDine.Restaurant.Controller.Dao.MenuDao;
import com.UniHubDine.Restaurant.Controller.bean.Menu;

import jakarta.annotation.PostConstruct;

@Repository
public class MenuDaoImpl extends JdbcDaoSupport implements MenuDao {

    @Autowired
    DataSource dataSource;

    @PostConstruct
    private void initialize() {
        setDataSource(dataSource);
    }

    @Override
    public List<Menu> findAll() {
        String sql = "SELECT * FROM menu";
        return getJdbcTemplate().query(sql, new MenuRowMapper());
    }

    @Override
    public Menu findById(int id) {
        String sql = "SELECT * FROM menu WHERE id = ?";
        return getJdbcTemplate().queryForObject(sql, new Object[]{id}, new MenuRowMapper());
    }

    private static final class MenuRowMapper implements RowMapper<Menu> {
        public Menu mapRow(ResultSet rs, int rowNum) throws SQLException {
            Menu menu = new Menu();
            menu.setId(rs.getInt("menu_id"));
            menu.setName(rs.getString("menu_name"));
            menu.setDescription(rs.getString("menu_description"));
            menu.setImage(rs.getString("image_path"));
            return menu;
        }
    }
}
