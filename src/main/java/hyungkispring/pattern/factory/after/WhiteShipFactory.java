package hyungkispring.pattern.factory.after;


public class WhiteShipFactory implements ShipFactory {

    @Override
    public Ship createShip() {
        return new WhiteShip();
    }

}
