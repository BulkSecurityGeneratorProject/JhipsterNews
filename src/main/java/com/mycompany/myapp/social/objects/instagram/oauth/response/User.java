package com.mycompany.myapp.social.objects.instagram.oauth.response;

import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("id")
    private String uid;
    @SerializedName("username")
    private String username;
    @SerializedName("full_name")
    private String fullName;
    @SerializedName("profile_picture")
    private String profilePicture;

    public User() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (getUid() != null ? !getUid().equals(user.getUid()) : user.getUid() != null) return false;
        if (getUsername() != null ? !getUsername().equals(user.getUsername()) : user.getUsername() != null)
            return false;
        if (getFullName() != null ? !getFullName().equals(user.getFullName()) : user.getFullName() != null)
            return false;
        return getProfilePicture() != null ? getProfilePicture().equals(user.getProfilePicture()) : user.getProfilePicture() == null;
    }

    @Override
    public int hashCode() {
        int result = getUid() != null ? getUid().hashCode() : 0;
        result = 31 * result + (getUsername() != null ? getUsername().hashCode() : 0);
        result = 31 * result + (getFullName() != null ? getFullName().hashCode() : 0);
        result = 31 * result + (getProfilePicture() != null ? getProfilePicture().hashCode() : 0);
        return result;
    }

    public String getUid() {
        return uid;
    }

    public String getUsername() {
        return username;
    }

    public String getFullName() {
        return fullName;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    @Override
    public String toString() {
        return "User{" +
            "uid='" + uid + '\'' +
            ", username='" + username + '\'' +
            ", fullName='" + fullName + '\'' +
            ", profilePicture='" + profilePicture + '\'' +
            '}';
    }
}
