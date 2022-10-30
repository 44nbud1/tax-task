package systems.achilles.tax.cli.processor;

import nl.altindag.console.ConsoleCaptor;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.MockitoAnnotations;
import systems.achilles.tax.cli.dto.TaxRequestDTO;
import systems.achilles.tax.cli.processor.impl.TaxProcessorImpl;
import systems.achilles.tax.cli.util.ParseConsoleLog;

public class TaxProcessorTest {

    TaxProcessor taxProcessor;

    ConsoleCaptor consoleCaptor = new ConsoleCaptor();

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        taxProcessor = new TaxProcessorImpl();
    }

    @Test
    public void testTrxTypeNotFound() {
        taxProcessor.taxProcessor(TaxRequestDTO.builder().build());
        Assertions.assertTrue(ParseConsoleLog.consoleLogToBoolean(consoleCaptor.getStandardOutput(),
                "TaxType not found"));
    }

    @Test (expected = NullPointerException.class)
    public void testCSVBlank() {
        taxProcessor.taxProcessor(TaxRequestDTO.builder()
                .taxType("GST")
                .fileLocation("")
                .build());
        Assertions.assertTrue(ParseConsoleLog.consoleLogToBoolean(consoleCaptor.getStandardOutput(),
                "fileLocation can't be blank"));
    }

    @Test
    public void testSuccess() {
        taxProcessor.taxProcessor(TaxRequestDTO.builder()
                        .fileLocation("transaction-30lines.csv")
                        .customerId("123")
                        .taxType("GST")
                .build());

        Assertions.assertEquals(23, consoleCaptor.getStandardOutput().size());
    }
}
