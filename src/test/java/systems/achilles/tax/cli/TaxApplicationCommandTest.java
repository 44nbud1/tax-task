package systems.achilles.tax.cli;

import nl.altindag.console.ConsoleCaptor;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import systems.achilles.tax.cli.bootstrap.TaxApplicationCommand;
import systems.achilles.tax.cli.util.ParseConsoleLog;

public class TaxApplicationCommandTest {

    ConsoleCaptor consoleCaptor = new ConsoleCaptor();

    @Test
    public void mainTest_moreThan3Args() {
        TaxApplicationCommand.main(new String[]{"", "", "", ""});
        Assertions.assertTrue(ParseConsoleLog.consoleLogToBoolean(consoleCaptor.getStandardOutput(),
                "Request invalid, request length less than 3 or more than 3"));
    }

    @Test
    public void mainTest_lessThan3Args() {
        TaxApplicationCommand.main(new String[]{"", ""});
        Assertions.assertTrue(ParseConsoleLog.consoleLogToBoolean(consoleCaptor.getStandardOutput(),
                "Request invalid, request length less than 3 or more than 3"));
    }

    @Test
    public void mainTest_success() {
        TaxApplicationCommand.main(new String[]{"GST", "123", "transaction-30lines.csv"});
        // length of result
        Assertions.assertEquals(23, consoleCaptor.getStandardOutput().size());
    }
}
