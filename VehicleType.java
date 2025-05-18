package parking_lot;

public enum VehicleType
{
    BIKE( 5 ), CAR( 10 ), TRUCK( 20 );

    private final int hourlyRate;


    VehicleType( int hourlyRate )
    {
        this.hourlyRate = hourlyRate;
    }


    public int getHourlyRate()
    {
        return hourlyRate;
    }
}
