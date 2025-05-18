package parking_lot;

import java.time.LocalDateTime;


public class Ticket
{
    String ticketId;

    Vehicle vehicle;

    LocalDateTime entryTime;

    ParkingSpot spot;


    public Ticket( String ticketId,
                   Vehicle vehicle,
                   LocalDateTime entryTime,
                   ParkingSpot spot )
    {
        this.ticketId = ticketId;
        this.vehicle = vehicle;
        this.entryTime = entryTime;
        this.spot = spot;
    }
}
