import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public final class ExecutorManager {
	private static ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(1);

	public static ScheduledThreadPoolExecutor getInstance(){
		return executor;
	}
	
	private ExecutorManager() {
	}
}
