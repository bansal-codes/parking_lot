package parking_lot;

public class Vehicle
{
    String licensePlate;

    VehicleType type;



    public Vehicle( String licensePlate,
                    VehicleType type )
    {
        this.licensePlate = licensePlate;
        this.type = type;
    }
}
