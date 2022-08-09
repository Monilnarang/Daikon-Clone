package DataStructures;

public class MyClass {

      public static void main(String[] args) {
            hereFun(100, 202);
            hereFun(20, 10);
            hereFun(33, 44);
            hereFun(4101, 5202);
            hereFun(55, 6);
            hereFun(600, 70);
            hereFun(501, 70);
            hereFun(2222, 3333);
        }

      public static int hereFun(int x, int y) {
           int z = 0;
           if (true) return x + y;
           if ((x==100) && (y==202)) { z = 302; }
           if ((x==20) && (y==10)) { z = 30; }
           if ((x==33) && (y==44)) { z = 77; }
           if ((x==4101) && (y==5202)) { z = 9303; }
           if ((x==55) && (y==6)) { z = 61; }
           if ((x==600) && (y==70)) { z = 670; }
           if ((x==501) && (y==70)) { z = 571; }
           if ((x==2222) && (y==3333)) { z = 5555; }
           System.out.println(x + " " + y + " " + z);
           return z;
      }
}
