package systems.achilles.tax.cli.service.innerservice;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.MockitoAnnotations;
import systems.achilles.tax.cli.model.TaxRequestModel;
import systems.achilles.tax.cli.model.TaxResponseModel;
import systems.achilles.tax.cli.service.innerservice.impl.TaxInnerServiceImpl;

import java.util.ArrayList;
import java.util.List;

public class TaxInnerServiceTest {

    private TaxInnerService taxInnerService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        taxInnerService = new TaxInnerServiceImpl();
    }

    @Test
    public void testTaskProcessor_success_request_0List() {

        List<TaxRequestModel> taxRequestModels = new ArrayList<>();

        List<TaxResponseModel> taxResponseModels = taxInnerService.taxProcessor(taxRequestModels);
        Assertions.assertNotNull(taxResponseModels);
    }

    @Test
    public void testTaskProcessor_success_request_one_request() {

        List<TaxRequestModel> taxRequestModels = new ArrayList<>();
        taxRequestModels.add(
                TaxRequestModel.builder()
                        .amount("150")
                        .invoiceNo("2020/10/ababaabba-45885")
                        .timestamp("2021-11-24T07:32:57.515")
                        .taxType("CAPITOL_GAIN")
                        .customerId("001")
                        .build()
        );

        List<TaxResponseModel> taxResponseModels = taxInnerService.taxProcessor(taxRequestModels);
        taxRequestModels.forEach(resp -> {
            Assertions.assertEquals("001",resp.getCustomerId());
            Assertions.assertEquals("CAPITOL_GAIN",resp.getTaxType());
            Assertions.assertEquals("150",resp.getAmount());
        });
    }

    @Test
    public void testTaskProcessor_success_request_multi_request_same_2_trx_type() {

        List<TaxRequestModel> taxRequestModels = new ArrayList<>();
        taxRequestModels.add(
                TaxRequestModel.builder()
                        .amount("124.57")
                        .invoiceNo("2020/10/ababaabba-45885")
                        .timestamp("2021-11-24T07:32:57.515")
                        .taxType("CAPITOL_GAIN")
                        .customerId("003")
                        .build()
                );
        taxRequestModels.add(
                TaxRequestModel.builder()
                        .amount("242.59")
                        .invoiceNo("2020/10/ababaabba-45885")
                        .timestamp("2021-11-24T07:32:57.515")
                        .taxType("PAYROLL")
                        .customerId("001")
                        .build()
        );
        taxRequestModels.add(
                TaxRequestModel.builder()
                        .amount("443.34")
                        .invoiceNo("2020/10/ababaabba-45885")
                        .timestamp("2021-11-24T07:32:57.515")
                        .taxType("CAPITOL_GAIN")
                        .customerId("002")
                        .build()
        );

        List<TaxResponseModel> taxResponseModels = taxInnerService.taxProcessor(taxRequestModels);
        Assertions.assertEquals("002",taxResponseModels.get(0).getCustomerId());
        Assertions.assertEquals("CAPITOL_GAIN",taxResponseModels.get(0).getTaxType());
        Assertions.assertEquals("487.67",taxResponseModels.get(0).getAmount());

        Assertions.assertEquals("003",taxResponseModels.get(1).getCustomerId());
        Assertions.assertEquals("CAPITOL_GAIN",taxResponseModels.get(1).getTaxType());
        Assertions.assertEquals("137.03",taxResponseModels.get(1).getAmount());

        Assertions.assertEquals("001",taxResponseModels.get(2).getCustomerId());
        Assertions.assertEquals("PAYROLL",taxResponseModels.get(2).getTaxType());
        Assertions.assertEquals("266.85",taxResponseModels.get(2).getAmount());
    }

    @Test
    public void testTaskProcessor_success_request_multi_request_same_customer_id_trx_type() {

        List<TaxRequestModel> taxRequestModels = new ArrayList<>();
        taxRequestModels.add(
                TaxRequestModel.builder()
                        .amount("124.57")
                        .invoiceNo("2020/10/ababaabba-45885")
                        .timestamp("2021-11-24T07:32:57.515")
                        .taxType("CAPITOL_GAIN")
                        .customerId("002")
                        .build()
        );
        taxRequestModels.add(
                TaxRequestModel.builder()
                        .amount("242.59")
                        .invoiceNo("2020/10/ababaabba-45885")
                        .timestamp("2021-11-24T07:32:57.515")
                        .taxType("PAYROLL")
                        .customerId("001")
                        .build()
        );
        taxRequestModels.add(
                TaxRequestModel.builder()
                        .amount("443.34")
                        .invoiceNo("2020/10/ababaabba-45885")
                        .timestamp("2021-11-24T07:32:57.515")
                        .taxType("CAPITOL_GAIN")
                        .customerId("002")
                        .build()
        );

        taxRequestModels.add(
                TaxRequestModel.builder()
                        .build()
        );

        List<TaxResponseModel> taxResponseModels = taxInnerService.taxProcessor(taxRequestModels);
        Assertions.assertEquals(2, taxResponseModels.size());
        Assertions.assertEquals("002",taxResponseModels.get(0).getCustomerId());
        Assertions.assertEquals("CAPITOL_GAIN",taxResponseModels.get(0).getTaxType());
        Assertions.assertEquals("624.70",taxResponseModels.get(0).getAmount());

        Assertions.assertEquals("001",taxResponseModels.get(1).getCustomerId());
        Assertions.assertEquals("PAYROLL",taxResponseModels.get(1).getTaxType());
        Assertions.assertEquals("266.85",taxResponseModels.get(1).getAmount());
    }
}
