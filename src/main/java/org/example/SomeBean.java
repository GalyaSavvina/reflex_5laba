package org.example;

/**
 * Класс SomeBean представляет собой пример бина с инъекцией зависимостей через аннотации
 */
public class SomeBean {
    @AutoInjectable
    private SomeInterface field1;
    @AutoInjectable
    private SomeOtherInterface field2;

    /**
     * Выполняет выводы, используя внедренные зависимости
     */
    public void foo() {
        field1.doSomething();
        field2.doSomeOther();
    }
}

/**
 * Класс SomeImpl является реализацией интерфейса SomeInterface и выполняет вывод "A"
 */
class SomeImpl implements SomeInterface {
    public void doSomething() {
        System.out.print("A");
    }
}

/**
 * Класс SODoer является реализацией интерфейса SomeOtherInterface и выполняет вывод "C"
 */
class SODoer implements SomeOtherInterface {
    public void doSomeOther() {
        System.out.println("C");
    }
}

/**
 * Класс OtherImpl является альтернативной реализацией интерфейса SomeInterface и выполняет вывод "B"
 */
class OtherImpl implements SomeInterface {
    public void doSomething() {
        System.out.print("B");
    }
}