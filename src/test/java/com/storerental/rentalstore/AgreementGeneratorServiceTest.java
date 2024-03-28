package com.storerental.rentalstore;

import com.storerental.rentalstore.exceptions.ParameterValidationException;
import com.storerental.rentalstore.model.RentalAgreement;
import com.storerental.rentalstore.services.AgreementGeneratorService;
import com.storerental.rentalstore.services.AgreementGeneratorServiceImpl;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import static org.junit.Assert.*;

/**
 * Unit tests for the ToolRentalService.
 */
public class AgreementGeneratorServiceTest {

    private AgreementGeneratorService toolAgreementGeneratorService;

    /**
     * Initializes the toolRentalService with a new instance
     * of RentalServiceImpl before each test.
     */
    @Before
    public void setUp() {
        this.toolAgreementGeneratorService = new AgreementGeneratorServiceImpl();
    }

    /**
     * Tests the checkoutTool method with valid parameters.
     */
    @Test
    public void testToolRentalWithValidParameters() {
        RentalAgreement rentalAgreement = toolAgreementGeneratorService.checkoutTool("JAKR", 5, 0, LocalDate.of(2015, 9, 3));
        assertEquals("JAKR", rentalAgreement.getTool().getCode());
        assertEquals(5, rentalAgreement.getRentalDaysCount());
    }

    /**
     * Tests the checkoutTool method with invalid rental days.
     */
    @Test
    public void testToolRentalWithInvalidRentalDays() {
        assertThrows(ParameterValidationException.class, () ->
                toolAgreementGeneratorService.checkoutTool("LADW", 0, 10, LocalDate.of(2020, 7, 2)));
    }

    /**
     * Tests the checkoutTool method with invalid discount percentage.
     */
    @Test
    public void testToolRentalWithInvalidDiscount() {
        assertThrows(ParameterValidationException.class, () ->
                toolAgreementGeneratorService.checkoutTool("CHNS", 5, 101, LocalDate.of(2015, 7, 2)));
    }

    /**
     * Tests the checkoutTool method with valid parameters for various scenarios.
     */
    @Test
    public void testToolRentalWithValidParametersForAllScenarios() {
        RentalAgreement rentalAgreement4 = toolAgreementGeneratorService.checkoutTool("JAKD", 6, 0, LocalDate.of(2015, 9, 3));
        assertNotNull(rentalAgreement4);
        assertEquals("JAKD", rentalAgreement4.getTool().getCode());
        assertEquals(6, rentalAgreement4.getRentalDaysCount());

        RentalAgreement rentalAgreement5 = toolAgreementGeneratorService.checkoutTool("JAKR", 9, 0, LocalDate.of(2015, 7, 2));
        assertNotNull(rentalAgreement5);
        assertEquals("JAKR", rentalAgreement5.getTool().getCode());
        assertEquals(9, rentalAgreement5.getRentalDaysCount());

        RentalAgreement rentalAgreement6 = toolAgreementGeneratorService.checkoutTool("JAKR", 4, 50, LocalDate.of(2020, 7, 2));
        assertNotNull(rentalAgreement6);
        assertEquals("JAKR", rentalAgreement6.getTool().getCode());
        assertEquals(4, rentalAgreement6.getRentalDaysCount());
        assertEquals(50, rentalAgreement6.getDiscountPercent());
    }
}
