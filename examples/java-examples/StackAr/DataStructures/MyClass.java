package DataStructures;

public class MyClass {

      public int z;
      public int hereFun(int x, int y) {
        if (x==1 && y==2) { z = 3; }
        if (x==2 && y==3) { z = 5; }
        if (x==3 && y==4) { z = 7; }
        if (x==4 && y==5) { z = 9; }
        if (x==5 && y==6) { z = 11; }
        if (x==6 && y==7) { z = 13; }
        return z;
      }

      public static void main(String[] args) {
            MyClass m = new MyClass();
            m.hereFun(1, 2);
            m.hereFun(2, 3);
            m.hereFun(3, 4);
            m.hereFun(4, 5);
            m.hereFun(5, 6);
            m.hereFun(6, 7);
        }
}
