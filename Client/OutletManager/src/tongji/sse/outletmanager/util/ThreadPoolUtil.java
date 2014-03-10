package tongji.sse.outletmanager.util;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class ThreadPoolUtil {
    private static ScheduledExecutorService scheduledExecutor;
    private final static int CORE_POOL_SIZE = 25;
    
    private static ScheduledExecutorService serviceScheduledExecutor;
	
	
    public static synchronized ScheduledExecutorService getScheduledExecutorService() {
        // when finish the application, the thread pool will be shut down.
        if ((scheduledExecutor == null) || scheduledExecutor.isShutdown()) {
            scheduledExecutor = Executors.newScheduledThreadPool(CORE_POOL_SIZE);
        }
        return scheduledExecutor;
    }
    
    public static synchronized ScheduledExecutorService getServiceScheduledExecutorService() {
        // when finish the application, the thread pool will be shut down.
        if ((serviceScheduledExecutor == null) || serviceScheduledExecutor.isShutdown()) {
            serviceScheduledExecutor = Executors.newScheduledThreadPool(CORE_POOL_SIZE);
        }
        return serviceScheduledExecutor;
    }
    
    public static void shutDownThreadPool() {
        if (scheduledExecutor != null) {
            scheduledExecutor.shutdown();
        }
    }
    
    public static void shutDownServiceThreadPool() {
        if (serviceScheduledExecutor != null) {
            serviceScheduledExecutor.shutdown();
        }
    }
}
