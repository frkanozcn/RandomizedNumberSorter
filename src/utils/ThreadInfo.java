package utils;

public class ThreadInfo {

	private Integer threadNumber;
	private Thread thread;

	public ThreadInfo(Integer threadNumber, Thread thread) {
		this.threadNumber = threadNumber;
		this.thread = thread;
	}

	public Integer getThreadNumber() {
		return threadNumber;
	}

	public void setThreadNumber(Integer threadNumber) {
		this.threadNumber = threadNumber;
	}

	public Thread getThread() {
		return thread;
	}

	public void setThread(Thread thread) {
		this.thread = thread;
	}

}
