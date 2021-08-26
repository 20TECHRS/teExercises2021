package com.techelevator.application.jdbcdao;

import com.techelevator.application.dao.PreferenceDAO;
import com.techelevator.application.model.Preferences;
import com.techelevator.security.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class JDBCPreferenceDAO implements PreferenceDAO {
    private JdbcTemplate theDatabase;
    public JDBCPreferenceDAO(DataSource theDataSource) {
        this.theDatabase = new JdbcTemplate(theDataSource);
    }

    public Preferences setPreferences(Preferences userPreferences) {
        String newPreferenceStmt = "insert into preferences (user_id, cuisine_style_1, cuisine_style_2, cuisine_style_3, " +
                                   "price_point, dietary_restrictions) values (?, ?, ?, ?, ?, ?)"; // removed vegan/vegetarian/gluten free and replaced with dietary_restrictions
        theDatabase.update(newPreferenceStmt, userPreferences.getUserId(), userPreferences.getCuisineStyle1(), userPreferences.getCuisineStyle2(),
                           userPreferences.getCuisineStyle3(), userPreferences.getPricePoint(), userPreferences.getDietaryRestrictions());
                //userPreferences.getVegan(), userPreferences.getVegetarian(), userPreferences.getGlutenFree());

        return userPreferences;
    };

    public Preferences viewPreferences(long currentUserId) {
        String sqlSearch = "select * from preferences where user_id = ?";
        Preferences userPreferences = new Preferences();

        SqlRowSet result = theDatabase.queryForRowSet(sqlSearch, currentUserId);

        if (result.next()) {
            userPreferences = mapToPreference(result);
        }
        return userPreferences;
    };

    public Preferences updatePreferences(Preferences updatedPreferences) {
        String sqlUpdateStmt = "update preferences set cuisine_style_1 = ?, cuisine_style_2 = ?, cuisine_style_3 = ?, "
                               + "price_point = ?, dietary_restrictions = ? " +
                " where user_id = ?";
        theDatabase.update(sqlUpdateStmt, updatedPreferences.getCuisineStyle1(),
                           updatedPreferences.getCuisineStyle2(), updatedPreferences.getCuisineStyle3(),
                           updatedPreferences.getPricePoint(), updatedPreferences.getDietaryRestrictions(),
               // updatedPreferences.getVegan(), updatedPreferences.getVegetarian(), updatedPreferences.getGlutenFree(),
                           updatedPreferences.getUserId());

        return updatedPreferences;
    };

    public void deletePreferences(long currentUserId) {
        String sqlDeleteStmt = "delete from preferences where user_id = ? ";
        theDatabase.update(sqlDeleteStmt, currentUserId);
    };


    // helper methods

    private Preferences mapToPreference(SqlRowSet row) {
        Preferences newPreferences = new Preferences();
        newPreferences.setUserId(row.getLong("user_id"));
        newPreferences.setCuisineStyle1(row.getString("cuisine_style_1"));
        newPreferences.setCuisineStyle2(row.getString("cuisine_style_2"));
        newPreferences.setCuisineStyle3(row.getString("cuisine_style_3"));
        newPreferences.setPricePoint(row.getString("price_point"));
        newPreferences.setDietaryRestrictions(row.getString("dietary_restrictions"));

//        if (row.getString("vegan") != null) {
//            newPreferences.setVegan(row.getString("vegan"));
//        }
//
//        if (row.getString("vegetarian") != null) {
//            newPreferences.setVegetarian(row.getString("vegetarian"));
//        }
//
//        if (row.getString("gluten_free") != null) {
//            newPreferences.setGlutenFree(row.getString("gluten_free"));
//        }

        return newPreferences;
    }

}
