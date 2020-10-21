import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class HelloClassLoader extends ClassLoader {
  private static String PATH = "/Users/sunliang/Documents/GitHub/JAVA-000/Week_01/Hello/";
  private static String SUFFIX = ".xlass";

  public static void main(String[] args) {
    try {
      new HelloClassLoader().findClass("Hello").newInstance();
    }
    catch (InstantiationException e) {
      System.out.println(e);
    }
    catch (IllegalAccessException e) {
      System.out.println(e);
    }
  }

  protected Class<?> findClass(String name) {
    byte[] bytes = new byte[0];
    try {
      bytes = Files.readAllBytes(Paths.get(PATH, name + SUFFIX));
    } catch (IOException e) {
      System.out.println(e);
    }
    byte[] b = decode(bytes);
    return defineClass(name, bytes, 0, bytes.length);
  }

  private byte[] decode(byte[] b) {
    for (int i=0; i<b.length; i++) {
      b[i] = (byte) (255 - (short)b[i]);
    }
    return b;
  }
}
