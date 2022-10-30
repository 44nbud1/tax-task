package systems.achilles.tax.cli.service.impl;


import systems.achilles.tax.cli.dto.TaxResponseDTO;
import systems.achilles.tax.cli.model.TaxRequestModel;
import systems.achilles.tax.cli.service.TaxService;
import systems.achilles.tax.cli.service.innerservice.TaxInnerService;
import systems.achilles.tax.cli.service.innerservice.impl.TaxInnerServiceImpl;
import systems.achilles.tax.cli.util.process.ProcessStd;
import systems.achilles.tax.cli.validation.ValidationUtil;
import systems.achilles.tax.cli.validation.errorcode.TaxErrorDescCode;

import java.util.List;

import static systems.achilles.tax.cli.validation.template.Template.processTemplate;

/**
 * @author Aan Budi Setiawan
 */

public class TaxServiceImpl implements TaxService {

    private final TaxInnerService taxInnerService = new TaxInnerServiceImpl();

    @Override
    public TaxResponseDTO taxProcess(List<TaxRequestModel> taxRequestModels) {

        TaxResponseDTO taxResponse = new TaxResponseDTO();

        processTemplate(new ProcessStd() {

            @Override
            public void checkParameter() {
                ValidationUtil.NOT_NUL(taxRequestModels, TaxErrorDescCode.PARAMETER_ERROR, "TaxRequestModels can't be null");
            }

            @Override
            public void process() {
                taxResponse.setResponseDTOS(
                        taxInnerService.taxProcessor(taxRequestModels)
                );
            }
        });
        return taxResponse;
    }
}