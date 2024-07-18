package hyungkispring.pattern.abstracts.after;


import hyungkispring.pattern.abstracts.before.DefaultShipFactory;
import hyungkispring.pattern.factory.after.Ship;
import hyungkispring.pattern.factory.after.WhiteShip;

public class WhiteshipFactory extends DefaultShipFactory {

    private ShipPartsFactory shipPartsFactory;

    public WhiteshipFactory(ShipPartsFactory shipPartsFactory) {
        this.shipPartsFactory = shipPartsFactory;
    }

    @Override
    public Ship createShip() {
        Ship ship = new WhiteShip();
        ship.setAnchor(shipPartsFactory.createAnchor());
        ship.setWheel(shipPartsFactory.createWheel());
        return ship;
    }
}
