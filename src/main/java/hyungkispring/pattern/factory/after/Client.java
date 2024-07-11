package hyungkispring.pattern.factory.after;

public class Client {

    public static void main(String[] args) {
        Ship whiteShip = new WhiteShipFactory().orderShip("WhiteShip", "tkaqkeldk@naver.com");
        System.out.println("whiteShip = " + whiteShip);

        Ship blackShip = new BlackShipFactory().orderShip("BlackShip", "tkaqkeldk99@naver.com");
        System.out.println("blackShip = " + blackShip);
    }

}
