package IdeaTestProject;

import java.util.Comparator;

public class TicketComparator implements Comparator<Ticket> {

    @Override
    public int compare(Ticket arg0, Ticket arg1) {
        return arg0.getFlightDuration().compareTo(arg1.getFlightDuration());
    }
    
}
