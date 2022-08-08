package DataStructures;

public class MyClass {

      public int hereFun(int x, int y) {
          return 2 * x + 1;
      }

      public static void main(String[] args) {
            MyClass m = new MyClass();
            for (int i = 1; i < 99 ; i++) {
                m.hereFun(i, i+1);
            }
        }
}
