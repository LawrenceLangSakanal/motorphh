package motorph.services;

public interface GovernmentDeductible {
    double computeSSS(double grossPay);
    double computePhilHealth(double grossPay);
    double computePagIBIG(double grossPay);
    double computeWithholdingTax(double taxableIncome);
}
