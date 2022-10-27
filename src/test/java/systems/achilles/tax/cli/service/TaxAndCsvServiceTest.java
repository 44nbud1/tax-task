package systems.achilles.tax.cli.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.MockitoAnnotations;
import systems.achilles.tax.cli.dto.CSVResponseDTO;
import systems.achilles.tax.cli.dto.TaxResponseDTO;
import systems.achilles.tax.cli.service.impl.CSVServiceImp;
import systems.achilles.tax.cli.service.impl.TaxServiceImpl;

public class TaxAndCsvServiceTest {

    private CSVService csvService;

    private TaxService taxService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        csvService = new CSVServiceImp();
        taxService = new TaxServiceImpl();
    }

    @Test
    public void testGenCSVAndProcess_success() {

        CSVResponseDTO csvResponseDTO = csvService.csvProcessor("transaction-30lines.csv");
        TaxResponseDTO taxResponseDTO = taxService.taxProcess(csvResponseDTO.getTaxRequestModels());

        Assertions.assertNotNull(taxResponseDTO);
        Assertions.assertEquals(23, taxResponseDTO.getResponseDTOS().size());
    }

}
