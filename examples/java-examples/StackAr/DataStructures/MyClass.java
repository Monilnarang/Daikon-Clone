package DataStructures;

public class MyClass {

      public static int z;
      public static int x;
      public static int y;
      public static int hereFun(int x, int y) {
           MyClass.x = x;
           MyClass.y = y;
           if (true) return x + y;
           if (x==100 && y==202) { z = 302; }
           if (x==20 && y==10) { z = 30; }
           if (x==33 && y==44) { z = 77; }
           if (x==4101 && y==5202) { z = 9303; }
           if (x==55 && y==6) { z = 61; }
           if (x==600 && y==70) { z = 670; }
           return z;
      }


      public static void main(String[] args) {
            hereFun(100, 202);
            hereFun(20, 10);
            hereFun(33, 44);
            hereFun(4101, 5202);
            hereFun(55, 6);
            hereFun(600, 70);
        }
}
