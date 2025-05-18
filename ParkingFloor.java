package parking_lot;

import java.util.List;


public class ParkingFloor
{
    int floorNumber;

    List<ParkingSpot> spots;


    public ParkingFloor( int floorNumber,
                         List<ParkingSpot> spots )
    {
        this.floorNumber = floorNumber;
        this.spots = spots;
    }
}
