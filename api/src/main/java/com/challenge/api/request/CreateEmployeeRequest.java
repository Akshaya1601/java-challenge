package com.challenge.api.request

import java.time.Instant;

public class CreateEmployeeRequest {
    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @Email
    @NotBlank
    private String email;

    @NotNull
    private Integer salary;

    @NotNull
    private Integer age;

    @NotBlank
    private string jobTitle;

    @NotNull
    private Instant contractHireDate;
}