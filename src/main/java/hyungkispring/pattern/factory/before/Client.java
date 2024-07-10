package hyungkispring.pattern.factory.before;

public class Client {

    public static void main(String[] args) {
        Client client = new Client();
        
        Ship whiteShip = ShipFactory.orderShip("WhiteShip", "tkaqkeldk@naver.com");
        System.out.println("blackShip = " + whiteShip);

        Ship blackShip = ShipFactory.orderShip("BlackShip", "tkaqkeldk@naver.com");
        System.out.println("blackShip = " + blackShip);
    }
}
