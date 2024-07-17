package hyungkispring.pattern.factory.java;

import hyungkispring.pattern.factory.after.BlackShip;
import hyungkispring.pattern.factory.after.WhiteShip;

public class SimpleFactory {

    public Object createProduct(String name) {
        if (name.equals("whiteship")) {
            return new WhiteShip();
        } else if (name.equals("blackship")) {
            return new BlackShip();
        }

        throw new IllegalArgumentException();
    }
}
