package com.example.definitelynotrobots;

/**
 * A dietary preference for a user.
 * */
public class UserPreference {
    private Integer ID;
    public Integer getID() { return ID; }
    public UserPreference setID(Integer id) { ID = id; return this; }

    private Integer UserID;
    public Integer getUserID() { return UserID; }
    public UserPreference setUserID(Integer userID) { UserID = userID; return this; }

    private String Content;
    public String getContent() { return Content; }
    public void setContent(String content) { Content = content; }

    private PreferenceTypeEnum PreferenceType;
    public PreferenceTypeEnum getPreferenceType() { return PreferenceType; }
    public void setPreferenceType(PreferenceTypeEnum preferenceType) { PreferenceType = preferenceType; }

    public UserPreference(Integer userID, String content, PreferenceTypeEnum preferenceType) {
        UserID = userID;
        Content = content;
        PreferenceType = preferenceType;
    }
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
