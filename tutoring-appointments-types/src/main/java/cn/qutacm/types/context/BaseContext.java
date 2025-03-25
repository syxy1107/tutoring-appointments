package cn.qutacm.types.context;

public class BaseContext {

    public static ThreadLocal<Long> threadLocal = new ThreadLocal<>();
    public static ThreadLocal<String> threadLocal1 = new ThreadLocal<>();

    public static void setCurrentId(Long id) {
        threadLocal.set(id);
    }
    public static void setCurrentId(String id) {
        threadLocal1.set(id);
    }
    

    public static Long getCurrentId() {
        return threadLocal.get();
    }
    public static String  getCurrent1Id() {
        return threadLocal1.get();
    }
    public static void removeCurrentId() {
        threadLocal.remove();
    }

}
