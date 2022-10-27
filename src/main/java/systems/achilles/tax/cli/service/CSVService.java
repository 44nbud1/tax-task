package systems.achilles.tax.cli.service;

import systems.achilles.tax.cli.dto.CSVResponseDTO;

/**
 * @author Aan Budi Setiawan
 */

public interface CSVService {
    CSVResponseDTO csvProcessor(String fileLocation);
}
