package systems.achilles.tax.cli.validation;

import org.apache.commons.lang3.StringUtils;
import systems.achilles.tax.cli.exception.TaxException;
import systems.achilles.tax.cli.validation.errorcode.BaseErrorDesc;

/**
 * @author Aan Budi Setiawan
 */

public class ValidationUtil {

    private static void checkParam (Boolean param, BaseErrorDesc baseErrorDesc, String msg) {
        if (!param) {
            throw new TaxException(msg, baseErrorDesc);
        }
    }

    public static void NOT_NUL(Object param, BaseErrorDesc baseErrorDesc, String msg) {
        checkParam(param != null, baseErrorDesc, msg);
    }

    public static void NOT_BLANK(String param, BaseErrorDesc baseErrorDesc, String msg) {
        checkParam(StringUtils.isNotBlank(param), baseErrorDesc, msg);
    }

    public static void IS_TRUE(boolean param, BaseErrorDesc baseErrorDesc, String msg) {
        checkParam(param, baseErrorDesc, msg);
    }

}