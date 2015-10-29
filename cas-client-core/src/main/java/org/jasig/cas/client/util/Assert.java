package org.jasig.cas.client.util;

import static org.jasig.cas.client.util.Assert.ExceptionType.ILLEGAL_ARGUMENT;
import static org.jasig.cas.client.util.Assert.ExceptionType.UNEXPECTED_FAILURE;
import static org.jasig.cas.client.util.Assert.ExceptionType.UNREACHABLE_CODE;
import static org.jasig.cas.client.util.Assert.ExceptionType.UNSUPPORTED_OPERATION;

import org.jasig.cas.client.exception.UnexpectedFailureException;
import org.jasig.cas.client.exception.UnreachableCodeException;

/**
 * @author Cruise.Xu
 *
 */
public class Assert {
	/**
     * 确保对象不为空，否则抛出<code>IllegalArgumentException</code>。
     */
    public static <T> T assertNotNull(T object) {
        return assertNotNull(object, null, null, (Object[]) null);
    }

    /**
     * 确保对象不为空，否则抛出<code>IllegalArgumentException</code>。
     */
    public static <T> T assertNotNull(T object, String message, Object... args) {
        return assertNotNull(object, null, message, args);
    }

    /**
     * 确保对象不为空，否则抛出指定异常，默认为<code>IllegalArgumentException</code>。
     */
    public static <T> T assertNotNull(T object, ExceptionType exceptionType, String message, Object... args) {
        if (object == null) {
            if (exceptionType == null) {
                exceptionType = ILLEGAL_ARGUMENT;
            }

            throw exceptionType.newInstance(getMessage(message, args,
                    "[Assertion failed] - the argument is required; it must not be null"));
        }

        return object;
    }

    /**
     * 确保对象为空，否则抛出<code>IllegalArgumentException</code>。
     */
    public static <T> T assertNull(T object) {
        return assertNull(object, null, null, (Object[]) null);
    }

    /**
     * 确保对象为空，否则抛出<code>IllegalArgumentException</code>。
     */
    public static <T> T assertNull(T object, String message, Object... args) {
        return assertNull(object, null, message, args);
    }

    /**
     * 确保对象为空，否则抛出指定异常，默认为<code>IllegalArgumentException</code>。
     */
    public static <T> T assertNull(T object, ExceptionType exceptionType, String message, Object... args) {
        if (object != null) {
            if (exceptionType == null) {
                exceptionType = ILLEGAL_ARGUMENT;
            }

            throw exceptionType.newInstance(getMessage(message, args,
                    "[Assertion failed] - the object argument must be null"));
        }

        return object;
    }

    /**
     * 确保表达式为真，否则抛出<code>IllegalArgumentException</code>。
     */
    public static void assertTrue(boolean expression) {
        assertTrue(expression, null, null, (Object[]) null);
    }

    /**
     * 确保表达式为真，否则抛出<code>IllegalArgumentException</code>。
     */
    public static void assertTrue(boolean expression, String message, Object... args) {
        assertTrue(expression, null, message, args);
    }

    /**
     * 确保表达式为真，否则抛出指定异常，默认为<code>IllegalArgumentException</code>。
     */
    public static void assertTrue(boolean expression, ExceptionType exceptionType, String message, Object... args) {
        if (!expression) {
            if (exceptionType == null) {
                exceptionType = ILLEGAL_ARGUMENT;
            }

            throw exceptionType.newInstance(getMessage(message, args, "[Assertion failed] - the expression must be true"));
        }
    }

    /**
     * 不可能到达的代码。
     */
    public static <T> T unreachableCode() {
        unreachableCode(null, (Object[]) null);
        return null;
    }

    /**
     * 不可能到达的代码。
     */
    public static <T> T unreachableCode(String message, Object... args) {
        throw UNREACHABLE_CODE.newInstance(getMessage(message, args, "[Assertion failed] - the code is expected as unreachable"));
    }

    /**
     * 不可能发生的异常。
     */
    public static <T> T unexpectedException(Throwable e) {
        unexpectedException(e, null, (Object[]) null);
        return null;
    }

    /**
     * 不可能发生的异常。
     */
    public static <T> T unexpectedException(Throwable e, String message, Object... args) {
        RuntimeException exception = UNEXPECTED_FAILURE.newInstance(getMessage(message, args,
    "[Assertion failed] - unexpected exception is thrown"));

        exception.initCause(e);

        throw exception;
    }

    /**
     * 未预料的失败。
     */
    public static <T> T fail() {
        fail(null, (Object[]) null);
        return null;
    }

    /**
     * 未预料的失败。
     */
    public static <T> T fail(String message, Object... args) {
        throw UNEXPECTED_FAILURE.newInstance(getMessage(message, args, "[Assertion failed] - unexpected failure"));
    }

    /**
     * 不支持的操作。
     */
    public static <T> T unsupportedOperation() {
        unsupportedOperation(null, (Object[]) null);
        return null;
    }

    /**
     * 不支持的操作。
     */
    public static <T> T unsupportedOperation(String message, Object... args) {
        throw UNSUPPORTED_OPERATION.newInstance(getMessage(message, args,
                "[Assertion failed] - unsupported operation or unimplemented function"));
    }

    /**
     * 取得带参数的消息。
     */
    private static String getMessage(String message, Object[] args, String defaultMessage) {
        if (message == null) {
            message = defaultMessage;
        }

        if (args == null || args.length == 0) {
            return message;
        }

        return String.format(message, args);
    }

    /**
     * Assertion错误类型。
     */
    public static enum ExceptionType {
        ILLEGAL_ARGUMENT {
            @Override
            RuntimeException newInstance(String message) {
                return new IllegalArgumentException(message);
            }
        },

        ILLEGAL_STATE {
            @Override
            RuntimeException newInstance(String message) {
                return new IllegalStateException(message);
            }
        },

        NULL_POINT {
            @Override
            RuntimeException newInstance(String message) {
                return new NullPointerException(message);
            }
        },

        UNREACHABLE_CODE {
            @Override
            RuntimeException newInstance(String message) {
                return new UnreachableCodeException(message);
            }
        },

        UNEXPECTED_FAILURE {
            @Override
            RuntimeException newInstance(String message) {
                return new UnexpectedFailureException(message);
            }
        },

        UNSUPPORTED_OPERATION {
            @Override
            RuntimeException newInstance(String message) {
                return new UnsupportedOperationException(message);
            }
        };

        abstract RuntimeException newInstance(String message);
    }
}