package nl.suriani.java17.sparks.of.design.inventory.validation;

public class DomainValidationException extends RuntimeException {
    public DomainValidationException() {
    }

    public DomainValidationException(String message) {
        super(message);
    }

    public DomainValidationException(String message, Throwable cause) {
        super(message, cause);
    }
}
