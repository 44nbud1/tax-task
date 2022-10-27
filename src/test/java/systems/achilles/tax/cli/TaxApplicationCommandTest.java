package systems.achilles.tax.cli;

import org.junit.Test;
import systems.achilles.tax.cli.bootstrap.TaxApplicationCommand;

public class TaxApplicationCommandTest {

    @Test
    public void mainTest_moreThan3Args() {
        TaxApplicationCommand.main(new String[]{"", "", "", ""});
    }

    @Test
    public void mainTest_lessThan3Args() {
        TaxApplicationCommand.main(new String[]{"", ""});
    }

    @Test
    public void mainTest_success() {
        TaxApplicationCommand.main(new String[]{"GST", "123", "transaction-30lines.csv"});
    }
}
