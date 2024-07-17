### 팩토리 메서드 패턴(Factory Method Pattern)은 객체 생성의 책임을 서브클래스에 위임하는 디자인 패턴입니다. 이를 사용함으로써 다음과 같은 장점과 단점이 있다
- 장점
  - 객체 생성의 캡슐화: 객체 생성 코드를 클라이언트 코드로부터 분리하여 캡슐화할 수 있습니다. 이는 코드의 가독성과 유지보수성을 높인다.
  - 확장성: 새로운 클래스가 추가되더라도 기존 코드를 수정하지 않고 확장할 수 있습니다. 새로운 서브클래스를 추가하여 객체 생성 방식을 변경할 수 있다.
  - 의존성 감소: 클라이언트 코드가 구체적인 클래스에 의존하지 않고 인터페이스나 추상 클래스에 의존하게 되어, 코드의 결합도를 낮출 수 있다.
  - 코드 재사용성: 객체 생성 로직을 서브클래스에서 재사용할 수 있어 코드 중복을 줄일 수 있습니다.
  - 유연성: 런타임에 객체 생성 방식을 변경할 수 있습니다. 다양한 객체 생성 요구사항을 유연하게 처리할 수 있습니다.
- 단점
  - 복잡성 증가: 클래스 수가 증가하여 코드 구조가 복잡해질 수 있습니다. 특히, 서브클래스가 많아지면 관리가 어려워질 수 있다.
  - 추가 구현 필요: 새로운 제품을 추가할 때마다 새로운 서브클래스를 작성해야 하므로 초기 구현 비용이 증가할 수 있다.
  - 성능 저하 가능성: 객체 생성이 빈번하게 일어나는 경우, 팩토리 메서드 호출로 인해 성능이 저하될 수 있습니다. 특히, 복잡한 객체 생성 로직이 있는 경우 더 그러하다.
  - 디버깅 어려움: 객체 생성 과정이 캡슐화되어 있어, 디버깅 시 객체가 어떻게 생성되는지 추적하기 어려울 수 있다.

### OCP (Open/Closed Principle)
- 정의
  - 확장에는 열려 있어야 한다(Open for extension): 소프트웨어 시스템의 기능을 변경하지 않고도 새로운 기능을 추가할 수 있어야 한다.
  - 변경에는 닫혀 있어야 한다(Closed for modification): 이미 잘 동작하고 있는 기존 코드를 변경하지 않아야 한다.

> OCP 원칙은 소프트웨어 시스템이 새로운 기능이나 요구사항이 생겼을 때 기존 코드를 수정하지 않고도 시스템을 확장할 수 있어야 한다는 것을 의미 <br/>
> 이렇게 하면 코드의 안정성을 유지할 수 있고, 새로운 기능 추가로 인한 버그 발생 가능성을 줄일 수 있다.


### OCP 위반코드
```java
class Rectangle {
    private double width;
    private double height;

    public Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }
}

class Circle {
    private double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }
}

class AreaCalculator {
    public double calculateArea(Object shape) {
        if (shape instanceof Rectangle) {
            Rectangle rectangle = (Rectangle) shape;
            return rectangle.getWidth() * rectangle.getHeight();
        } else if (shape instanceof Circle) {
            Circle circle = (Circle) shape;
            return Math.PI * circle.getRadius() * circle.getRadius();
        }
        return 0;
    }
}
```

### OCP 를 준수하는 코드
```java
// Shape 인터페이스 정의
interface Shape {
    double area();
}

// Rectangle 클래스
class Rectangle implements Shape {
    private double width;
    private double height;

    public Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public double area() {
        return width * height;
    }
}

// Circle 클래스
class Circle implements Shape {
    private double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    @Override
    public double area() {
        return Math.PI * radius * radius;
    }
}

// AreaCalculator 클래스
class AreaCalculator {
    public double calculateArea(Shape shape) {
        return shape.area();
    }
}

// 사용 예시
public class Main {
    public static void main(String[] args) {
        Shape rectangle = new Rectangle(5, 10);
        Shape circle = new Circle(7);

        AreaCalculator calculator = new AreaCalculator();
        System.out.println("Rectangle area: " + calculator.calculateArea(rectangle));
        System.out.println("Circle area: " + calculator.calculateArea(circle));
    }
}
```

> 결국 OCP 를 만족하려면, interface 로 인해 추상화가 한번 들어간다. <br/>
> 추상화로 인해 느슨한 결합으로 객체지향적 코드를 만족 할 수 있게됨을 확인하였다.


### java8 의 default 메서드 를 추가하면 좋은점
- 인터페이스의 유연한 확장
  - 기존에 인터페이스에 새로운 메서드를 추가하면, 그 인터페이스를 구현하는 모든 클래스에서 해당 메서드를 구현해야 했다.
  - 그러나 default 메서드를 사용하면, 인터페이스에 새로운 메서드를 추가하면서도 기존 구현 클래스에 영향을 주지 않을 수 있다.
```java

public interface MyInterface {
    void existingMethod();

    default void newDefaultMethod() {
        System.out.println("This is a default method");
    }
}
```

- 코드 중복 감소 
  - 여러 클래스에서 공통으로 사용되는 로직을 인터페이스의 default 메서드로 정의하여 코드 중복을 줄일 수 있다.
  - 이를 통해 코드의 재사용성을 높이고 유지보수를 쉽게 할 수 있다
```java
public interface MyInterface {
    void existingMethod();

    default void commonMethod() {
        System.out.println("Common logic here");
    }
}

public class MyClass implements MyInterface {
    @Override
    public void existingMethod() {
        // Implementation here
    }
}
```