package com.storerental.rentalstore.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class RentalAgreement {

        private Tools tools;
        private int rentalDaysCount;
        private LocalDate checkoutDate;
        private LocalDate dueDate;
        private BigDecimal dailyRentalCharge;
        private int chargeDays;
        private BigDecimal preDiscountCharge;
        private int discountPercent;
        private BigDecimal discountAmount;
        private BigDecimal finalCharge;
        /**
         * Default constructor for RentalAgreement class.
         */

        public RentalAgreement() {
        }
        /**
         * Parameterized constructor for RentalAgreement class.
         *
         * @param tools             the rented tool
         * @param rentalDaysCount        the number of rental days
         * @param checkoutDate      the checkout date
         * @param dueDate           the due date
         * @param dailyRentalCharge the daily rental charge
         * @param chargeDays        the number of chargeable days
         * @param preDiscountCharge the pre-discount charge
         * @param discountPercent   the discount percentage
         * @param discountAmount    the discount amount
         * @param finalCharge       the final charge after discount
         * @param brandCharge       the brand charge
         */
        public RentalAgreement(Tools tools, int rentalDaysCount, LocalDate checkoutDate, LocalDate dueDate, BigDecimal dailyRentalCharge, int chargeDays, BigDecimal preDiscountCharge, int discountPercent, BigDecimal discountAmount, BigDecimal finalCharge, BigDecimal brandCharge) {
            this.tools = tools;
            this.rentalDaysCount = rentalDaysCount;
            this.checkoutDate = checkoutDate;
            this.dueDate = dueDate;
            this.dailyRentalCharge = dailyRentalCharge;
            this.chargeDays = chargeDays;
            this.preDiscountCharge = preDiscountCharge;
            this.discountPercent = discountPercent;
            this.discountAmount = discountAmount;
            this.finalCharge = finalCharge;
        }
        @JsonIgnore
        public Tools getTool() {
            return tools;
        }
        @JsonProperty("toolCode")
        public String getToolCode(){
            return tools.getCode();
        }
        @JsonProperty("toolType")
        public String getToolType(){
            return tools.getType();
        }
        @JsonProperty("toolBrand")
        public String getToolBrand(){
            return tools.getBrand();
        }


        public void setTool(Tools tools) {
            this.tools = tools;
        }

        public int getRentalDaysCount() {
            return rentalDaysCount;
        }

        public void setRentalDays(int rentalDaysCount) {
            this.rentalDaysCount = rentalDaysCount;
        }

        public LocalDate getCheckoutDate() {
            return checkoutDate;
        }
        @JsonProperty("checkoutDate")
        public String getFormattedCheckoutDate() {
            return checkoutDate.format(DateTimeFormatter.ofPattern("MM/dd/yy"));
        }

        @JsonProperty("dueDate")
        public String getFormattedDueDate() {
            return dueDate.format(DateTimeFormatter.ofPattern("MM/dd/yy"));
        }

        public void setCheckoutDate(LocalDate checkoutDate) {
            this.checkoutDate = checkoutDate;
        }

        public LocalDate getDueDate() {
            return dueDate;
        }

        public void setDueDate(LocalDate dueDate) {
            this.dueDate = dueDate;
        }

        public BigDecimal getDailyRentalCharge() {
            return dailyRentalCharge;
        }

        public void setDailyRentalCharge(BigDecimal dailyRentalCharge) {
            this.dailyRentalCharge = dailyRentalCharge;
        }

        public int getChargeDays() {
            return chargeDays;
        }

        public void setChargeDays(int chargeDays) {
            this.chargeDays = chargeDays;
        }

        public BigDecimal getPreDiscountCharge() {
            return preDiscountCharge;
        }

        public void setPreDiscountCharge(BigDecimal preDiscountCharge) {
            this.preDiscountCharge = preDiscountCharge;
        }

        public int getDiscountPercent() {
            return discountPercent;
        }

        public void setDiscountPercent(int discountPercent) {
            this.discountPercent = discountPercent;
        }

        public BigDecimal getDiscountAmount() {
            return discountAmount;
        }

        public void setDiscountAmount(BigDecimal discountAmount) {
            this.discountAmount = discountAmount;
        }

        public BigDecimal getFinalCharge() {
            return finalCharge;
        }

        public void setFinalCharge(BigDecimal finalCharge) {
            this.finalCharge = finalCharge;
        }



        // Function to print the details of Rental Agreement
        public void printDetails() {
            DecimalFormat currencyFormat = new DecimalFormat("$#,##0.00");
            DecimalFormat percentFormat = new DecimalFormat("##0%");
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MM/dd/yy");
            System.out.println("Tool code: " + tools.getCode());
            System.out.println("Tool type: " + tools.getType());
            System.out.println("Brand: " + tools.getBrand());
            System.out.println("Rental days: " + rentalDaysCount);
            System.out.println("Checkout date: " + checkoutDate.format(dateFormatter));
            System.out.println("Due date: " + dueDate.format(dateFormatter));
            System.out.println("Daily rental charge: " + currencyFormat.format(dailyRentalCharge));
            System.out.println("Charge days: " + chargeDays);
            System.out.println("Pre-discount charge: " + currencyFormat.format(preDiscountCharge));
            System.out.println("Discount percent: " + percentFormat.format((double) discountPercent / 100));
            System.out.println("Discount amount: " + currencyFormat.format(discountAmount));
            System.out.println("Final charge: " + currencyFormat.format(finalCharge));
        }
    }