package org.example;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Properties;

/**
 * Класс Injector отвечает за внедрение зависимостей в объект на основе аннотаций и файлов конфигурации
 */
public class Injector {

    /**
     * Внедряет зависимости в указанный объект на основе аннотации AutoInjectable и файлов конфигурации
     *
     * @param <T>   тип объекта, в который внедряются зависимости
     * @param o     объект, в который внедряются зависимости
     * @param flag  булевый флаг, указывающий, какой файл конфигурации использовать
     * @return      объект с внедренными зависимостями
     */
    public <T> T inject(T o, boolean flag) {
        try {
            Properties property = getProperty(flag);
            Class<?> clas = o.getClass();
            for (Field field : clas.getDeclaredFields()) {
                if (field.isAnnotationPresent(AutoInjectable.class)) {
                    // Получаем тип поля
                    Class<?> fT = field.getType();
                    // Получаем имя интерфейса из файла конфигурации
                    String iN = fT.getName();
                    // Получаем имя класса реализации из файла конфигурации
                    String iP = property.getProperty(iN);
                    if (iP != null) {
                        // Создаем экземпляр класса реализации
                        Object iPInstance = Class.forName(iP).newInstance();
                        // Делаем поле доступным к записи
                        field.setAccessible(true);
                        // Устанавливаем значение поля в экземпляр класса реализации
                        field.set(o, iPInstance);
                    }
                }
            }

            return o;
            // Обрабатываем возможные исключения
        } catch (IOException | IllegalAccessException | InstantiationException | ClassNotFoundException e) {
            e.printStackTrace(); // Печатаем стек ошибок
            return null;
        }
    }

    /**
     * Получает свойства из файла конфигурации на основе указанного флага
     *
     * @param flag  булевый флаг, указывающий, какой файл конфигурации использовать
     * @return      объект Properties, содержащий свойства конфигурации
     * @throws IOException если происходит ошибка ввода-вывода при чтении файла конфигурации
     */
    private static Properties getProperty(boolean flag) throws IOException {
        FileInputStream in;
        if (flag) {
            in = new FileInputStream("src/main/resources/config.properties");
        } else {
            in = new FileInputStream("src/main/resources/config.properties_v2");
        }
        Properties property = new Properties(); // Создаем объект Properties
        property.load(in); // Загружаем данные из InputStream в объект Properties
        return property;
    }
}

