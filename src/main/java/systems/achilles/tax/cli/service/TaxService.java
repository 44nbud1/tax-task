package systems.achilles.tax.cli.service;

import systems.achilles.tax.cli.dto.TaxResponseDTO;
import systems.achilles.tax.cli.model.TaxRequestModel;

import java.util.List;

/**
 * @author Aan Budi Setiawan
 */
public interface TaxService {

    TaxResponseDTO taxProcess (List<TaxRequestModel> taxRequestDTOS);

}
