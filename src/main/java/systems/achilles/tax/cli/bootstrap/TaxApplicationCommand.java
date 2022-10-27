package systems.achilles.tax.cli.bootstrap;

import picocli.CommandLine;
import picocli.CommandLine.Parameters;
import systems.achilles.tax.cli.dto.TaxRequestDTO;
import systems.achilles.tax.cli.processor.TaxProcessor;
import systems.achilles.tax.cli.processor.impl.TaxProcessorImpl;
import systems.achilles.tax.cli.util.message.MessageUtil;

import java.util.Arrays;

public class TaxApplicationCommand implements Runnable {

    TaxProcessor taxProcessor = new TaxProcessorImpl();

    @Parameters(index = "0")
    private String taxType;

    @Parameters(index = "1")
    private String customerId;

    @Parameters(index = "2")
    private String fileLocation;

    public static void main(String[] args) {

        if (args.length == 3) {
            CommandLine.run(new TaxApplicationCommand(), args);
        } else {
            System.out.printf("Request invalid, request length less than 3 or more than 3, " +
                    "request: %s length: %s", Arrays.toString(args), args.length);

            MessageUtil.printUsage();
        }
    }

    @Override
    public void run() {
        taxProcessor.taxProcessor(TaxRequestDTO.builder()
                    .taxType(taxType)
                    .customerId(customerId)
                    .fileLocation(fileLocation)
                .build()
        );
    }
}
