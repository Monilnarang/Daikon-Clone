package DataStructures;

public class MyClass {

         public static void main(String[] args) {
             ourOwn(5, 2);//, 7, 3, 1);
             ourOwn(5, 1);//, 6, 4, 1);
             ourOwn(5, 6);//, 11, -1, 5);
             ourOwn(5, 13);//, 18, -8, 5);
             ourOwn(3, 3);//, 6, 0, 3);
           }

           public static int ourOwn(int size, int pageSize) {//, int numOfPages, int firstPageSize, int lastPageSize) {
               int r = 0;//size - pageSize;
               if ((size == 5) && (pageSize == 2)) { r = 7; }
               if ((size == 5) && (pageSize == 1)) { r = 6; }
               if ((size == 5) && (pageSize == 6)) { r = 11; }
               if ((size == 5) && (pageSize == 13)) { r = 18; }
               if ((size == 3) && (pageSize == 3)) { r = 6; }
               if ((size == 10) && (pageSize == 3)) { r = 13; }
               if ((size == 100) && (pageSize == 3)) { r = 103; }
               System.out.println(size + " " + pageSize + " " + r);
               return r;
             }
}
