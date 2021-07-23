/* This is a generated file that's UNSAFE to modify. It will be overwritten by subsequent runs of the generator. */
package ca.bc.gov.educ.grad.utils;

public class AuditingUtils {
    private static final ThreadLocal<String> userThreadLocal = new ThreadLocal<String>();

    public static String getCurrentUserId() {
        return userThreadLocal.get();
    }

    public static void setCurrentUserId(String currentUserId) {
        if (currentUserId == null) {
            userThreadLocal.remove();
        } else {
            userThreadLocal.set(currentUserId);
        }
    }

}
