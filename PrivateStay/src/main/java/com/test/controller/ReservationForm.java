package com.test.controller;
import javax.validation.constraints.NotEmpty;

import com.test.entity.ReservationStatus;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class ReservationForm {
    @NotEmpty(message = "필수입력사항입니다")
    private ReservationStatus status;
    @NotEmpty(message = "필수입력사항입니다.")
    private Integer headCount;

}
