package com.htp.controller.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import java.sql.Timestamp;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApplicantFront {

    @Size(min = 1, max = 100)
    private String firstName;

    @Size(min = 1, max = 100)
    private String secondName;

    @Size(max = 100)
    private String patronymic;

    @NotNull
    private Integer typeOfApplicant;

    @Past
    private Timestamp dateOfBirthday;

    @PositiveOrZero
    private Double income;

    @Size(min = 1, max = 1)
    private String gender;

    @NotNull
    private Integer experience;

    @NotNull
    private Integer maritalStatus;

    @NotNull
    private Integer education;

    @PositiveOrZero
    private Integer childrenQuantity;

    @Size(max = 20)
    private String personalNumber;

    private List<PhoneFront> phones;

    private List<AddressFront> addresses;

}
