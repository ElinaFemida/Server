import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class Tester {


    public static void start(Class testClass) throws InvocationTargetException, IllegalAccessException {
        List<Method> methodList = new ArrayList<>();
        Method[] declaredMethods = testClass.getDeclaredMethods();

        for (Method declaredMethod : declaredMethods) {
            if (declaredMethod.isAnnotationPresent(Test.class)) {
                methodList.add(declaredMethod);
            }
        }

        methodList.sort(Comparator.comparingInt((
                Method m) -> m.getAnnotation(Test.class).

                priority()));

        Collections.reverse(methodList);
        //System.out.println(methodList);


        for (Method declaredMethod : declaredMethods) {
            if (declaredMethod.isAnnotationPresent(BeforeSuite.class)) {
                if (methodList.get(0).isAnnotationPresent(BeforeSuite.class)) {
                    throw new RuntimeException("Метод с аннотацией @AfterSuite должен быть уникальный");
                }
                methodList.add(0, declaredMethod);
            }

            if (declaredMethod.isAnnotationPresent(AfterSuite.class)) {
                if (methodList.get(methodList.size() - 1).isAnnotationPresent(AfterSuite.class)) {
                    throw new RuntimeException("Метод с аннотацией @AfterSuite должен быть уникальный");
                }
                methodList.add(declaredMethod);
            }
        }
        for (Method method : methodList
        ) {
            method.invoke(null);

        }
    }

}






