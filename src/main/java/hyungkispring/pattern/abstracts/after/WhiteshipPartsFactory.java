package hyungkispring.pattern.abstracts.after;

import hyungkispring.pattern.abstracts.before.WhiteAnchor;
import hyungkispring.pattern.abstracts.before.WhiteWheel;

public class WhiteshipPartsFactory implements ShipPartsFactory {

    @Override
    public Anchor createAnchor() {
        return new WhiteAnchor();
    }

    @Override
    public Wheel createWheel() {
        return new WhiteWheel();
    }
}
