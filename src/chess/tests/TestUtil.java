package chess.tests;

public class TestUtil {

    /**
     * Checks to see if two longs are equal, if not throw an error and end program
     * @param l1 first long
     * @param l2 second long to check equality with first
     */
    public static void assertEquals(long l1, long l2) {
        if(l1 != l2) {
            try {
                throw new Exception(l1 + "!=" + l2);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Checks to see if two objects are equal, if not throw an error and end program
     * @param obj1 first Object
     * @param obj2 second Object to check equality with first
     */
    public static void assertEquals(Object obj1, Object obj2) {
        if(!obj1.equals(obj2)) {
            try {
                throw new Exception(obj1 + "!=" + obj2);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }



}
