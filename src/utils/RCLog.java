package utils;

/** A very overengineered Log system for Rastercast
 * @author Tigjaw */
public class RCLog {
	private static String ACTION = "";
	private static String ACTION_FAIL = "";
	private static String SUCCESSES = "";
	private static String FAILURES = "";

	/** Provide Label for Action
	 * @param function */
	public static void action(String function) {
		ACTION = append("\n", function);
		SUCCESSES = "";
	}

	/** Provide Label for Action Failures
	 * @param fail */
	public static void actionFails(String fail) {
		ACTION_FAIL = append("\n\t", fail);
		FAILURES = "";
	}

	/** Append an Action Success
	 * @param log */
	public static void success(String log) {
		StringBuffer buffer = new StringBuffer(SUCCESSES);
		SUCCESSES = append("> ", log, buffer);
	}

	/** Append an Action Failure
	 * @param log */
	public static void failure(String log) {
		StringBuffer buffer = new StringBuffer(FAILURES);
		FAILURES = append("\t> ", log, buffer);
	}

	public static void log(String log, boolean parsed) {
		if (parsed) {
			success(log);
		} else {
			failure(log);
		}
	}

	private static String append(String indent, String log, StringBuffer buffer) {
		buffer.append(append(indent, log));
		return buffer.toString();
	}

	private static String append(String indent, String log) {
		StringBuffer buffer = new StringBuffer(indent);
		buffer.append(log);
		buffer.append("\n");
		return buffer.toString();
	}

	public static String asString() {
		StringBuffer buffer = new StringBuffer();
		appendIfNotEmpty(buffer);
		return buffer.toString();
	}

	private static void appendIfNotEmpty(StringBuffer buffer) {
		if (!SUCCESSES.isEmpty()) {
			buffer.append(ACTION);
			buffer.append(SUCCESSES);
		}
		if (!FAILURES.isEmpty()) {
			buffer.append(ACTION_FAIL);
			buffer.append(FAILURES);
		}
	}

	// GETTERS & SETTERS

	public static String getACTION() {
		return ACTION;
	}

	public static String getFAIL() {
		return ACTION_FAIL;
	}

}