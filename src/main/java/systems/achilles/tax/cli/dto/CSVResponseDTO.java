package systems.achilles.tax.cli.dto;

import lombok.*;
import systems.achilles.tax.cli.model.TaxRequestModel;

import java.util.List;

/**
 * @author Aan Budi Setiawan
 */

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class CSVResponseDTO {
    List<TaxRequestModel> taxRequestModels;
}
