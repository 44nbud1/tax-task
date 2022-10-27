package systems.achilles.tax.cli.processor;

import systems.achilles.tax.cli.dto.TaxRequestDTO;

/**
 * @author Aan Budi Setiawan
 */

public interface TaxProcessor {
    void taxProcessor(TaxRequestDTO taxRequestDTO);
}
