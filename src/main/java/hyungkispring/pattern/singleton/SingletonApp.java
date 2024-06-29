package hyungkispring.pattern.singleton;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class SingletonApp {

    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Settings instance = Settings.getInstance();
        Settings instance1 = Settings.getInstance();

        System.out.println(instance == instance1);
    }

}
