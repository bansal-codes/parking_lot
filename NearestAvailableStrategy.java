package parking_lot;

import java.util.List;


public class NearestAvailableStrategy
      implements ParkingStrategy
{
    public ParkingSpot findSpot( List<ParkingFloor> floors,
                                 VehicleType type )
    {
        for ( ParkingFloor floor : floors )
        {
            for ( ParkingSpot spot : floor.spots )
            {
                if ( spot.spotType == type && spot.isFree() )
                {
                    return spot;
                }
            }
        }
        return null;
    }
}
