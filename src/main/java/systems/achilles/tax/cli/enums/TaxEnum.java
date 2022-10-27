package systems.achilles.tax.cli.enums;

/**
 * @author Aan Budi Setiawan
 */

public enum TaxEnum {
    GST, PAYROLL, COMPANY_TAX, LAND_TAX, CAPITOL_GAIN;

    public static boolean contains(String name) {

        for(TaxEnum type: TaxEnum.values()) {
            if (type.name().equals(name)){
                return true;
            }
        }

        return false;
    }
}