package wateringcan.com.br.wateringcan.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by Bruno on 03/11/2017.
 */

public class DateUtil {

    public static String getHours(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        return format.format(date);
    }

    public static String getDayOfWeek(Date date) {

        long days = getPeriodOfDays(date);
        if (days > 7) {
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            return format.format(date);
        }
//        else if (days == 0) {
//            return "Hoje";
//        } else if (days == 1) {
//            return "Ontém";
//        }

        return dayOfWeek(date);
    }

    private static String dayOfWeek(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        switch (dayOfWeek) {
            case Calendar.MONDAY:
                return "Segunda-Feira";
            case Calendar.TUESDAY:
                return "Terça-Feira";
            case Calendar.WEDNESDAY:
                return "Quarta-Feira";
            case Calendar.THURSDAY:
                return "Quinta-Feita";
            case Calendar.FRIDAY:
                return "Sexta-Feira";
            case Calendar.SATURDAY:
                return "Sábado";
            case Calendar.SUNDAY:
                return "Domingo";
        }
        return "";
    }

    private static long getPeriodOfDays(Date date) {
        Calendar now = Calendar.getInstance();

        // Monta data final
        GregorianCalendar finalDate =
                new GregorianCalendar(now.get(Calendar.YEAR),
                        now.get(Calendar.MONTH), now.get(Calendar.DAY_OF_MONTH));

        // Monta data inicial
        GregorianCalendar initialDate = new GregorianCalendar();
        initialDate.setTimeInMillis(date.getTime());

        GregorianCalendar initialGregorianDate =
                new GregorianCalendar(initialDate.get(Calendar.YEAR),
                        initialDate.get(Calendar.MONTH), initialDate.get(Calendar.DAY_OF_MONTH));

        // Calcula a diferença entre a data final menos a inicial
        long difference = finalDate.getTimeInMillis() - initialGregorianDate.getTimeInMillis();

        // Converte a diferença de milesegundos para minutos
        long hours = difference / (60 * 60 * 1000);

        // Converte a diferença de minutos para dias
        long dias = hours / 24;

        return dias;
    }
}
