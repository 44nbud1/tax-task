package systems.achilles.tax.cli.processor;

import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import systems.achilles.tax.cli.dto.TaxRequestDTO;
import systems.achilles.tax.cli.processor.impl.TaxProcessorImpl;

public class TaxProcessorTest {

    TaxProcessor taxProcessor;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        taxProcessor = new TaxProcessorImpl();
    }

    @Test
    public void testTrxTypeNotFound() {
        taxProcessor.taxProcessor(TaxRequestDTO.builder().build());
    }

    @Test
    public void testSuccess() {
        taxProcessor.taxProcessor(TaxRequestDTO.builder()
                        .fileLocation("transaction-30lines.csv")
                        .customerId("123")
                        .taxType("GST")
                .build());
    }
}
