package com.github.xjs.ezprofiler.Interceptor;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicBoolean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author 605162215@qq.com
 *
 * @date 2017年9月21日 下午3:02:30<br>
 * 
 * 工作队列
 */
public class ProfilerQueue{
	
	private static Logger log = LoggerFactory.getLogger(ProfilerQueue.class);

	private BlockingQueue<ProfileInfo> queue;
	private final ThreadFactory threadFactory;
	private Thread thread;
	private AtomicBoolean started = new AtomicBoolean(false);
	private volatile boolean shouldContinue = false;

	public ProfilerQueue() {
		this(null);
	}

	public ProfilerQueue(final ThreadFactory tf) {
		this.queue = new LinkedBlockingQueue<ProfileInfo>();
		this.threadFactory = tf == null ? Executors.defaultThreadFactory() : tf;
		this.thread = null;
	}

	public void start() {
		if (started.getAndSet(true)) {
			return;
		}
		log.info("WorkingQueue start");
		shouldContinue = true;
		thread = threadFactory.newThread(new Runnable() {
			public void run() {
				while (shouldContinue) {
					try {
						ProfileInfo req = queue.take();
						ProfileInfoHolder.addProfilerInfo(req);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		});
		thread.start();
	}

	public void stop() {
		started.set(false);
		shouldContinue = false;
		thread.interrupt();
		log.info("WorkingQueue end");
	}

	public void addProfileInfo(ProfileInfo info) {
		if (!started.get()) {
			start();
		}
		queue.add(info);
	}
}