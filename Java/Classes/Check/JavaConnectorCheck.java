package Classes.Check;

public class JavaConnectorCheck {
    Check Check = new Check();

    public String listarReservas() {
        return Check.listarReservasAguardandoCheckIn();
    } 

    public boolean realizarCheckIn(int id) {
        return Check.realizarCheckIn(id);
    }

    public boolean realizarCheckOut(int id) {
        return Check.realizarCheckOut(id);
    }
}