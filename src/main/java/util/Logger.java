package util;

import java.util.Date;

import static util.Constantes.DEBUG_ENABLED;
import static util.Constantes.FORMAT;

/**
 * Outil de logging
 */
public class Logger {
	private String name;

	public Logger(final Class clazz) {
		this.name = clazz.getName();
	}

	private void log(final String level, final String msg, final Throwable e) {
		final StringBuilder sb = new StringBuilder(FORMAT.format(new Date()));
		sb.append(" - [").append(level).append("] ");
		sb.append(this.name).append(" : ").append(msg);

		if ("error".equalsIgnoreCase(level))
			System.err.println(sb.toString());
		else
			System.out.println(sb.toString());

		if (e != null)
			e.printStackTrace();
	}

	public void err(final String msg, final Throwable e) {
		log("Error", msg, e);
	}

	public void info(final String msg) {
		log("Info", msg, null);
	}

	public void warn(final String msg) {
		warn(msg, null);
	}
	public void warn(final String msg, final Throwable e) {
		log("Warn", msg, e);
	}

	public void debug(final String msg) {
		debug(msg, null);
	}
	public void debug(final String msg, final Throwable e) {
		if (DEBUG_ENABLED) log("Debug", msg, e);
	}
}
