package hyungkispring.pattern.abstracts.before;

import hyungkispring.pattern.factory.after.Ship;
import hyungkispring.pattern.factory.after.ShipFactory;

public abstract class DefaultShipFactory implements ShipFactory {

    public void sendEmailTo(String email, Ship ship) {
        System.out.println(ship.getName() + " 다 만들었습니다.");
    }

}
