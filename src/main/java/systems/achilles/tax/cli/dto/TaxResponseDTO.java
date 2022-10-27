package systems.achilles.tax.cli.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import systems.achilles.tax.cli.model.TaxResponseModel;

import java.util.List;

/**
 * @author Aan Budi Setiawan
 */

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TaxResponseDTO {

    private List<TaxResponseModel> responseDTOS;
}
