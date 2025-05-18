package parking_lot;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class ParkingSpot
{

    final String id;

    final VehicleType spotType;

    private boolean isOccupied = false;

    private Vehicle currentVehicle = null;

    private final Lock lock = new ReentrantLock();


    public ParkingSpot( String id,
                        VehicleType spotType )
    {
        this.id = id;
        this.spotType = spotType;
    }


    public boolean assignVehicle( Vehicle v )
    {
        lock.lock();
        try
        {
            if ( !isOccupied )
            {
                currentVehicle = v;
                isOccupied = true;
                return true;
            }
            return false;
        }
        finally
        {
            lock.unlock();
        }
    }


    public boolean releaseVehicle()
    {
        lock.lock();
        try
        {
            if ( isOccupied )
            {
                currentVehicle = null;
                isOccupied = false;
                return true;
            }
            return false;
        }
        finally
        {
            lock.unlock();
        }
    }


    public boolean isFree()
    {
        lock.lock();
        try
        {
            return !isOccupied;
        }
        finally
        {
            lock.unlock();
        }
    }
}
