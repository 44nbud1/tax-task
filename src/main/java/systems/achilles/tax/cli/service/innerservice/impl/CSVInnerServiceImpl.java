package systems.achilles.tax.cli.service.innerservice.impl;

import systems.achilles.tax.cli.bootstrap.TaxApplicationCommand;
import systems.achilles.tax.cli.exception.TaxException;
import systems.achilles.tax.cli.model.TaxRequestModel;
import systems.achilles.tax.cli.service.innerservice.CSVInnerService;
import systems.achilles.tax.cli.validation.errorcode.TaxErrorDescCode;

import java.io.BufferedReader;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Aan Budi Setiawan
 */

public class CSVInnerServiceImpl implements CSVInnerService {

    @Override
    public List<TaxRequestModel> csvProcessor(String fileLocation) throws URISyntaxException {

        List<TaxRequestModel> taxRequestModels = new ArrayList<>();
        URL resource = TaxApplicationCommand.class.getClassLoader().getResource(fileLocation);

        if (resource == null) {
            throw new TaxException("File is not found", TaxErrorDescCode.PARAMETER_ERROR);
        }

        Path file = Paths.get(resource.toURI()).toAbsolutePath();
        try (BufferedReader br = Files.newBufferedReader(file, StandardCharsets.US_ASCII)) {
            String line = br.readLine();

            while (line != null) {
                String[] attributes = line.replaceAll("\\s", "").split(",");
                TaxRequestModel taxRequestModel = createTaxRequest(attributes);
                taxRequestModels.add(taxRequestModel);
                line = br.readLine();
            }
        } catch (Exception ioe) {
            throw new TaxException("Failed get csv files, detail: " + ioe.getMessage(), TaxErrorDescCode.DATA_NOT_FOUND);
        }

        return taxRequestModels;
    }

    private TaxRequestModel createTaxRequest(String[] attributes) {

        if (attributes.length != 5) {
            return new TaxRequestModel();
        }

        return TaxRequestModel.builder()
                    .customerId(attributes[0])
                    .invoiceNo(attributes[1])
                    .timestamp(attributes[2])
                    .amount(attributes[3])
                    .taxType(attributes[4])
                .build();
    }
}
