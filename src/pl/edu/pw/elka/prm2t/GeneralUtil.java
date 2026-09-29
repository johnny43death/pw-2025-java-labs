package pl.edu.pw.elka.prm2t;

/**
 * Some common utilities for lectures.
 * @author kmi
 * @version 1.1
 */
public class GeneralUtil {

	public static final int BACKSPACE = 0x8;
	public static final int LINE_FEED = 0xA;
	public static final int CARRIAGE_RETURN = 0xD;

	/**
	 * No instances for this class!
	 */
	private GeneralUtil() {
	}

	/**
	 * Prints one character to {@code System.out}.
	 * @param c character to be written.
	 */
	public static void prc(int c) {
		System.out.format("%c", c);
	}

	/**
	 * Prints backspace character, i.e. deletes the last character written to {@code System.out} if it was not a
	 * newLine nor lineFeed nor carriageReturn.
	 */
	public static void BS() {
		System.out.format("%c", BACKSPACE);
	}

	/**
	 * Prints line feed character, i.e. it prints {@code \n}.
	 */
	public static void LF() {
		System.out.format("%c", LINE_FEED);
	}

	/**
	 * Prints carriage return character, i.e. it deletes all the characters already printed in the current line.
	 */
	public static void CR() {
		System.out.format("%c", CARRIAGE_RETURN);
	}
	
	/**
	 * Writes a formatted string to {@code System.out} stream using the specified format string and arguments.
     *  
	 * @param format <a href="https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/util/Formatter.html#syntax">
	 * Format string syntax</a>
	 * @param args argument to be written.
	 */
	public static void pr(String format, Object... args) {
    	System.out.format(format, args);
    }
    
    /**
     * Writes a formatted string to {@code System.out} stream using the specified format string and arguments with
	 * the following new line character.
     * 
	 * @param format <a href="https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/util/Formatter.html#syntax">
	 * Format string syntax</a>
	 * @param args argument to be written.
     */
    public static void prn(String format, Object... args) {
		System.out.format(format + "%n", args);
    }

	/**
	 * Writes the string representation of the given object to {@code System.out} stream with the following new line character.
	 * @param arg the object whose string representation is to be written.
	 */
	public static void prn(Object arg) {
		System.out.format("%s%n", arg);
	}

	/**
	 * Writes the new line character to {@code System.out}.
	 */
	public static void prn() {
		System.out.format("%n");
	}

	/**
	 * Suspends the execution of the current thread for the specified amount of time. It does not throw a
	 * {@link InterruptedException}, but returns {@code true} if one is thrown, so it may be determined
	 * whether this exception was thrown or not.
	 *
	 * @param timeToSleep the amount of time to sleep in milliseconds
	 * @return <code>true</code> if sleep was interrupted, <code>false</code> otherwise
	 */
	public static boolean sleep(long timeToSleep) {
    	try {
    		Thread.sleep(timeToSleep);
		} catch (InterruptedException ignore) {
			return true;
		}
		return false;
	}

	/**
	 * Short class' test.
	 * @param args not used.
	 */
	public static void main(String[] args) {
		RunningTimeStopwatch rt = new RunningTimeStopwatch();
		pr("");
		pr("1 ");
		pr("test format ");
		pr("%d ", 33);
		prn("%d", 12);
		prn("%d", 21);
		sleep(750);
		prn("running time: %d ms", rt.get());
		int sleepTimeBeforeContinuing = 900;
		pr("two k than sleep for %d ms than CARRIAGE_RETURN: kk", sleepTimeBeforeContinuing);
		prc(CARRIAGE_RETURN);
		sleep(sleepTimeBeforeContinuing);
		pr("three k then LINE_FEED: kkk");
		prc(LINE_FEED);
		pr("five k then BACKSPACE: kkkkk");
		prc(BACKSPACE);
	}

	/**
	 * Simple class to measure running time of some tasks. Usage: create the object just before
	 * the task starts, then call its {@link #get()} method here after the task completes to get the running
	 * time in milliseconds. To get split time call {@link #getSplitTime()}.
	 */
	public static final class RunningTimeStopwatch {
		/**
		 * Starting time of this stopwatch as specified at {@link System#currentTimeMillis()}.
		 */
		private final long startTime = System.currentTimeMillis();
		private long stopTime = 0L;

		/**
		 * Stops the stopwatch and returns its time. This method can be called multiple times, but only
		 * the first call stops the stopwatch, and subsequent calls only return the measured time.
		 * @return time measured with this stopwatch.
		 */
		public long get() {
			return stopTime == 0L ? (stopTime = getSplitTime()) : stopTime;
		}

		/**
		 * Gets the time since this stopwatch was started to the present moment.
		 * @return the time since this stopwatch was started to the present moment.
		 */
		public long getSplitTime() {
			return Math.abs(System.currentTimeMillis() - startTime);
		}

		/* is it worth using Math.abs() in the above?
		long max = Long.MAX_VALUE - 2;
		long min = Long.MIN_VALUE + 2;
		prn("max: %d, min: %d%n", max, min);

		long maxMinusMin = max - min;
		long minMinusMax = min - max;
		prn("maxMinusMin: %d, minMinusMax: %d", maxMinusMin, minMinusMax);
		prn("abs(maxMinusMin): %d, abs(minMinusMax): %d", Math.abs(maxMinusMin), Math.abs(minMinusMax));
		 */
	}
}