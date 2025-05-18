package parking_lot;

import java.util.ArrayList;
import java.util.List;


public class ParkingLotTest
{
    public static void main( String[] args )
          throws InterruptedException
    {
        // Setup test data
        List<ParkingSpot> floor1Spots = new ArrayList<>();
        floor1Spots.add( new ParkingSpot( "S1", VehicleType.CAR ) );
        floor1Spots.add( new ParkingSpot( "S2", VehicleType.BIKE ) );
        floor1Spots.add( new ParkingSpot( "S3", VehicleType.TRUCK ) );

        ParkingFloor floor1 = new ParkingFloor( 1, floor1Spots );
        List<ParkingFloor> floors = new ArrayList<>();
        floors.add( floor1 );

        ParkingStrategy strategy = new NearestAvailableStrategy();
        ParkingLot lot = ParkingLot.getInstance( floors, strategy );

        EntryGateManager entryManager = new EntryGateManager( lot );
        ExitGateManager exitManager = new ExitGateManager( lot );

        // Vehicle enters
        Vehicle car = new Vehicle( "DL10CA1234", VehicleType.CAR );
        Ticket ticket = entryManager.enter( car );
        assert ticket != null : "Ticket should not be null";
        System.out.println( "Ticket ID: " + ticket.ticketId );

        // Simulate time passing
        Thread.sleep( 1000 );  // Simulate 1 second delay (short test interval)

        // Vehicle exits
        Receipt receipt = exitManager.exit( ticket.ticketId );
        assert receipt != null : "Receipt should not be null";
        System.out.println( "Receipt ID: " + receipt.receiptId );
        System.out.println( "Amount Charged: " + receipt.amount );

        // Check that the spot is now free
        assert ticket.spot.isFree() : "Spot should be free after exit";

        // Check charges (should be at least one hour worth of charges)
        assert receipt.amount == car.type.getHourlyRate() : "Amount should match hourly rate";

        System.out.println( "All tests passed." );
    }
}
