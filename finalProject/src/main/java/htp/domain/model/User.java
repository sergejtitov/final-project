package htp.domain.model;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.OneToMany;
import javax.persistence.FetchType;
import javax.persistence.CascadeType;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name = "m_user")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Column
    private String login;

    @Column
    private String password;

    @Column
    private Timestamp created;

    @Column
    private Timestamp changed;

    @Column
    private boolean isdeleted;

    @JsonManagedReference
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "user")
    private Set<Roles> roles;


    public User() {
    }

    public void setRoles(Set<Roles> roles) {
        if (this.roles==null){
            this.roles = roles;
        } else {
            this.roles.clear();
            if (roles != null) {
                this.roles.addAll(roles);
            }
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public int hashCode() {

        return Objects.hash(userId, login, password, created, changed, isdeleted);
    }

    @Override
    public String toString() {
        return "User{" + "userId=" + userId + ", login='" + login +", created='" + created + ", changed='" + changed + ", is Deleted='" + isdeleted +'}';
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
                Objects.equals(isdeleted, user.isdeleted) ;
    }
}
