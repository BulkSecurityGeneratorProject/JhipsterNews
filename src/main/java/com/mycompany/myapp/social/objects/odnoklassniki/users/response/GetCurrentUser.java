package com.mycompany.myapp.social.objects.odnoklassniki.users.response;

import com.google.gson.annotations.SerializedName;

public class GetCurrentUser {

    @SerializedName("uid")
    private String uid;
    @SerializedName("birthday")
    private String birthday;
    @SerializedName("age")
    private Integer age;
    @SerializedName("name")
    private String name;
    @SerializedName("locale")
    private String locale;
    @SerializedName("gender")
    private String gender;
    @SerializedName("location")
    private Location location;
    @SerializedName("first_name")
    private String firstName;
    @SerializedName("last_name")
    private String lastName;
    @SerializedName("has_email")
    private boolean hasEmail;
    @SerializedName("pic_3")
    private String pic3;

    public GetCurrentUser() {
    }

    public String getUid() {
        return uid;
    }

    public String getBirthday() {
        return birthday;
    }

    public Integer getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public String getLocale() {
        return locale;
    }

    public String getGender() {
        return gender;
    }

    public Location getLocation() {
        return location;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public boolean isHasEmail() {
        return hasEmail;
    }

    public String getPic3() {
        return pic3;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GetCurrentUser that = (GetCurrentUser) o;

        if (isHasEmail() != that.isHasEmail()) return false;
        if (getUid() != null ? !getUid().equals(that.getUid()) : that.getUid() != null) return false;
        if (getBirthday() != null ? !getBirthday().equals(that.getBirthday()) : that.getBirthday() != null)
            return false;
        if (getAge() != null ? !getAge().equals(that.getAge()) : that.getAge() != null) return false;
        if (getName() != null ? !getName().equals(that.getName()) : that.getName() != null) return false;
        if (getLocale() != null ? !getLocale().equals(that.getLocale()) : that.getLocale() != null) return false;
        if (getGender() != null ? !getGender().equals(that.getGender()) : that.getGender() != null) return false;
        if (getLocation() != null ? !getLocation().equals(that.getLocation()) : that.getLocation() != null)
            return false;
        if (getFirstName() != null ? !getFirstName().equals(that.getFirstName()) : that.getFirstName() != null)
            return false;
        if (getLastName() != null ? !getLastName().equals(that.getLastName()) : that.getLastName() != null)
            return false;
        return getPic3() != null ? getPic3().equals(that.getPic3()) : that.getPic3() == null;
    }

    @Override
    public int hashCode() {
        int result = getUid() != null ? getUid().hashCode() : 0;
        result = 31 * result + (getBirthday() != null ? getBirthday().hashCode() : 0);
        result = 31 * result + (getAge() != null ? getAge().hashCode() : 0);
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (getLocale() != null ? getLocale().hashCode() : 0);
        result = 31 * result + (getGender() != null ? getGender().hashCode() : 0);
        result = 31 * result + (getLocation() != null ? getLocation().hashCode() : 0);
        result = 31 * result + (getFirstName() != null ? getFirstName().hashCode() : 0);
        result = 31 * result + (getLastName() != null ? getLastName().hashCode() : 0);
        result = 31 * result + (isHasEmail() ? 1 : 0);
        result = 31 * result + (getPic3() != null ? getPic3().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "GetCurrentUser{" +
            "uid='" + uid + '\'' +
            ", birthday='" + birthday + '\'' +
            ", age=" + age +
            ", name='" + name + '\'' +
            ", locale='" + locale + '\'' +
            ", gender='" + gender + '\'' +
            ", location=" + location +
            ", firstName='" + firstName + '\'' +
            ", lastName='" + lastName + '\'' +
            ", hasEmail=" + hasEmail +
            ", pic3='" + pic3 + '\'' +
            '}';
    }
}
