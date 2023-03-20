package project.goorm.authentication.business.core.domain.common.error;

import java.util.Objects;

public final class ErrorField extends RuntimeException {

    private final String fieldName;
    private final Object value;

    private ErrorField(String fieldName, Object value) {
        this.fieldName = fieldName;
        this.value = convertValue(value);
    }

    private Object convertValue(Object value) {
        if (Objects.isNull(value)) {
            return "Null";
        }
        if (value == "" || value == " ") {
            return "''";
        }
        return value;
    }

    public static ErrorField of(String fieldName, Object value) {
        return new ErrorField(fieldName, value);
    }

    public String getFieldName() {
        return fieldName;
    }

    public Object getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ErrorField)) return false;
        ErrorField that = (ErrorField) o;
        return Objects.equals(getFieldName(), that.getFieldName()) && Objects.equals(getValue(), that.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFieldName(), getValue());
    }

    @Override
    public String toString() {
        return String.format("field: %s, value: %s", fieldName, value);
    }
}
