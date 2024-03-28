package com.storerental.rentalstore.dtos.response;

import com.storerental.rentalstore.model.RentalAgreement;

public class CheckoutResponseDto {
    private RentalAgreement rentalAgreement;

    public CheckoutResponseDto() {
    }

    public CheckoutResponseDto(RentalAgreement rentalAgreement) {
        this.rentalAgreement = rentalAgreement;
    }

    public RentalAgreement getRentalAgreement() {
        return rentalAgreement;
    }

    public void setRentalAgreement(RentalAgreement rentalAgreement) {
        this.rentalAgreement = rentalAgreement;
    }
}
