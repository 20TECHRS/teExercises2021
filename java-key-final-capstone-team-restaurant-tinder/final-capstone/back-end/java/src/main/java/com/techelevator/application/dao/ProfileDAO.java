package com.techelevator.application.dao;

import com.techelevator.application.model.Profile;
import com.techelevator.security.model.User;

import java.sql.Date;

public interface ProfileDAO {

   Profile setProfile(Profile userProfile);

   Profile viewProfile(long currentUserId);

   Profile updateProfile(Profile updatedProfile);

   void deleteProfile(long currentUserId);
}
