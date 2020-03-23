package htp.utils;

import htp.entities.db_entities.CreditInfo;

import java.sql.Timestamp;
import java.util.List;

public class Functions {
    public static final Integer SECONDS = 1000;
    public static final Integer MINUTES = 60;
    public static final Integer HOURS = 60;
    public static final Integer DAYS = 24;
    public static final Double MONTHS = 30.4375;
    public static final Integer ONE = 1;
    public static final Integer IN_YEAR = 1200;


    public static Integer positiveOrZeroInt (Integer number){
        return number < 0 ? 0 : number;
    }

    public static Integer calculateAge(Timestamp birthday){
        Long now = new Timestamp(System.currentTimeMillis()).getTime();
        Long dob = birthday.getTime();
        return Double.valueOf(Math.floor((now-dob)/SECONDS/MINUTES/HOURS/DAYS/MONTHS)).intValue();
    }

    public static Integer calculateMonths (Integer minMonth, Integer maxMonth){
        return maxMonth - minMonth;
    }

    public static Double calculateExternalPayments (List<CreditInfo> creditInfos){
        Double payments = 0D;
        for (CreditInfo creditInfo : creditInfos){
            payments += creditInfo.getPayment();
        }
        return payments;
    }

    public static Double calculateMaxAmount(Double income, Double expenses, Integer term, Double interestRate, Double coefficient){
        return (income*coefficient-expenses)/(ONE/term+interestRate/IN_YEAR);
    }
}
