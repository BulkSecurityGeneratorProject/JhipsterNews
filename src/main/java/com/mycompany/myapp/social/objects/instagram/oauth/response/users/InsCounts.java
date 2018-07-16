package com.mycompany.myapp.social.objects.instagram.oauth.response.users;

import com.google.gson.annotations.SerializedName;

public class InsCounts {
    @SerializedName("media")
    Integer media;
    @SerializedName("follows")
    Integer follows;
    @SerializedName("followed_by")
    Integer followedBy;

    public InsCounts() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        InsCounts insCounts = (InsCounts) o;

        if (media != null ? !media.equals(insCounts.media) : insCounts.media != null) return false;
        if (follows != null ? !follows.equals(insCounts.follows) : insCounts.follows != null) return false;
        return followedBy != null ? followedBy.equals(insCounts.followedBy) : insCounts.followedBy == null;
    }

    @Override
    public int hashCode() {
        int result = media != null ? media.hashCode() : 0;
        result = 31 * result + (follows != null ? follows.hashCode() : 0);
        result = 31 * result + (followedBy != null ? followedBy.hashCode() : 0);
        return result;
    }

    public Integer getMedia() {
        return media;
    }

    public Integer getFollows() {
        return follows;
    }

    public Integer getFollowedBy() {
        return followedBy;
    }

    @Override
    public String toString() {
        return "InsCounts{" +
            "media=" + media +
            ", follows=" + follows +
            ", followedBy=" + followedBy +
            '}';
    }
}
