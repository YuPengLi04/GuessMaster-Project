// student number: 20347665
// name: Yu Peng Li
package com.example.guessmaster3;
import java.util.Scanner;

public class Date{
    private String month;
    private int day;
    private int year; //a four digit number.

    public Date(){//this parentheses can be put in the above line.
        month = "January";
        day = 1;
        year = 1000;
    }

    // Overloading the constructor to allow string inputs
    public Date(String strDate) {

        // Splitting the input at "slash" and saved into the array for example, 04/30/1700 => [04], [30], [1700]
        String[] parts = strDate.split("/");

        // If the length of the array does not reach 3 "items" it will ask the user to enter the answer again
        if (parts.length != 3) {
            throw new IllegalArgumentException("Invalid date format. Please use mm/dd/yyyy.");
        }

        // If the first item's length at most 2, it will parse the input as an integer
        if(parts[0].length() <= 2) {
            int numMonth = Integer.parseInt(parts[0]);
            // Uses the monthString function to convert the integer month into word month in all lower case using .toLowerCase for example, 04 => april
            month = monthString(numMonth).toLowerCase();
            System.out.println(this.month);

            // Otherwise just save the month as string since all the word version of the months are longer than 2 characters
        }else {
            month = parts[0];
        }

        // Parses the string into integer for day and year
        day = Integer.parseInt(parts[1]);
        year = Integer.parseInt(parts[2]);

        // Assume setDate() method exists and sets the date while handling validations.
        setDate(month, day, year);
    }

    public Date(String monthString, int day, int year)
    {
        setDate(monthString, day, year);
    }

    public Date(int monthInt, int day, int year)
    {
        setDate(monthInt, day, year);
    }

    public Date(int year)
    {
        setDate(1, 1, year);
    }


    //copy constructor
    public Date(Date aDate)
    {
        if (aDate == null)//Not a real date.
        {
            System.out.println("Invalid date.");
        }

        month = aDate.month;
        day = aDate.day;
        year = aDate.year;
    }

    public void setDate(int monthInt, int day, int year)
    {

        if (dateOK(monthInt, day, year))
        {
            this.month = monthString(monthInt);
            this.day = day;
            this.year = year;
        }
        else
        {
            System.out.println("Set Date 1 failed");
        }
    }

    public void setDate(String monthString, int day, int year)
    {

        if (dateOK(monthString, day, year))
        {
            this.month = monthString.toLowerCase();
            this.day = day;
            this.year = year;
        }
        else
        {
            System.out.println("Set Date 2 failed");
        }
    }

    public void setDate(int year)
    {
        setDate(1, 1, year);
    }

    public void setYear(int year)
    {
        if ( (year < 1000) || (year > 9999) )
        {
            System.out.println("Invalid year.");
        }
        else
            this.year = year;
    }
    public void setMonth(int monthNumber)
    {
        if ((monthNumber <= 0) || (monthNumber > 12))
        {
            System.out.println("Invalid month.");
        }
        else
            month = monthString(monthNumber);
    }

    public void setDay(int day)
    {
        if ((day <= 0) || (day > 31))
        {
            System.out.println("Invalid day.");
        }
        else
            this.day = day;
    }

    public int getMonth( )
    {
        if (month.equalsIgnoreCase("January"))
            return 1;
        else if (month.equalsIgnoreCase("February"))
            return 2;
        else if (month.equalsIgnoreCase("March"))
            return 3;
        else if (month.equalsIgnoreCase("April"))
            return 4;
        else if (month.equalsIgnoreCase("May"))
            return 5;
        else if (month.equalsIgnoreCase("June"))
            return 6;
        else if (month.equalsIgnoreCase("July"))
            return 7;
        else if (month.equalsIgnoreCase("August"))
            return 8;
        else if (month.equalsIgnoreCase("September"))
            return 9;
        else if (month.equalsIgnoreCase("October"))
            return 10;
        else if (month.equalsIgnoreCase("November"))
            return 11;
        else if (month.equalsIgnoreCase("December"))
            return 12;
        else
        {
            System.out.println("get month error.");
            return 0; //Needed to keep the compiler happy
        }
    }

    public int getDay( )
    {
        return day;
    }

    public int getYear( )
    {
        return year;
    }

    public String toString( )
    {
        return (month + " " + day + ", " + year);
        //value will be shown when debugging
        //a very useful feature for dubugging
        //also useful in println(), which
        //automatically call toString();
    }

    public boolean equals(Date otherDate)
    {
        return ((month.equals(otherDate.month)
                && (day == otherDate.day) && (year == otherDate.year)));
    }

    public boolean precedes(Date otherDate)
    {
        return ( (year < otherDate.year) ||
                (year == otherDate.year && getMonth( ) < otherDate.getMonth( )) ||
                (year == otherDate.year && month.equals(otherDate.month)
                        && day < otherDate.day) );
    }

    public void readInput( )
    {
        boolean tryAgain = true;
        Scanner keyboard = new Scanner(System.in);
        while (tryAgain)
        {
            System.out.println("Enter month, day, and year.");
            System.out.println("Do not use a comma.");
            String monthInput = keyboard.next( );
            int dayInput = keyboard.nextInt( );
            int yearInput = keyboard.nextInt( );
            if (dateOK(monthInput, dayInput, yearInput) )
            {
                setDate(monthInput, dayInput, yearInput);
                tryAgain = false;
            }
            else
                System.out.println("Illegal date. Reenter input.");
        }
    }

    private boolean dateOK(int monthInt, int dayInt, int yearInt)
    {
        return ( (monthInt >= 1) && (monthInt <= 12) &&
                (dayInt >= 1) && (dayInt <= 31) &&
                (yearInt >= 1000) && (yearInt <= 9999) );
    }

    private boolean dateOK(String monthString, int dayInt, int yearInt)
    {

        return ( monthOK(monthString) &&
                (dayInt >= 1) && (dayInt <= 31) &&
                (yearInt >= 1000) && (yearInt <= 9999) );
    }


    //changed the month to all lower case
    private boolean monthOK(String month)
    {
        return (month.toLowerCase().equals("january") || month.toLowerCase().equals("february") ||
                month.toLowerCase().equals("march") || month.toLowerCase().equals("april") ||
                month.toLowerCase().equals("may") || month.toLowerCase().equals("june") ||
                month.toLowerCase().equals("july") || month.toLowerCase().equals("august") ||
                month.toLowerCase().equals("september") || month.toLowerCase().equals("october") ||
                month.toLowerCase().equals("november") || month.toLowerCase().equals("december") );
    }

    private String monthString(int monthNumber){
        switch (monthNumber){
            case 1:
                return "January";
            case 2:
                return "February";
            case 3:
                return "March";
            case 4:
                return "April";
            case 5:
                return "May";
            case 6:
                return "June";
            case 7:
                return "July";
            case 8:
                return "August";
            case 9:
                return "September";
            case 10:
                return "October";
            case 11:
                return "November";
            case 12:
                return "December";
            default:
                System.out.println("Invalid month conversion");
                return "Error"; //to keep the compiler happy
        }
    }
}


