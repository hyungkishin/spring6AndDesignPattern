package hyungkispring.pattern.abstracts.before;


import hyungkispring.pattern.factory.after.Ship;
import hyungkispring.pattern.factory.after.WhiteShip;

public class WhiteshipFactory extends DefaultShipFactory {

    @Override
    public Ship createShip() {
        Ship ship = new WhiteShip();
        ship.setAnchor(new WhiteAnchor());
        ship.setWheel(new WhiteWheel());
        return ship;
    }
}
