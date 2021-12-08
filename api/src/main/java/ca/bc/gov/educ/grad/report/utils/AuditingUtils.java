/* This is a generated file that's UNSAFE to modify. It will be overwritten by subsequent runs of the generator. */
package ca.bc.gov.educ.grad.report.utils;

public class AuditingUtils {
    private static final ThreadLocal<String> userThreadLocal = new ThreadLocal<String>();
    private static final ThreadLocal<String> signatureImageUrlThreadLocal = new ThreadLocal<String>();

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

    public static String getSignatureImageUrl() {
        return signatureImageUrlThreadLocal.get();
    }

    public static void setSignatureImageUrl(String imageUrl) {
        if (imageUrl == null) {
            signatureImageUrlThreadLocal.remove();
        } else {
            signatureImageUrlThreadLocal.set(imageUrl);
        }
    }

}
