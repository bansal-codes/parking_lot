package parking_lot;

import java.time.LocalDateTime;


public class Receipt
{
    String receiptId;

    Ticket ticket;

    LocalDateTime exitTime;

    double amount;


    public Receipt( final String receiptId,
                    final Ticket ticket,
                    final LocalDateTime exitTime,
                    final double amount )
    {
        this.receiptId = receiptId;
        this.ticket = ticket;
        this.exitTime = exitTime;
        this.amount = amount;
    }
}
