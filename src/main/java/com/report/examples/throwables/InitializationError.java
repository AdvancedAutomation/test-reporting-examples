package com.report.examples.throwables;

public class InitializationError extends Error {
    /**
     * Default error message.
     */
    private static final String MESSAGE = "Error: file initialization failed.";

    /**
     * Default constructor.
     */
    public InitializationError() {
        super(MESSAGE);
    }

    /**
     * Constructor, change the default message.
     *
     * @param message to change
     */
    public InitializationError(final String message) {
        super(message);
    }

    /**
     * Constructs a new exception specified with a detail message.
     *
     * @param throwable new throwable
     */
    public InitializationError(final Throwable throwable) {
        super(MESSAGE, throwable);
    }

    /**
     * Constructs a new exception with the specified detail message.
     *
     * @param message   specified
     * @param throwable new throwable
     */
    public InitializationError(final String message, final Throwable throwable) {
        super(message, throwable);
    }
}
