package systems.achilles.tax.cli.service;

import nl.altindag.console.ConsoleCaptor;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import systems.achilles.tax.cli.dto.CSVResponseDTO;
import systems.achilles.tax.cli.dto.TaxResponseDTO;
import systems.achilles.tax.cli.exception.TaxException;
import systems.achilles.tax.cli.model.TaxRequestModel;
import systems.achilles.tax.cli.model.TaxResponseModel;
import systems.achilles.tax.cli.service.impl.CSVServiceImp;
import systems.achilles.tax.cli.service.impl.TaxServiceImpl;
import systems.achilles.tax.cli.util.ParseConsoleLog;
import systems.achilles.tax.cli.validation.errorcode.TaxErrorDescCode;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;

public class TaxAndCsvServiceTest {

    private CSVService csvService;

    private TaxService taxService;

    ConsoleCaptor consoleCaptor = new ConsoleCaptor();

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        csvService = mock(CSVServiceImp.class);
        taxService = mock(TaxServiceImpl.class);
    }

    @Test
    public void testGenCSVAndProcess_success() {

        Mockito.when(csvService.csvProcessor(Mockito.anyString())).thenReturn(
                CSVResponseDTO.builder()
                        .taxRequestModels(taxRequestModelList())
                        .build()
        );

        Mockito.when(taxService.taxProcess(Matchers.anyListOf(TaxRequestModel.class))).thenReturn(
                TaxResponseDTO.builder()
                        .responseDTOS(taxResponse())
                        .build()
        );

        CSVResponseDTO csvResponseDTO = csvService.csvProcessor("transaction-30lines.csv");
        TaxResponseDTO taxResponseDTO = taxService.taxProcess(csvResponseDTO.getTaxRequestModels());

        Assertions.assertNotNull(taxResponseDTO);
        Assertions.assertEquals(3, taxResponseDTO.getResponseDTOS().size());
    }

    @Test (expected = TaxException.class)
    public void testGenCSVAndProcess_csvBlank() {

        Mockito.when(csvService.csvProcessor(Mockito.anyString())).thenThrow(
                new TaxException("fileLocation can't be blank", TaxErrorDescCode.DATA_NOT_FOUND)
        );

        csvService.csvProcessor("");
        Assertions.assertTrue(ParseConsoleLog.consoleLogToBoolean(consoleCaptor.getStandardOutput(),
                "fileLocation can't be blank"));
    }

    @Test (expected = TaxException.class)
    public void testGenCSVAndProcess_taxRequestNull() {

        Mockito.when(csvService.csvProcessor(Mockito.anyString())).thenReturn(
                CSVResponseDTO.builder()
                        .taxRequestModels(null)
                        .build()
        );

        Mockito.when(taxService.taxProcess(Matchers.anyListOf(TaxRequestModel.class))).thenThrow(
                new TaxException("TaxRequestModels can't be null", TaxErrorDescCode.PARAMETER_ERROR)
        );

        CSVResponseDTO csvResponseDTO = csvService.csvProcessor("test");
        taxService.taxProcess(csvResponseDTO.getTaxRequestModels());
        Assertions.assertTrue(ParseConsoleLog.consoleLogToBoolean(consoleCaptor.getStandardOutput(),
                "TaxRequestModels can't be null"));
    }

    public List<TaxRequestModel> taxRequestModelList () {

        List<TaxRequestModel> list = new ArrayList<>();
        list.add(TaxRequestModel.builder()
                        .customerId("3077")
                        .taxType("GST")
                        .timestamp("2021-11-24T16:32:57.533")
                        .invoiceNo("2020/11/baaaaabab-98609")
                        .amount("647.43")
                .build());

        list.add(TaxRequestModel.builder()
                .customerId("4527")
                .taxType("CAPITOL_GAIN")
                .timestamp("2021-11-24T17:32:57.534")
                .invoiceNo("2010/11/aaabababb-62312")
                .amount("882.49")
                .build());

        list.add(TaxRequestModel.builder()
                .customerId("3201")
                .taxType("PAYROLL")
                .timestamp("2021-11-24T18:32:57.535")
                .invoiceNo("2010/11/aaabababb-62312")
                .amount("511.99")
                .build());

        return list;
    }

    private List<TaxResponseModel> taxResponse() {
        List<TaxResponseModel> list = new ArrayList<>();

        list.add(TaxResponseModel.builder()
                .customerId("3077")
                .taxType("GST")
                .amount("647.43")
                .build());

        list.add(TaxResponseModel.builder()
                .customerId("4527")
                .taxType("CAPITOL_GAIN")
                .amount("882.49")
                .build());

        list.add(TaxResponseModel.builder()
                .customerId("3201")
                .taxType("PAYROLL")
                .invoiceNo("2010/11/aaabababb-62312")
                .amount("511.99")
                .build());
        return list;
    }

}
