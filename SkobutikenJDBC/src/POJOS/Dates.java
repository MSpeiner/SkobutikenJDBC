package POJOS;

import java.time.LocalDateTime;

public class Dates {

    public Dates() {
    }

    public int setYears(){
        LocalDateTime todayDates = LocalDateTime.now();
        int year = todayDates.getYear();
        return year;
    }

    public String setMonths(){
        LocalDateTime todayDates = LocalDateTime.now();
        int month = todayDates.getMonthValue();
        return switch (month) {
            case 1 -> "January";
            case 2 -> "February";
            case 3 -> "March";
            case 4 -> "April";
            case 5 -> "May";
            case 6 -> "June";
            case 7 -> "July";
            case 8 -> "August";
            case 9 -> "September";
            case 10 -> "October";
            case 11 -> "November";
            case 12 -> "December";
            default -> throw new IllegalArgumentException("Invalid month: " + month);
        };
    }

    public int setDays(){
        LocalDateTime todayDates = LocalDateTime.now();
        int days = todayDates.getDayOfMonth();
        return days;
    }

}
