package systems.achilles.tax.cli.exception;

import systems.achilles.tax.cli.validation.errorcode.BaseErrorDesc;

/**
 * @author Aan Budi Setiawan
 */

public class TaxException extends BaseException{

    private BaseErrorDesc baseErrorDesc;

    public TaxException(String message, BaseErrorDesc baseErrorDesc) {
        super(message);
        this.baseErrorDesc = baseErrorDesc;
    }

    @Override
    public void setTaxErrorDec(final BaseErrorDesc errorDec) {
        this.baseErrorDesc = new BaseErrorDesc() {
            @Override
            public String getCode() {
                return errorDec.getCode();
            }

            @Override
            public String getMessage() {
                return errorDec.getMessage();
            }

            @Override
            public String getErrorLevel() {
                return errorDec.getErrorLevel();
            }
        };
    }
}