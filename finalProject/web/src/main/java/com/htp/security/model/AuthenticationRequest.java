package com.htp.security.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Slf4j
@Data
@ApiModel(description = "Object with values for user authentication")
public class AuthenticationRequest implements Serializable {

    @NotEmpty
    @ApiModelProperty(required = true, dataType = "string", notes = "user's login")
    private String username;

    @NotEmpty
    @ApiModelProperty(required = true, dataType = "string", notes = "user's password")
    private String password;

}
