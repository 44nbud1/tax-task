package systems.achilles.tax.cli.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Aan Budi Setiawan
 */

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TaxResponseModel {

    private String customerId;

    private String invoiceNo;

    private String taxType;

    private String amount;
}
