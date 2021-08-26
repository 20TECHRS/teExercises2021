package com.techelevator.application.jdbcdao;

import com.techelevator.application.dao.ProfileDAO;
import com.techelevator.application.model.Profile;
import com.techelevator.security.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Date;
@Component
public class JDBCProfileDAO implements ProfileDAO {
    private JdbcTemplate theDatabase;
    public JDBCProfileDAO(DataSource theDataSource) {
        this.theDatabase = new JdbcTemplate(theDataSource);
    }

    @Override
    public Profile setProfile(Profile userProfile) {
        String sqlCreateProfile = "insert into profile (user_id, first_name, last_name, date_of_birth, email, zip_code) " +
                                  "values (?, ?, ?, ?, ?, ?)";

        theDatabase.update(sqlCreateProfile, userProfile.getUserId(), userProfile.getFirstName(),
                userProfile.getLastName(),userProfile.getDateOfBirth(), userProfile.getEmail(),
                userProfile.getZipCode());

        return userProfile;
    }

    public Profile viewProfile(long currentUserId) {
        Profile userProfile = new Profile();

        String sqlSearch = "select * from profile where user_id = ?";

        SqlRowSet result = theDatabase.queryForRowSet(sqlSearch, currentUserId);

        if (result.next()) {
            userProfile = mapToProfile(result);
        }

        return userProfile;
    }

    public Profile updateProfile(Profile updatedProfile) { // determine parameters
        String updateSql = "update profile set first_name = ?, last_name = ?, date_of_birth = ?, "
                         + " email = ?, zip_code = ? where user_id = ?";
        theDatabase.update(updateSql, updatedProfile.getFirstName(), updatedProfile.getLastName(),
                           updatedProfile.getDateOfBirth(), updatedProfile.getEmail(), updatedProfile.getZipCode(),
                           updatedProfile.getUserId());

        return updatedProfile;
    }

    public void deleteProfile(long currentUserId) {
        String deleteSql = "delete from profile where user_id = ?";

        theDatabase.update(deleteSql, currentUserId);
    }



    // helper methods

    private Profile mapToProfile (SqlRowSet row) {
        Profile newProfile = new Profile();

        newProfile.setUserId(row.getLong("user_id"));
        newProfile.setFirstName(row.getString("first_name"));
        newProfile.setLastName(row.getString("last_name"));
        newProfile.setDateOfBirth(row.getDate("date_of_birth"));
        newProfile.setEmail(row.getString("email"));
        newProfile.setZipCode(row.getInt("zip_code"));

     return newProfile;
    }

}
