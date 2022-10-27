package systems.achilles.tax.cli.service.impl;

import systems.achilles.tax.cli.dto.CSVResponseDTO;
import systems.achilles.tax.cli.service.CSVService;
import systems.achilles.tax.cli.service.innerservice.CSVInnerService;
import systems.achilles.tax.cli.service.innerservice.impl.CSVInnerServiceImpl;
import systems.achilles.tax.cli.util.process.ProcessStd;
import systems.achilles.tax.cli.validation.ValidationUtil;
import systems.achilles.tax.cli.validation.errorcode.TaxErrorDescCode;

import java.net.URISyntaxException;

import static systems.achilles.tax.cli.validation.template.Template.processTemplate;

public class CSVServiceImp implements CSVService {

    CSVInnerService csvInnerService = new CSVInnerServiceImpl();

    @Override
    public CSVResponseDTO csvProcessor(String fileLocation) {

        CSVResponseDTO responseDTO = new CSVResponseDTO();

        processTemplate(new ProcessStd() {

            @Override
            public void checkParameter() {
                ValidationUtil.NOT_BLANK(fileLocation, TaxErrorDescCode.PARAMETER_ERROR, "fileLocation can't be blank");
            }

            @Override
            public void process() throws URISyntaxException {
                responseDTO.setTaxRequestModels(
                        csvInnerService.csvProcessor(fileLocation)
                );
            }
        });

        return responseDTO;
    }
}
