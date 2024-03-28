package com.storerental.rentalstore.services;

import com.storerental.rentalstore.model.RentalAgreement;

import java.time.LocalDate;
/**
 * Service interface for tool rental operations.
 */
public interface AgreementGeneratorService {
    /**
     * Checks out a tool for rental based on the provided parameters.
     *
     * @param toolCode      The code of the tool to be rented.
     * @param rentalDays    The number of rental days.
     * @param discountPercent   The discount percentage applied to the rental.
     * @param checkoutDate  The date when the tool is checked out.
     * @return The rental agreement containing details of the rental transaction.
     */
    RentalAgreement checkoutTool(String toolCode, int rentalDays, int discountPercent, LocalDate checkoutDate);
}
