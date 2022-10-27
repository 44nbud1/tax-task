package systems.achilles.tax.cli.processor;

import systems.achilles.tax.cli.dto.TaxRequestDTO;


public interface TaxProcessor {
    void taxProcessor(TaxRequestDTO taxRequestDTO);
}
