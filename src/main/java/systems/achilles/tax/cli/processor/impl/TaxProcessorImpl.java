package systems.achilles.tax.cli.processor.impl;

import systems.achilles.tax.cli.dto.CSVResponseDTO;
import systems.achilles.tax.cli.dto.TaxRequestDTO;
import systems.achilles.tax.cli.dto.TaxResponseDTO;
import systems.achilles.tax.cli.enums.TaxEnum;
import systems.achilles.tax.cli.exception.TaxException;
import systems.achilles.tax.cli.processor.TaxProcessor;
import systems.achilles.tax.cli.service.CSVService;
import systems.achilles.tax.cli.service.TaxService;
import systems.achilles.tax.cli.service.impl.CSVServiceImp;
import systems.achilles.tax.cli.service.impl.TaxServiceImpl;
import systems.achilles.tax.cli.util.message.MessageUtil;
import systems.achilles.tax.cli.validation.ValidationUtil;
import systems.achilles.tax.cli.validation.errorcode.TaxErrorDescCode;

public class TaxProcessorImpl implements TaxProcessor {

    TaxService taxService = new TaxServiceImpl();

    CSVService csvService = new CSVServiceImp();
    @Override
    public void taxProcessor(TaxRequestDTO taxRequestDTO) {

        try {
            ValidationUtil.IS_TRUE(TaxEnum.contains(taxRequestDTO.getTaxType()), TaxErrorDescCode.DATA_NOT_FOUND,
                    "TaxType not found");

            CSVResponseDTO csvResponseDTO = csvService.csvProcessor(taxRequestDTO.getFileLocation());
            TaxResponseDTO taxResponseDTO = taxService.taxProcess(csvResponseDTO.getTaxRequestModels());

            taxResponseDTO.getResponseDTOS().forEach(taxRes -> {
                System.out.printf("For tax %s, customer %s has declared $%s%n",
                        taxRes.getTaxType(), taxRes.getCustomerId(), taxRes.getAmount());
            });

        } catch (TaxException exception) {
            System.out.print(exception.getMessage());
            MessageUtil.printUsage();
        }
    }
}
