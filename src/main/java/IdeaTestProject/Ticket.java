package IdeaTestProject;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Ticket {
    String origin;
    String origin_name;
    String destination;
    String destination_name;
    LocalDate departure_date;
    LocalTime departure_time;
    LocalDate arrival_date;
    LocalTime arrival_time;
    String carrier;
    int stops;
    int price;

    public Duration getFlightDuration(){
        LocalDateTime arrival = arrival_date.atTime(arrival_time);
        LocalDateTime departure = departure_date.atTime(departure_time);
        return Duration.between(arrival, departure).abs();
    }
}
