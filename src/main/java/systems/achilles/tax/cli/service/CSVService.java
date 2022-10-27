package systems.achilles.tax.cli.service;

import systems.achilles.tax.cli.dto.CSVResponseDTO;

public interface CSVService {
    CSVResponseDTO csvProcessor(String fileLocation);
}
