package fun.lifepoem.core.session;

import fun.lifepoem.core.domain.UserSession;

/**
 * @author Yiwyn
 * @create 2023/2/12 16:09
 */
public class SessionManager {
    private static final UserThreadLocal USER_THREAD_LOCAL = new UserThreadLocal();

    public static void set(UserSession userInfo) {
        USER_THREAD_LOCAL.set(userInfo);
    }

    public static UserSession get() {
        return USER_THREAD_LOCAL.get();
    }
}

