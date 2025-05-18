package parking_lot;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;


public class ParkingLot
{
    private static ParkingLot instance;

    private final List<ParkingFloor> floors;

    private final ParkingStrategy strategy;

    private final ConcurrentMap<String, Ticket> activeTickets;


    private ParkingLot( List<ParkingFloor> floors,
                        ParkingStrategy strategy )
    {
        this.floors = floors;
        this.strategy = strategy;
        this.activeTickets = new ConcurrentHashMap<>();
    }


    public static synchronized ParkingLot getInstance( List<ParkingFloor> floors,
                                                       ParkingStrategy strategy )
    {
        if ( instance == null )
        {
            instance = new ParkingLot( floors, strategy );
        }
        return instance;
    }


    public synchronized Ticket assignSpot( Vehicle vehicle )
    {
        ParkingSpot spot = strategy.findSpot( floors, vehicle.type );
        if ( spot == null || !spot.assignVehicle( vehicle ) )
        {
            return null;
        }

        String ticketId = UUID.randomUUID().toString();
        Ticket ticket = new Ticket( ticketId, vehicle, LocalDateTime.now(), spot );
        activeTickets.put( ticketId, ticket );
        return ticket;
    }


    public synchronized Receipt releaseSpot( String ticketId )
    {
        Ticket ticket = activeTickets.remove( ticketId );
        if ( ticket == null )
        {
            return null;
        }

        if ( ticket.spot.releaseVehicle() )
        {
            LocalDateTime exitTime = LocalDateTime.now();
            double amount = calculateCharges( ticket.vehicle.type, ticket.entryTime, exitTime );
            return new Receipt( UUID.randomUUID().toString(), ticket, exitTime, amount );
        }
        return null;
    }


    private double calculateCharges( VehicleType type,
                                     LocalDateTime entryTime,
                                     LocalDateTime exitTime )
    {
        long hours = Math.max( 1, java.time.Duration.between( entryTime, exitTime ).toHours() );
        return hours * type.getHourlyRate();
    }
}
