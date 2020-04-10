package htp.utils;


import htp.domain.model.Applicant;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.util.Set;

@Slf4j
public class Functions {
    public static final Integer SECONDS = 1000;
    public static final Integer MINUTES = 60;
    public static final Integer HOURS = 60;
    public static final Integer DAYS = 24;
    public static final Double MONTHS = 30.4375;
    public static final Double ONE_DOUBLE = 1D;
    public static final int ZERO_INT = 0;
    public static final double ZERO_DOUBLE = 0D;
    public static final Integer IN_YEAR = 1200;
    public static final Integer FIRST_ITEM = 0;
    public static final Integer SECOND_ITEM = 1;
    public static final int TWO_DIGITS_AFTER_COMMA = 2;
    public static final int MAIN_APPLICANT = 1;


    public static Integer positiveOrZeroInt (Integer number){
        return number < 0 ? 0 : number;
    }

    public static Integer calculateAge(Timestamp birthday){
        long now = new Timestamp(System.currentTimeMillis()).getTime();
        long dob = birthday.getTime();
        Double nowDouble= (double) now;
        Double dobDouble = (double) dob;
        return Double.valueOf(Math.floor((nowDouble-dobDouble)/SECONDS/MINUTES/HOURS/DAYS/MONTHS)).intValue();
    }

    public static Integer calculateMonths (Integer minMonth, Integer maxMonth){
        return maxMonth - minMonth;
    }


    public static Double calculateMaxAmount(Double income, Double expenses, Integer term, Double interestRate, Double coefficient){
        return Math.floor((income*coefficient-expenses)/(ONE_DOUBLE/term+interestRate/IN_YEAR));
    }

    public static Double calculatePayment(Double amount, Integer term, Double interestRate){
        if (positiveOrZeroInt(term)==ZERO_INT){
            return ZERO_DOUBLE;
        }
        BigDecimal payment = new BigDecimal(Double.toString(amount/term + interestRate*amount/IN_YEAR));
        payment = payment.setScale(TWO_DIGITS_AFTER_COMMA, RoundingMode.HALF_EVEN);
        return payment.doubleValue();
    }

    public static Integer getTypeFromCode(Integer productCode){
        String codeString = productCode.toString();
        String firstDigit = codeString.substring(FIRST_ITEM, SECOND_ITEM);
        return Integer.parseInt(firstDigit);
    }

    public static String findMainApplicantPersonalNumber(Set<Applicant> applicants){
        for (Applicant applicant : applicants){
            if (applicant.getTypeOfApplicant().equals(MAIN_APPLICANT)){
                return applicant.getPersonalNumber();
            }
        }
        return null;
    }
}
