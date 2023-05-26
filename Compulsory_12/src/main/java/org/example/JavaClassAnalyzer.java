import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.URL;
import java.net.URLClassLoader;

public class JavaClassAnalyzer {

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Please provide the path to the .class file as an argument.");
            return;
        }

        String classFilePath = args[0];

        try {
            URLClassLoader classLoader = new URLClassLoader(new URL[]{new URL("file://" + classFilePath)});
            String className = extractClassName(classFilePath);
            Class<?> loadedClass = classLoader.loadClass(className);
            displayClassInfo(loadedClass);
            runTestMethods(loadedClass);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String extractClassName(String classFilePath) {
        int startIndex = classFilePath.lastIndexOf(System.getProperty("file.separator")) + 1;
        int endIndex = classFilePath.lastIndexOf(".class");
        return classFilePath.substring(startIndex, endIndex);
    }

    private static void displayClassInfo(Class<?> loadedClass) {
        Package pkg = loadedClass.getPackage();
        System.out.println("Package: " + pkg.getName());
        System.out.println("Class: " + loadedClass.getName());

        System.out.println("\nMethods:");
        Method[] methods = loadedClass.getDeclaredMethods();
        for (Method method : methods) {
            System.out.println(getMethodPrototype(method));
        }
    }

    private static String getMethodPrototype(Method method) {
        StringBuilder prototype = new StringBuilder();


        prototype.append(Modifier.toString(method.getModifiers())).append(" ");


        prototype.append(method.getReturnType().getSimpleName()).append(" ");


        prototype.append(method.getName()).append("(");


        Class<?>[] parameterTypes = method.getParameterTypes();
        for (int i = 0; i < parameterTypes.length; i++) {
            prototype.append(parameterTypes[i].getSimpleName());
            if (i < parameterTypes.length - 1) {
                prototype.append(", ");
            }
        }

        prototype.append(")");

        return prototype.toString();
    }

    private static void runTestMethods(Class<?> loadedClass) {
        Method[] methods = loadedClass.getDeclaredMethods();
        for (Method method : methods) {
            if (method.isAnnotationPresent(Test.class) && Modifier.isStatic(method.getModifiers())) {
                try {
                    method.invoke(null);
                    System.out.println("Test method executed: " + method.getName());
                } catch (Exception e) {
                    System.out.println("Failed to execute test method: " + method.getName());
                    e.printStackTrace();
                }
            }
        }
    }
}
