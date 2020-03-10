package htp.entities.db_entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.sql.Timestamp;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
public class User {
 private Long userId;
 private String login;
 private String password;
 private Timestamp created;
 private Timestamp changed;
 private String role;
 private boolean isdeleted;



    public User() {
    }

    @Override
    public int hashCode() {

        return Objects.hash(userId, login, password, created, changed, role, isdeleted);
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
                Objects.equals(isdeleted, user.isdeleted) ;
    }
}
