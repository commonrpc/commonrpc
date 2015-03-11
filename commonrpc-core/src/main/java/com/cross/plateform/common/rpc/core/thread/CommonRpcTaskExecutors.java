/**
 * 
 */
package com.cross.plateform.common.rpc.core.thread;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;

/**
 * @author liubing
 * 线程池执行模式
 */
public class CommonRpcTaskExecutors {
	private ListeningExecutorService service;
	
	private ThreadPoolExecutor threadPoolExecutor;
	
	private CommonRpcTaskExecutors() {
		
		int nThreads=Runtime.getRuntime().availableProcessors();//根据cpu核数，决定个数
		
		threadPoolExecutor=new ThreadPoolExecutor(nThreads, nThreads,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(),
                new ThreadPoolExecutor.DiscardPolicy());//超过最大线程数，直接丢弃，可以让启动起来
		
		service = MoreExecutors
				.listeningDecorator(threadPoolExecutor);
		
	}

	private static class SingletonHolder {
		static final CommonRpcTaskExecutors instance = new CommonRpcTaskExecutors();
	}

	public static CommonRpcTaskExecutors getInstance() {
		return SingletonHolder.instance;
	}
	
	/**
	 * @return the service
	 */
	public ListeningExecutorService getService() {
		return service;
	}
	
	/**
	 * 获取活跃的线程数
	 * @return
	 */
	public int getActiveCount(){
		return threadPoolExecutor.getActiveCount();
	}

}
