package com.dailyCodeWork.lakeSidehotel.response;

import com.dailyCodeWork.lakeSidehotel.model.Room;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class BookingResponse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookingId;
    private LocalDate checkInDate;

    private LocalDate checkOutDate;

    private String guestFullName;


    private String guestEmail;

    private int NumOfAdults;

    private int NumOfChildren;


    private int totalNumOfGuest;


    private String bookingConfirmationCode;


    private Room room;

    public BookingResponse(Long bookingId, LocalDate checkInDate, LocalDate checkOutDate, String bookingConfirmationCode) {
        this.bookingId = bookingId;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.bookingConfirmationCode = bookingConfirmationCode;
    }
}
