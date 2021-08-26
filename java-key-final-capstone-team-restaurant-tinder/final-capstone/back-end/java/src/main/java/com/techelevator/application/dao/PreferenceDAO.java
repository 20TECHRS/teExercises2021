package com.techelevator.application.dao;

import com.techelevator.application.model.Preferences;
import com.techelevator.security.model.User;

public interface PreferenceDAO {

    Preferences setPreferences(Preferences userPreferences);

    Preferences viewPreferences(long currentUserId);

    Preferences updatePreferences(Preferences updatedPreferences);

    void deletePreferences(long currentUserId);

}
