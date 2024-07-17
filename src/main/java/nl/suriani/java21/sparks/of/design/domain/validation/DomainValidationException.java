package nl.suriani.java21.sparks.of.design.domain.validation;

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
