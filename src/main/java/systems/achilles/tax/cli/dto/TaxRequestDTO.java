package systems.achilles.tax.cli.dto;

import lombok.*;

/**
 * @author Aan Budi Setiawan
 */

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class TaxRequestDTO {

    private String taxType;

    private String customerId;

    private String fileLocation;

}
