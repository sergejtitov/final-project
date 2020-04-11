package com.htp.controller.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PhoneFront {

    @Size(max = 100)
    private String phoneType;

    @Size(max = 100)
    private String phoneNumber;

}
