package systems.achilles.tax.cli.exception;

import systems.achilles.tax.cli.validation.errorcode.BaseErrorDesc;

/**
 * @author Aan Budi Setiawan
 */

public abstract class BaseException extends RuntimeException {

    public abstract void setTaxErrorDec(BaseErrorDesc errorDec);

    BaseException(String message) {
        super(message);
    }
    
}