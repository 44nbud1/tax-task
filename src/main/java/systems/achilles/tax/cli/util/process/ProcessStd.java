package systems.achilles.tax.cli.util.process;

import java.net.URISyntaxException;

/**
 * @author Aan Budi Setiawan
 */
public interface ProcessStd {
    void checkParameter();
    void process() throws URISyntaxException;
}
