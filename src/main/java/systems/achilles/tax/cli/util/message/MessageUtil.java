package systems.achilles.tax.cli.util.message;

/**
 * @author Aan Budi Setiawan
 */

public class MessageUtil {

    public static void printUsage() {
        String usage = "Tax Report CLI.\n" + "\n" + "Usage:\n"
                + "  tcli (GST | PAYROLL | COMPANY_TAX | LAND_TAX | CAPITOL_GAIN) <user_id> <tax_transaction_file>\n\n"
                + "Example:\n" + "  tcli GST 123 filename.csv";

        System.out.println(usage);

    }
}
