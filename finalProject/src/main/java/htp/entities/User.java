package htp.entities;


import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import java.sql.Timestamp;
import java.util.Objects;

public class User {
 private long userId;
 private String login;
 private String password;
 private Timestamp created;
 private Timestamp changed;
 private String role;
 private boolean isDeleted;

    public User(long userId, String login, String password, Timestamp created, Timestamp changed, String role, boolean isDeleted) {
        this.userId = userId;
        this.login = login;
        this.password = password;
        this.created = created;
        this.changed = changed;
        this.role = role;
        this.isDeleted = isDeleted;
    }

    public User() {
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    public Timestamp getChanged() {
        return changed;
    }

    public void setChanged(Timestamp changed) {
        this.changed = changed;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    @Override
    public int hashCode() {

        return Objects.hash(userId, login, password, created, changed, role, isDeleted);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(userId, user.userId) &&
                Objects.equals(login, user.login) &&
                Objects.equals(password, user.password) &&
                Objects.equals(created, user.created) &&
                Objects.equals(changed, user.changed) &&
                Objects.equals(role, user.role) &&
                Objects.equals(isDeleted, user.isDeleted) ;
    }
}
