package systems.achilles.tax.cli.service.innerservice;

import systems.achilles.tax.cli.model.TaxRequestModel;

import java.net.URISyntaxException;
import java.util.List;

/**
 * @author Aan Budi Setiawan
 */

public interface CSVInnerService {
    List<TaxRequestModel> csvProcessor(String fileLocation) throws URISyntaxException;
}
