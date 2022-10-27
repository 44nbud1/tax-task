package systems.achilles.tax.cli.model;

import lombok.*;

/**
 * @author Aan Budi Setiawan
 */

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class TaxRequestModel {

    private String customerId;

    private String invoiceNo;

    private String timestamp;

    private String taxType;

    private String amount;
}
