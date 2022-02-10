package TestMain;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class TestCalendar {
    public static void main(String[] args) {
//        Calendar cal = new GregorianCalendar();
//        int month = cal.get(Calendar.MONTH);
//        cal.set(Calendar.MONTH,month);
//        cal.set(Calendar.MONTH,month);
//        cal.set(Calendar.HOUR_OF_DAY, 0);
//        cal.set(Calendar.SECOND, 0);
//        cal.set(Calendar.MINUTE, 0);
//        Date startDay =cal.getTime();
//        System.out.println( startDay.toString());
//        Date now =new Date();
//        System.out.println(now.toString());


//        Calendar cal = new GregorianCalendar();
//        int month = cal.get(Calendar.MONTH);
//        int day = cal.get(Calendar.DAY_OF_MONTH);
//        cal.set(Calendar.MONTH,month);
//        cal.set(Calendar.DAY_OF_MONTH,day);
//        cal.set(Calendar.HOUR_OF_DAY, 0);
//        cal.set(Calendar.SECOND, 0);
//        cal.set(Calendar.MINUTE, 0);
//        Date startDay =cal.getTime();
//        System.out.println(startDay);

          String startdate ="2021/12/15 11:00 - 2022/01/07 23:50";
          String[] parts = startdate.split("-");
          System.out.println("parts1:"+parts[0]);
          System.out.println("parts2:"+parts[1].trim());


          String timeStr = "23:59:59";
          Date date =new Date();
         SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
         SimpleDateFormat dateFormat =new SimpleDateFormat("yyyy/MM/dd HH:mm") ;
        try {
            Date resultTime = timeFormat.parse(timeStr);
            Date resultTime2 = dateFormat.parse(parts[0]);
            Date resultTime3 = dateFormat.parse(parts[1]);
            System.out.println("resultTime2:"+resultTime2);
            System.out.println("resultTime3:"+resultTime3);

        }catch (ParseException e){
            e.getMessage();
        }
    }

}
