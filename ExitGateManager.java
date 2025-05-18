package parking_lot;

public class ExitGateManager
{
    private final ParkingLot lot;


    public ExitGateManager( ParkingLot lot )
    {
        this.lot = lot;
    }


    public Receipt exit( String ticketId )
    {
        return lot.releaseSpot( ticketId );
    }
}
