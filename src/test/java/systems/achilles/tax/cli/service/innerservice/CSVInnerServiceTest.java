package systems.achilles.tax.cli.service.innerservice;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.MockitoAnnotations;
import systems.achilles.tax.cli.exception.TaxException;
import systems.achilles.tax.cli.model.TaxRequestModel;
import systems.achilles.tax.cli.service.innerservice.impl.CSVInnerServiceImpl;

import java.net.URISyntaxException;
import java.util.List;

public class CSVInnerServiceTest {

    private CSVInnerService csvInnerService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        csvInnerService = new CSVInnerServiceImpl();
    }

    @Test (expected = TaxException.class)
    public void testCsvProduce_location_not_found() throws URISyntaxException {

        csvInnerService.csvProcessor("not_found");
    }

    @Test
    public void testCsvProduce_success() throws URISyntaxException {

        List<TaxRequestModel> taxRequestModels = csvInnerService.csvProcessor("transaction-30lines.csv");
        Assertions.assertNotNull(taxRequestModels);
        Assertions.assertEquals(31, taxRequestModels.size());
    }

}
