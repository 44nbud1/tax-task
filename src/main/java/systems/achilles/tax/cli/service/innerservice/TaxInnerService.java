package systems.achilles.tax.cli.service.innerservice;

import systems.achilles.tax.cli.model.TaxRequestModel;
import systems.achilles.tax.cli.model.TaxResponseModel;

import java.util.List;

/**
 * @author Aan Budi Setiawan
 */
public interface TaxInnerService {

    List<TaxResponseModel> taxProcessor(List<TaxRequestModel> taxRequestModels);

}
