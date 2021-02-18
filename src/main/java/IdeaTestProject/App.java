package IdeaTestProject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class App 
{
    public static void main( String[] args )
    {
        if (args.length < 1) {
            System.out.println("Имя файла .json должно быть передано в качестве параметра!");
            return;
        }
        try {
            BufferedReader br = new BufferedReader(new FileReader(args[0]));
            String jsonString = br.lines().reduce("", String::concat);
            br.close();
            Gson gson = new GsonBuilder()
                                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                                .registerTypeAdapter(LocalTime.class, new LocalTimeAdapter())
                                .create();
            TicketArray ticketArray = gson.fromJson(jsonString, TicketArray.class);
            
            ticketArray.tickets.removeIf(ticket -> !ticket.origin.equals("VVO") || !ticket.destination.equals("TLV") );
            
            Duration avgDuration = Duration.ZERO;
            long ticketCount = 0;
            for(Ticket t : ticketArray.tickets){
                avgDuration = avgDuration.plus(t.getFlightDuration());
                ticketCount++;
            }
            avgDuration = avgDuration.dividedBy(ticketCount);
            System.out.println("Среднее время полета: " + 
                    avgDuration.toMinutes() + " минут (" + 
                    avgDuration.toHoursPart() + " часов и " +
                    avgDuration.toMinutesPart() + " минут)");
            
            ticketArray.tickets.sort(new TicketComparator());
            // -1 ибо индексы начинаются с 0
            Duration percentile90 = ticketArray.tickets.get(
                (int)Math.ceil(ticketCount * 0.9) - 1).getFlightDuration();
            System.out.println("90-й процентиль времени полета: " + 
                    percentile90.toMinutes() + " минут (" + 
                    percentile90.toHoursPart() + " часов и " +
                    percentile90.toMinutesPart() + " минут)");
        } 
        catch(IOException ioExc) {
            System.out.println("Не удается найти файл с таким именем!");
        } 
        catch(Exception exc) {
            System.out.println("Ошибка:" + exc.getLocalizedMessage());
        }
    }
}
