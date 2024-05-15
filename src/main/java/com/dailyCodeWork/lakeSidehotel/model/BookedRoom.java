package com.dailyCodeWork.lakeSidehotel.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookedRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookingId;
    @Column(name = "check_In")
    private LocalDate checkInDate;
    @Column(name = "check_Out")

    private LocalDate checkOutDate;
    @Column(name = "Guest_FullName")

    private String guestFullName;
    @Column(name = "guest_Email")


    private String guestEmail;
    @Column(name = "adults")

    private int NumOfAdults;
    @Column(name = "children")

    private int NumOfChildren;
    @Column(name = "total_guest")


    private int totalNumOfGuest;
    @Column(name = "confirmation_code")


    private String bookingConfirmationCode;
    @JoinColumn(name = "room_id")

    @ManyToOne(fetch = FetchType.LAZY) //one room can be booked by many people

    private Room room;


    public void calculateTotalNumOfQuest(){
        this.totalNumOfGuest = this.NumOfAdults + this.NumOfChildren;
    }

    public void setNumOfAdults(int numOfAdults) {
        NumOfAdults = numOfAdults;
        calculateTotalNumOfQuest();
    }

    public void setNumOfChildren(int numOfChildren) {
        NumOfChildren = numOfChildren;
        calculateTotalNumOfQuest();
    }

    public void setBookingConfirmationCode(String bookingConfirmationCode) {
        this.bookingConfirmationCode = bookingConfirmationCode;
    }
}


