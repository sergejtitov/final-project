package htp.entities.front_entities;

import htp.entities.db_entities.Roles;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


import javax.validation.constraints.Size;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@EqualsAndHashCode
@ToString
public class UserFAdmin {
    @Size(min = 1, max = 100)
    private String login;

    @Size(min = 8, max = 100)
    private String password;

    private List<Roles> role;
}
