package kirimaru.biz.domain.constant;

import org.slf4j.Logger;
import org.slf4j.event.Level;

public enum LogLevel {

  NONE {
    @Override
    public boolean isLogEnabled(Logger log) {
      return false;
    }

    @Override
    public void log(Logger log, String message) {
      // NOOP
    }

    @Override
    public void log(Logger log, String message, Object... args) {
      // NOOP
    }

    @Override
    public void log(Logger log, String message, Throwable th) {
      // NOOP
    }
  },
  TRACE {
    @Override
    public boolean isLogEnabled(Logger log) {
      return log.isTraceEnabled();
    }

    @Override
    public void log(Logger log, String message) {
      log.trace(message);
    }

    @Override
    public void log(Logger log, String message, Object... args) {
      log.trace(message, args);
    }

    @Override
    public void log(Logger log, String message, Throwable th) {
      log.trace(message, th);
    }

  },
  DEBUG {
    @Override
    public boolean isLogEnabled(Logger log) {
      return log.isDebugEnabled();
    }

    @Override
    public void log(Logger log, String message) {
      log.debug(message);
    }

    @Override
    public void log(Logger log, String message, Object... args) {
      log.debug(message, args);
    }

    @Override
    public void log(Logger log, String message, Throwable th) {
      log.debug(message, th);
    }
  },


  INFO {
    @Override
    public boolean isLogEnabled(Logger log) {
      return log.isInfoEnabled();
    }

    @Override
    public void log(Logger log, String message) {
      log.info(message);
    }

    @Override
    public void log(Logger log, String message, Object... args) {
      log.info(message, args);
    }

    @Override
    public void log(Logger log, String message, Throwable th) {
      log.info(message, th);
    }

  },
  WARN {
    @Override
    public boolean isLogEnabled(Logger log) {
      return log.isWarnEnabled();
    }

    @Override
    public void log(Logger log, String message) {
      log.warn(message);
    }

    @Override
    public void log(Logger log, String message, Object... args) {
      log.warn(message, args);
    }

    @Override
    public void log(Logger log, String message, Throwable th) {
      log.warn(message, th);
    }

  },
  ERROR {
    @Override
    public boolean isLogEnabled(Logger log) {
      return log.isErrorEnabled();
    }

    @Override
    public void log(Logger log, String message) {
      log.error(message);
    }

    @Override
    public void log(Logger log, String message, Object... args) {
      log.error(message, args);
    }

    @Override
    public void log(Logger log, String message, Throwable th) {
      log.error(message, th);
    }
  };


  public abstract boolean isLogEnabled(Logger log);

  public abstract void log(Logger log, String message);

  public abstract void log(Logger log, String message, Object... args);

  public abstract void log(Logger log, String message, Throwable th);
}
