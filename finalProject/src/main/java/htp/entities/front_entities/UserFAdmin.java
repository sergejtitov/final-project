package htp.entities.front_entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserFAdmin {
    @Size(min = 1, max = 100)
    private String login;

    @Size(min = 8, max = 100)
    private String password;

    private List<String> role;
}
