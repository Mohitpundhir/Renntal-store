package com.storerental.rentalstore.controllers;
import com.storerental.rentalstore.dtos.request.CheckoutRequestDto;
import com.storerental.rentalstore.dtos.response.CheckoutResponseDto;
import com.storerental.rentalstore.exceptions.ParameterValidationException;
import com.storerental.rentalstore.model.RentalAgreement;
import com.storerental.rentalstore.services.AgreementGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Controller class for handling tool rental operations.
 */
@RestController
public class CheckoutController {
    @Autowired
    private AgreementGeneratorService toolAgreementGeneratorService;


@PostMapping("/checkout")
    public ResponseEntity<CheckoutResponseDto>checkoutTool(@RequestBody CheckoutRequestDto checkoutRequestDto)
    {
        if (checkoutRequestDto.getRentalDaysCount() < 1) {
            throw new ParameterValidationException("Rental days count must be 1 or greater");
        }

        if (checkoutRequestDto.getDiscountPercentage() < 0 || checkoutRequestDto.getDiscountPercentage() > 100) {
            throw new ParameterValidationException("Discount percent must be in the range 0-100");
        }
        String checkoutDateString=checkoutRequestDto.getCheckoutDate();
        DateTimeFormatter dateTimeFormatter=DateTimeFormatter.ofPattern("MM/dd/yy");
        LocalDate checkoutDate=LocalDate.parse(checkoutDateString,dateTimeFormatter);

        RentalAgreement rentalAgreement= toolAgreementGeneratorService.checkoutTool(
                checkoutRequestDto.getToolCode(),
                checkoutRequestDto.getRentalDaysCount(),
                checkoutRequestDto.getDiscountPercentage(),
                checkoutDate
        );
        rentalAgreement.printDetails();
        CheckoutResponseDto checkoutResponseDto=new CheckoutResponseDto();
        checkoutResponseDto.setRentalAgreement(rentalAgreement);
        return ResponseEntity.ok(checkoutResponseDto);
    }

}
