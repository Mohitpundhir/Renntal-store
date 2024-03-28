package com.storerental.rentalstore.services;

import com.storerental.rentalstore.exceptions.ParameterValidationException;
import com.storerental.rentalstore.model.RentalAgreement;
import com.storerental.rentalstore.model.Tools;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;

@Service
public class AgreementGeneratorServiceImpl implements AgreementGeneratorService {

    private final Map<String, Tools> toolMap = new HashMap<>();
    public AgreementGeneratorServiceImpl() {
        initializeToolMap();
    }
    private void initializeToolMap() {
        toolMap.put("LADW", new Tools("LADW", "LADDER", "Werner", new BigDecimal("1.99"), true, true, false));
        toolMap.put("CHNS", new Tools("CHNS", "CHAINSAW", "Stihl", new BigDecimal("1.49"), true, false, true));
        toolMap.put("JAKD", new Tools("JAKD", "JACKHAMMER", "DeWalt", new BigDecimal("2.99"), true, false, false));
        toolMap.put("JAKR", new Tools("JAKR", "JACKHAMMER", "Ridgid", new BigDecimal("2.99"), true, false, false));
    }

    @Override
    public RentalAgreement checkoutTool(String toolCode, int rentalDays, int discountPercent, LocalDate checkoutDate) {
        Tools tool = toolMap.get(toolCode);
        validateTool(tool);
        validateRentalDays(rentalDays);
        validateDiscountPercent(discountPercent);
        BigDecimal brandCharge = calculateBrandCharge(tool, checkoutDate);
        LocalDate dueDate = checkoutDate.plusDays(rentalDays);
        BigDecimal dailyRentalCharge = tool.getDailyCharge();
        int chargeDays = calculateChargeDays(checkoutDate, dueDate, tool);
        BigDecimal preDiscountCharge = dailyRentalCharge.multiply(BigDecimal.valueOf(chargeDays));
        BigDecimal discountAmount = preDiscountCharge.multiply(BigDecimal.valueOf(discountPercent / 100.0));
        BigDecimal finalCharge = preDiscountCharge.subtract(discountAmount);

        return new RentalAgreement(tool, rentalDays, checkoutDate, dueDate, dailyRentalCharge, chargeDays,
                preDiscountCharge, discountPercent, discountAmount, finalCharge, brandCharge);
    }

    private void validateTool(Tools tool) {
        if (tool == null) {
            throw new ParameterValidationException("Tool is null");
        }
    }

    private void validateRentalDays(int rentalDays) {
        if (rentalDays < 1) {
            throw new ParameterValidationException("Rental day count must be 1 or greater");
        }
    }

    private void validateDiscountPercent(int discountPercent) {
        if (discountPercent < 0 || discountPercent > 100) {
            throw new ParameterValidationException("Discount percent must be in the range 0-100");
        }
    }

    private BigDecimal calculateBrandCharge(Tools tool, LocalDate checkoutDate) {
        boolean isWeekday = isWeekday(checkoutDate);
        boolean isWeekend = !isWeekday;
        boolean isHoliday = isHoliday(checkoutDate);

        if ((isWeekday && tool.isWeekdayCharge()) || (isWeekend && tool.isWeekendCharge()) || (isHoliday && tool.isHolidayCharge())) {
            return tool.getDailyCharge();
        }
        return BigDecimal.ZERO;
    }

    private boolean isWeekday(LocalDate date) {
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        return dayOfWeek != DayOfWeek.SATURDAY && dayOfWeek != DayOfWeek.SUNDAY;
    }

    private int calculateChargeDays(LocalDate checkoutDate, LocalDate dueDate, Tools tool) {
            // Get the total number of days between checkoutDate and dueDate, inclusive
            long daysDifference = ChronoUnit.DAYS.between(checkoutDate, dueDate) + 1;

            // Count the number of weekend days between checkoutDate and dueDate
            long weekendDays = checkoutDate.datesUntil(dueDate.plusDays(1)).filter(this::isWeekend).count();

            // Count the number of holiday days between checkoutDate and dueDate
            long holidayDays = checkoutDate.datesUntil(dueDate.plusDays(1)).filter(this::isHoliday).count();

            // Calculate the total chargeable days by subtracting weekends and holidays from the total days
            long chargeableDays = daysDifference - weekendDays - holidayDays;

            // Ensure chargeableDays is non-negative
            chargeableDays = Math.max(0, chargeableDays);

            // Convert the long value to int (as chargeableDays is expected to be an int)
            return Math.toIntExact(chargeableDays);
        }

    private boolean isChargeableDay(LocalDate date, Tools tool) {
        return !isHoliday(date) && isWeekday(date) || tool.isHolidayCharge();
    }

    private boolean isHoliday(LocalDate date) {
        int year=date.getYear();
        Month month=date.getMonth();
        int dayOfMonth=date.getDayOfMonth();
        DayOfWeek dayOfWeek=date.getDayOfWeek();

        LocalDate independenceDay= LocalDate.of(LocalDate.now().getYear(),Month.JULY,4);
        if(independenceDay.getDayOfWeek()==DayOfWeek.SATURDAY){
            independenceDay=independenceDay.minusDays(1);
        }
        else if(independenceDay.getDayOfWeek()==DayOfWeek.SUNDAY){
            independenceDay=independenceDay.plusDays(1);
        }
        if(date==independenceDay){
            return true;
        }
        if(month==Month.SEPTEMBER && dayOfWeek==DayOfWeek.MONDAY && (dayOfMonth>=1 && dayOfMonth<=7)){
            return true;
        }
        return false;
    }
    private boolean isWeekend(LocalDate date){
        return date.getDayOfWeek()==DayOfWeek.SATURDAY || date.getDayOfWeek()==DayOfWeek.SUNDAY;
    }



}
