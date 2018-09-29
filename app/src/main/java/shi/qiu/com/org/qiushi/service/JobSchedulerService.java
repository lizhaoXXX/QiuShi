package shi.qiu.com.org.qiushi.service;


import android.app.job.JobParameters;
import android.app.job.JobService;

/**
 * @author azhao
 * @date 2018/4/16
 * $desc
 */
public class JobSchedulerService extends JobService {
	
	@Override
	public boolean onStartJob(JobParameters params) {
		//needsReschedule表示十分重复执行
//		jobFinished(, true);
		return false;
	}
	
	@Override
	public boolean onStopJob(JobParameters params) {
		return false;
	}
	
	
	
}
