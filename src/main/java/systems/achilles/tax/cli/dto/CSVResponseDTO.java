package systems.achilles.tax.cli.dto;

import lombok.*;
import systems.achilles.tax.cli.model.TaxRequestModel;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class CSVResponseDTO {
    List<TaxRequestModel> taxRequestModels;
}
