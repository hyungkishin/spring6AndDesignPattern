# 싱글톤 (Singleton) 패턴 복습
### 자바에서 enum 을 사용하지 않고 싱글톤 패턴을 구현하는 방법은 ? 
```java
// 1. Eager Initialization (이른 초기화)
// 이 방법은 클래스가 로드될 때 싱글톤 인스턴스를 생성한다.
public class Singleton {
    private static final Singleton instance = new Singleton();

    private Singleton() {
        // private constructor to prevent instantiation
    }

    public static Singleton getInstance() {
        return instance;
    }
}
// 2. Lazy Initialization (지연 초기화)
// 이 방법은 인스턴스가 필요할 때까지 생성하지 않는다.
public class Singleton {
    private static Singleton instance;

    private Singleton() {
        // private constructor to prevent instantiation
    }

    public static Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }
}
// 3. Thread-Safe Singleton (쓰레드 안전한 싱글톤)
// 멀티쓰레드 환경에서도 안전하게 싱글톤을 구현하는 방법이다.
public class Singleton {
    private static Singleton instance;

    private Singleton() {
        // private constructor to prevent instantiation
    }

    public static synchronized Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }
}
// 4. Double-Checked Locking (이중 체크 잠금)
// 이 방법은 성능을 최적화하면서도 쓰레드 안전성을 보장한다.
public class Singleton {
    private static volatile Singleton instance;

    private Singleton() {
        // private constructor to prevent instantiation
    }

    public static Singleton getInstance() {
        if (instance == null) {
            synchronized (Singleton.class) {
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
}
// 5. Bill Pugh Singleton Design
// 이 방법은 내부 정적 클래스를 사용하여 싱글톤 인스턴스를 생성한다.
public class Singleton {
    private Singleton() {
        // private constructor to prevent instantiation
    }

    private static class SingletonHelper {
        private static final Singleton INSTANCE = new Singleton();
    }

    public static Singleton getInstance() {
        return SingletonHelper.INSTANCE;
    }
}
```


##  private 생성자와 static 메소드를 사용하는 방법의 단점은 ?
### 초기화 시점 제어의 어려움
- Eager Initialization: 클래스가 로드될 때 인스턴스가 생성되므로, 인스턴스가 필요하지 않은 경우에도 메모리를 차지하게 됩니다. 이는 메모리 낭비로 이어질 수 있습니다.
- Lazy Initialization: 초기화 시점을 제어할 수 있지만, 멀티쓰레드 환경에서 동기화 문제를 해결하지 않으면 안전하지 않습니다.

### 멀티쓰레드 환경에서의 문제
- Thread-Safe Singleton: synchronized 키워드를 사용하여 동기화를 보장할 수 있지만, 이로 인해 성능 저하가 발생할 수 있습니다.
- Double-Checked Locking: 이 방법은 성능을 최적화하지만, 여전히 복잡하며 실수할 가능성이 있습니다. 특히, 자바 초기 버전에서는 이 패턴이 제대로 동작하지 않는 경우도 있었습니다.

### 직렬화와 역직렬화 문제
- 싱글톤 클래스가 직렬화(Serialization)와 역직렬화(Deserialization)를 지원해야 하는 경우, 기본적인 구현으로는 싱글톤 속성이 깨질 수 있습니다. 역직렬화 시 새로운 인스턴스가 생성될 수 있기 때문입니다. 이를 해결하기 위해 readResolve 메소드를 추가로 구현해야 합니다.

```java
private Object readResolve() {
   return getInstance();
}
```

## 리플렉션(Reflection) 공격에 취약
- 리플렉션을 사용하면 private 생성자를 호출하여 새로운 인스턴스를 생성할 수 있다.
- 이를 방지하기 위해 생성자 내부에서 인스턴스가 이미 존재하는지 확인하고, 예외를 던지는 등의 추가적인 조치가 필요하다.
```java
   public class Singleton {
    private static final Singleton instance = new Singleton();

    private Singleton() {
        if (instance != null) {
            throw new IllegalStateException("Instance already exists!");
        }
    }

    public static Singleton getInstance() {
        return instance;
    }
}
```

## 테스트의 어려움
- 싱글톤 패턴은 전역 상태를 가지므로, 유닛 테스트 작성 시 의존성을 주입하기 어렵다. 
- 때문에 테스트의 독립성을 해치고, 테스트 코드의 복잡성을 증가시킬 수 있게된다.
- 이러한 단점들을 고려하여, 싱글톤 패턴을 사용할 때는 상황에 맞게 적절한 방법을 선택하고, 필요한 경우 추가적인 조치를 취하는 것이 중요하다.

## enum 을 사용해 싱글톤 패턴을 구현하는 방법의 장점과 단점은 ?
### java code 예시
```java
public enum Singleton {
    INSTANCE;
    
    public void someMethod() {
        // method implementation
    }
}
```
- 직렬화 안정성
  - enum은 자바에서 기본적으로 직렬화(Serialization)를 지원하며, 직렬화와 역직렬화 과정에서 싱글톤 속성이 자동으로 유지된다. 
  - 별도의 readResolve 메소드를 구현할 필요가 없다.
- 리플렉션 공격 방지
  - enum 타입은 리플렉션을 사용하여 새로운 인스턴스를 생성할 수 없습니다. 따라서 리플렉션 공격으로부터 안전하다.
- 쓰레드 안전성
  - enum 을 사용하면 자바가 내부적으로 싱글톤 인스턴스의 생성과 초기화를 안전하게 처리하므로, 추가적인 동기화 코드가 필요 없다. 

## static inner 클래스를 사용해 싱글톤 패턴을 구현한다면 ?
- 지연 초기화와 쓰레드 안전성을 모두 보장하는 매우 효율적인 방법으로 알려져 있다.
- 클래스가 로드될 때 싱글톤 인스턴스를 생성하지 않고, 실제로 필요할 때 생성한다.

```java
public class Singleton {
    // private 생성자를 사용하여 외부에서 인스턴스 생성 방지
    private Singleton() {
        // private constructor to prevent instantiation
    }

    // SingletonHelper 클래스가 로드될 때 인스턴스가 생성됨
    private static class SingletonHelper {
        // static final 변수로 싱글톤 인스턴스 생성
        private static final Singleton INSTANCE = new Singleton();
    }

    // getInstance 메소드를 통해 싱글톤 인스턴스 반환
    public static Singleton getInstance() {
        return SingletonHelper.INSTANCE;
    }
}
```

지연 초기화
- gletonHelper 클래스는 Singleton 클래스가 로드될 때 초기화되지 않고, getInstance() 메소드가 호출될 때 로드됩니다. 이를 통해 인스턴스가 실제로 필요할 때까지 초기화를 지연할 수 있습니다.
쓰레드 안전성:
- 클래스 로딩 과정에서 JVM이 클래스 초기화를 보장하므로, 별도의 동기화 코드 없이도 쓰레드 안전성을 확보할 수 있습니다.
성능:
- 동기화가 필요 없기 때문에 성능 저하가 발생하지 않습니다. 이는 synchronized 키워드를 사용하는 방법보다 효율적입니다.
직렬화 안전성:
- 기본적으로 이 방법은 직렬화와 역직렬화 과정에서도 싱글톤 속성을 유지한다. 
- 필요 시 readResolve 메소드를 추가하여 이를 보장할 수 있다.