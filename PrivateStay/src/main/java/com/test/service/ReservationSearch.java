package com.test.service;
import com.test.entity.ReservationStatus;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class ReservationSearch {
    ReservationStatus status;
    String userId;
}

