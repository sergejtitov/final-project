package htp.controller.request;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.validation.constraints.Email;
import javax.validation.constraints.Size;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserFront {

    @Email
    @Size(min = 1, max = 100)
    private String login;

    @Size(min = 8, max = 100)
    private String password;



}
