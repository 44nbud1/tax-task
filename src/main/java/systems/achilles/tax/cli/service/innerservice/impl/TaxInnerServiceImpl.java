package systems.achilles.tax.cli.service.innerservice.impl;


import systems.achilles.tax.cli.model.TaxRequestModel;
import systems.achilles.tax.cli.model.TaxResponseModel;
import systems.achilles.tax.cli.service.innerservice.TaxInnerService;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

/**
 * @author Aan Budi Setiawan
 */

public class TaxInnerServiceImpl implements TaxInnerService {

    @Override
    public List<TaxResponseModel> taxProcessor(List<TaxRequestModel> taxRequestModels) {

        List<TaxResponseModel> taxResponseModels = new ArrayList<>();

        taxRequestModels = taxRequestModels.stream()
                .filter(t -> t.getTaxType() != null && t.getCustomerId() != null).collect(Collectors.toList());
        taxRequestModels.sort(Comparator.comparing(TaxRequestModel::getTaxType).thenComparing(TaxRequestModel::getCustomerId));

        if (taxRequestModels.size() == 1) {
            getTax(taxRequestModels.subList( 0, 1), taxResponseModels);
            return taxResponseModels;
        }

        int startIdx = 0;
        for (int i = 0; i < taxRequestModels.size()-1; i++) {

            if (!(taxRequestModels.get(i).getCustomerId().equals(taxRequestModels.get(i + 1).getCustomerId())))  {
                getTax(taxRequestModels.subList(startIdx, i+1), taxResponseModels);
                startIdx = i+1;
            }

            if (taxRequestModels.size()-2 == i) {
                getTax(taxRequestModels.subList(startIdx, i+2), taxResponseModels);
            }
        }

        return taxResponseModels;
    }

    private void getTax(List<TaxRequestModel> taxRequestModels, List<TaxResponseModel> result) {

        TaxResponseModel taxResponseModel = new TaxResponseModel();
        AtomicReference<Double> amt = new AtomicReference<>(0.00d);
        taxRequestModels.forEach(models -> {
            amt.updateAndGet(v -> (Double) (v + Double.parseDouble(models.getAmount())));
            taxResponseModel.setTaxType(models.getTaxType());
            taxResponseModel.setCustomerId(models.getCustomerId());
            taxResponseModel.setAmount(getAmountAfterTax(amt.get()));
        });
        amt.set(0.00d);
        result.add(taxResponseModel);
    }

    private String getAmountAfterTax(Double amount) {
        return String.format("%.2f", (amount * 0.1) + amount);
    }
}