package systems.achilles.tax.cli.validation.template;

import systems.achilles.tax.cli.exception.TaxException;
import systems.achilles.tax.cli.util.message.MessageUtil;
import systems.achilles.tax.cli.util.process.ProcessStd;

/**
 * @author Aan Budi Setiawan
 */

public class Template {

    public static void processTemplate(ProcessStd std) {
        try {
            std.checkParameter();
            std.process();
        } catch (TaxException ex) {
            System.out.printf("[Tax_Service]Error occur: %s", ex.getMessage());
            MessageUtil.printUsage();
        } catch (Throwable throwable) {
            System.out.printf("[Tax_Service]Error occur unknown exception: %s", throwable.getMessage());
            MessageUtil.printUsage();
            System.exit(1);
        }
    }

}