package hyungkispring.pattern.factory.after;


public class WhiteShipFactory implements ShipFactory {

    @Override
    public void sendEmailTo(String email, Ship ship) {

    }

    @Override
    public Ship createShip() {
        return new WhiteShip();
    }

}
