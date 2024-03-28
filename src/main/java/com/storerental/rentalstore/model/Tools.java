package com.storerental.rentalstore.model;

import java.math.BigDecimal;
/**
 * Represents a tool available for rental.
 */
public class Tools {
    private String code;
    private String type;
    private String brand;
    private BigDecimal dailyCharge;
    private boolean weekdayCharge;
    private boolean weekendCharge;
    private boolean holidayCharge;
    /**
     * Default constructor for Tool class.
     */
    public Tools() {
    }

    /**
     * Parameterized constructor for Tool class.
     *
     * @param code          the code of the tool
     * @param type          the type of the tool
     * @param brand         the brand of the tool
     * @param dailyCharge   the daily rental charge of the tool
     * @param weekdayCharge indicates if weekday rental is allowed for the tool
     * @param weekendCharge indicates if weekend rental is allowed for the tool
     * @param holidayCharge indicates if holiday rental is allowed for the tool
     */
    public Tools(String code, String type, String brand, BigDecimal dailyCharge, boolean weekdayCharge, boolean weekendCharge, boolean holidayCharge) {
        this.code = code;
        this.type = type;
        this.brand = brand;
        this.dailyCharge = dailyCharge;
        this.weekdayCharge = weekdayCharge;
        this.weekendCharge = weekendCharge;
        this.holidayCharge = holidayCharge;
    }
    // Getters and setters


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public BigDecimal getDailyCharge() {
        return dailyCharge;
    }

    public void setDailyCharge(BigDecimal dailyCharge) {
        this.dailyCharge = dailyCharge;
    }

    public boolean isWeekdayCharge() {
        return weekdayCharge;
    }

    public void setWeekdayCharge(boolean weekdayCharge) {
        this.weekdayCharge = weekdayCharge;
    }

    public boolean isWeekendCharge() {
        return weekendCharge;
    }

    public void setWeekendCharge(boolean weekendCharge) {
        this.weekendCharge = weekendCharge;
    }

    public boolean isHolidayCharge() {
        return holidayCharge;
    }

    public void setHolidayCharge(boolean holidayCharge) {
        this.holidayCharge = holidayCharge;
    }
}