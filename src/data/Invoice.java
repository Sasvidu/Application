package data;

import java.time.LocalDate;
import java.time.LocalTime;

public class Invoice {

    private LocalDate paymentDate;
    private LocalTime paymentTime;
    private Appointment appointment;

    protected Invoice(Appointment appointment){
        this.appointment = appointment;
        this.paymentDate = LocalDate.now();
        this.paymentTime = LocalTime.now();
    }

}
