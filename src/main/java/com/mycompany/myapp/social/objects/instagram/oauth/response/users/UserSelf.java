package com.mycompany.myapp.social.objects.instagram.oauth.response.users;

import com.google.gson.annotations.SerializedName;

public class UserSelf {

    @SerializedName("id")
    String uid;
    @SerializedName("username")
    String username;
    @SerializedName("full_name")
    String fullName;
    @SerializedName("profile_picture")
    String profilePicture;
    @SerializedName("bio")
    String bio;
    @SerializedName("website")
    String website;
    @SerializedName("is_business")
    String isBusiness;
    @SerializedName("counts")
    InsCounts counts;

    public UserSelf() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserSelf userSelf = (UserSelf) o;

        if (uid != null ? !uid.equals(userSelf.uid) : userSelf.uid != null) return false;
        if (username != null ? !username.equals(userSelf.username) : userSelf.username != null) return false;
        if (fullName != null ? !fullName.equals(userSelf.fullName) : userSelf.fullName != null) return false;
        if (profilePicture != null ? !profilePicture.equals(userSelf.profilePicture) : userSelf.profilePicture != null)
            return false;
        if (bio != null ? !bio.equals(userSelf.bio) : userSelf.bio != null) return false;
        if (website != null ? !website.equals(userSelf.website) : userSelf.website != null) return false;
        if (isBusiness != null ? !isBusiness.equals(userSelf.isBusiness) : userSelf.isBusiness != null) return false;
        return counts != null ? counts.equals(userSelf.counts) : userSelf.counts == null;
    }

    @Override
    public int hashCode() {
        int result = uid != null ? uid.hashCode() : 0;
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (fullName != null ? fullName.hashCode() : 0);
        result = 31 * result + (profilePicture != null ? profilePicture.hashCode() : 0);
        result = 31 * result + (bio != null ? bio.hashCode() : 0);
        result = 31 * result + (website != null ? website.hashCode() : 0);
        result = 31 * result + (isBusiness != null ? isBusiness.hashCode() : 0);
        result = 31 * result + (counts != null ? counts.hashCode() : 0);
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

    public String getBio() {
        return bio;
    }

    public String getWebsite() {
        return website;
    }

    public String getIsBusiness() {
        return isBusiness;
    }

    public InsCounts getCounts() {
        return counts;
    }

    @Override
    public String toString() {
        return "UserSelf{" +
            "uid=" + uid +
            ", username='" + username + '\'' +
            ", fullName='" + fullName + '\'' +
            ", profilePicture='" + profilePicture + '\'' +
            ", bio='" + bio + '\'' +
            ", website='" + website + '\'' +
            ", isBusiness='" + isBusiness + '\'' +
            ", counts=" + counts +
            '}';
    }
}
