package systems.achilles.tax.cli.validation.errorcode;

/**
 * @author Aan Budi Setiawan
 */
public enum TaxErrorDescCode implements BaseErrorDesc {

    PARAMETER_ERROR("001", "Parameter is error", ""),
    DATA_NOT_FOUND("002", "Data is not found", ""),
    ;

    private String code;

    private String message;

    private String errorLevel;

    TaxErrorDescCode(String code, String message, String errorLevel) {
        this.code = code;
        this.message = message;
        this.errorLevel = errorLevel;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public String getErrorLevel() {
        return errorLevel;
    }

}
