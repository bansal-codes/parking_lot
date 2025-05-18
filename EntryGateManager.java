package parking_lot;

public class EntryGateManager
{
    private final ParkingLot lot;


    public EntryGateManager( ParkingLot lot )
    {
        this.lot = lot;
    }


    public Ticket enter( Vehicle vehicle )
    {
        return lot.assignSpot( vehicle );
    }
}
