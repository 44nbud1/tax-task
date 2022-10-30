package systems.achilles.tax.cli.service;

import nl.altindag.console.ConsoleCaptor;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.MockitoAnnotations;
import systems.achilles.tax.cli.dto.CSVResponseDTO;
import systems.achilles.tax.cli.dto.TaxResponseDTO;
import systems.achilles.tax.cli.service.impl.CSVServiceImp;
import systems.achilles.tax.cli.service.impl.TaxServiceImpl;
import systems.achilles.tax.cli.util.ParseConsoleLog;

public class TaxAndCsvServiceTest {

    private CSVService csvService;

    private TaxService taxService;

    ConsoleCaptor consoleCaptor = new ConsoleCaptor();

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

    @Test
    public void testGenCSVAndProcess_csvBlank() {

        CSVResponseDTO csvResponseDTO = csvService.csvProcessor("");
        TaxResponseDTO taxResponseDTO = taxService.taxProcess(csvResponseDTO.getTaxRequestModels());
        Assertions.assertTrue(ParseConsoleLog.consoleLogToBoolean(consoleCaptor.getStandardOutput(),
                "Error occur: fileLocation can't be blank"));
    }

    @Test
    public void testGenCSVAndProcess_taxRequestNull() {

        CSVResponseDTO csvResponseDTO = csvService.csvProcessor("transaction-30lines.csv");
        csvResponseDTO.setTaxRequestModels(null);
        TaxResponseDTO taxResponseDTO = taxService.taxProcess(csvResponseDTO.getTaxRequestModels());
        Assertions.assertTrue(ParseConsoleLog.consoleLogToBoolean(consoleCaptor.getStandardOutput(),
                "Error occur: TaxRequestModels can't be null"));
    }

}
