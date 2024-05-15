package com.dailyCodeWork.lakeSidehotel.controller;

import com.dailyCodeWork.lakeSidehotel.exception.InvalidBookingRequestException;
import com.dailyCodeWork.lakeSidehotel.exception.ResourceNotFoundException;
import com.dailyCodeWork.lakeSidehotel.model.BookedRoom;
import com.dailyCodeWork.lakeSidehotel.response.BookingResponse;
import com.dailyCodeWork.lakeSidehotel.service.IBookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
@RequiredArgsConstructor
@RestController
@RequestMapping("/bookings")
public class BookingController {
    private final IBookingService bookingService;
    @GetMapping("all-bookings")
    public ResponseEntity<List<BookingResponse>> getAllBookings() {
        List<BookedRoom> bookings = bookingService.getAllBookings();
        List<BookingResponse> bookingResponses = new ArrayList<>();
        for (BookedRoom booking : bookings) {
            BookingResponse bookingResponse = getBookinResponse(booking);
            bookingResponses.add(bookingResponse);

        }
        return ResponseEntity.ok(bookingResponses);

    }
    @GetMapping("/confirmation/{confirmationCode}")
    public ResponseEntity<?> getBookingByConfirmationCode(@PathVariable String confirmationCode){
        try{
            BookedRoom booking = bookingService.findByBookingConfirmationCode(confirmationCode);
            BookingResponse bookingResponse = getBookingResponse(booking);
            return ResponseEntity.ok(bookingResponse);

        } catch (ResourceNotFoundException ex){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());

        }
    }



    @PostMapping("/room/{roomId}/booking")
    public ResponseEntity<?> saveBooking(@PathVariable Long roomId,@RequestBody BookedRoom bookingRequest){
        try{
            String confirmationCode = bookingService.saveBooking(roomId,bookingRequest);

            return ResponseEntity.ok("Room booked successfully ! Your booking confirmation code is :" + confirmationCode);


        }
        catch (InvalidBookingRequestException e){
            return ResponseEntity.badRequest().body(e.getMessage());

        }
    }
    @DeleteMapping("/booking/{bookingId}/delete")
    public void cancelBooking(@PathVariable Long bookingId){
        bookingService.cancelBooking(bookingId);
    }


    private BookingResponse getBookingResponse(BookedRoom booking) {
        return null;
    }
}
