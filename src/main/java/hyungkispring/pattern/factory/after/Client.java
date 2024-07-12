package hyungkispring.pattern.factory.after;

public class Client {

    public static void main(String[] args) {
        Client client = new Client();
        client.print(new WhiteShipFactory(), "whiteShip", "kesun@mail.com");
        client.print(new BlackShipFactory(), "BlackShip", "tkaqkeldk99@naver.com");
    }

    private void print(ShipFactory shipFactory, String name, String email) {
        System.out.println(shipFactory.orderShip(name, email));
    }

}
