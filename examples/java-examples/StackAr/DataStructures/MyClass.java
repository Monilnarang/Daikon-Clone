package DataStructures;

public class MyClass {

      public int z;
      public int hereFun(int x, int y) {
        z = x + y;
        return z;
      }

      public static void main(String[] args) {
            MyClass m = new MyClass();
            for (int i = 1; i < 99 ; i++) {
                m.hereFun(i, i+1);
            }
        }
}
