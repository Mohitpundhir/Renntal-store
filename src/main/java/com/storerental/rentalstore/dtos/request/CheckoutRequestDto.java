package com.storerental.rentalstore.dtos.request;

public class CheckoutRequestDto {
    private String toolCode;
    private String checkoutDate;
    private  int rentalDaysCount;
    private int discountPercentage;

    public CheckoutRequestDto() {
    }

    public CheckoutRequestDto(String toolCode, String checkoutDate, int rentalDaysCount, int discountPercentage) {
        this.toolCode = toolCode;
        this.checkoutDate = checkoutDate;
        this.rentalDaysCount = rentalDaysCount;
        this.discountPercentage = discountPercentage;
    }

    public String getToolCode() {
        return toolCode;
    }

    public void setToolCode(String toolCode) {
        this.toolCode = toolCode;
    }

    public String getCheckoutDate() {
        return checkoutDate;
    }

    public void setCheckoutDate(String checkoutDate) {
        this.checkoutDate = checkoutDate;
    }

    public int getRentalDaysCount() {
        return rentalDaysCount;
    }

    public void setRentalDaysCount(int rentalDaysCount) {
        this.rentalDaysCount = rentalDaysCount;
    }

    public int getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(int discountPercentage) {
        this.discountPercentage = discountPercentage;
    }
}
