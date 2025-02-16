import java.time.LocalDate;
import java.time.Period;
import java.util.Scanner;

public class AgeCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter birth year: ");
        int year = scanner.nextInt();
        System.out.print("Enter birth month: ");
        int month = scanner.nextInt();
        System.out.print("Enter birth day: ");
        int day = scanner.nextInt();
        
        LocalDate birthDate = LocalDate.of(year, month, day);
        LocalDate currentDate = LocalDate.now();
        
        Period age = Period.between(birthDate, currentDate);
        System.out.println("You are " + age.getYears() + " years, " + age.getMonths() + " months, and " + age.getDays() + " days old.");
        
        // Upcoming Birthday Countdown
        LocalDate nextBirthday = birthDate.withYear(currentDate.getYear());
        if (nextBirthday.isBefore(currentDate) || nextBirthday.equals(currentDate)) {
            nextBirthday = nextBirthday.plusYears(1);
        }
        Period birthdayCountdown = Period.between(currentDate, nextBirthday);
        System.out.println("Your next birthday is in " + birthdayCountdown.getMonths() + " months and " + birthdayCountdown.getDays() + " days.");
        
        // Zodiac Sign Calculation
        String zodiacSign = getZodiacSign(month, day);
        System.out.println("Your Zodiac Sign is: " + zodiacSign);
        
        // Leap Year Check
        boolean isLeapYear = (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
        System.out.println("Your birth year " + year + " was " + (isLeapYear ? "a leap year." : "not a leap year."));
        
        scanner.close();
    }
    
    private static String getZodiacSign(int month, int day) {
        String sign = "";
        if ((month == 1 && day >= 20) || (month == 2 && day <= 18)) sign = "Aquarius";
        else if ((month == 2 && day >= 19) || (month == 3 && day <= 20)) sign = "Pisces";
        else if ((month == 3 && day >= 21) || (month == 4 && day <= 19)) sign = "Aries";
        else if ((month == 4 && day >= 20) || (month == 5 && day <= 20)) sign = "Taurus";
        else if ((month == 5 && day >= 21) || (month == 6 && day <= 20)) sign = "Gemini";
        else if ((month == 6 && day >= 21) || (month == 7 && day <= 22)) sign = "Cancer";
        else if ((month == 7 && day >= 23) || (month == 8 && day <= 22)) sign = "Leo";
        else if ((month == 8 && day >= 23) || (month == 9 && day <= 22)) sign = "Virgo";
        else if ((month == 9 && day >= 23) || (month == 10 && day <= 22)) sign = "Libra";
        else if ((month == 10 && day >= 23) || (month == 11 && day <= 21)) sign = "Scorpio";
        else if ((month == 11 && day >= 22) || (month == 12 && day <= 21)) sign = "Sagittarius";
        else if ((month == 12 && day >= 22) || (month == 1 && day <= 19)) sign = "Capricorn";
        return sign;
    }
}
