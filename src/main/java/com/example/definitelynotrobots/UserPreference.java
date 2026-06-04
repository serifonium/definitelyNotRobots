package com.example.definitelynotrobots;

/**
 * A dietary preference for a user.
 * */
public class UserPreference {
    /**
     * A UUID for the preference.
     * */
    private Integer ID;
    public Integer getID() { return ID; }
    public UserPreference setID(Integer id) { ID = id; return this; }

    /**
     * The ID of the user who made the preference.
     * */
    private Integer UserID;
    public Integer getUserID() { return UserID; }
    public UserPreference setUserID(Integer userID) { UserID = userID; return this; }

    /**
     * The item to include or exclude.
     * */
    private String Content;
    public String getContent() { return Content; }
    public void setContent(String content) { Content = content; }

    /**
     * Type of preference (inclusion or exclusion).
     * */
    private PreferenceTypeEnum PreferenceType;
    public PreferenceTypeEnum getPreferenceType() { return PreferenceType; }
    public void setPreferenceType(PreferenceTypeEnum preferenceType) { PreferenceType = preferenceType; }

    /**
     * Create a dietary preference with no UUID.
     * @param userID The ID of the user who made the preference.
     * @param content The item to include or exclude.
     * @param preferenceType The type of preference.
     * */
    public UserPreference(Integer userID, String content, PreferenceTypeEnum preferenceType) {
        UserID = userID;
        Content = content;
        PreferenceType = preferenceType;
    }
    /**
     * Create a dietary preference with a UUID.
     * @param id The UUID of the preference.
     * @param userID The ID of the user who made the preference.
     * @param content The item to include or exclude.
     * @param preferenceType The type of preference.
     * */
    public UserPreference(Integer id, Integer userID, String content, PreferenceTypeEnum preferenceType) {
        ID = id;
        UserID = userID;
        Content = content;
        PreferenceType = preferenceType;
    }

    @Override
    public String toString() {
        return "UserPreference{" +
                "ID=" + ID +
                ", UserID=" + UserID +
                ", Content='" + Content + '\'' +
                ", PreferenceType=" + PreferenceType +
                '}';
    }
}
