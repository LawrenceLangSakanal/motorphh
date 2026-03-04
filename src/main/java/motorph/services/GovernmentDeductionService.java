package motorph.services;

public class GovernmentDeductionService implements GovernmentDeductible {

    @Override
    public double computeSSS(double grossPay) {
        if (grossPay <= 3250) return 135.00;
        if (grossPay <= 3750) return 157.50;
        if (grossPay <= 4250) return 180.00;
        if (grossPay <= 4750) return 202.50;
        if (grossPay <= 5250) return 225.00;
        if (grossPay <= 5750) return 247.50;
        if (grossPay <= 6250) return 270.00;
        if (grossPay <= 6750) return 292.50;
        if (grossPay <= 7250) return 315.00;
        if (grossPay <= 7750) return 337.50;
        if (grossPay <= 8250) return 360.00;
        if (grossPay <= 8750) return 382.50;
        if (grossPay <= 9250) return 405.00;
        if (grossPay <= 9750) return 427.50;
        if (grossPay <= 10250) return 450.00;
        if (grossPay <= 10750) return 472.50;
        if (grossPay <= 11250) return 495.00;
        if (grossPay <= 11750) return 517.50;
        if (grossPay <= 12250) return 540.00;
        if (grossPay <= 12750) return 562.50;
        if (grossPay <= 13250) return 585.00;
        if (grossPay <= 13750) return 607.50;
        if (grossPay <= 14250) return 630.00;
        if (grossPay <= 14750) return 652.50;
        if (grossPay <= 15250) return 675.00;
        if (grossPay <= 15750) return 697.50;
        if (grossPay <= 16250) return 720.00;
        if (grossPay <= 16750) return 742.50;
        if (grossPay <= 17250) return 765.00;
        if (grossPay <= 17750) return 787.50;
        if (grossPay <= 18250) return 810.00;
        if (grossPay <= 18750) return 832.50;
        if (grossPay <= 19250) return 855.00;
        if (grossPay <= 19750) return 877.50;
        if (grossPay <= 20250) return 900.00;
        if (grossPay <= 24750) return 1125.00;
        return 1125.00;
    }

    @Override
    public double computePhilHealth(double grossPay) {
        double premium = grossPay * 0.05;
        double employeeShare = premium / 2.0;
        return Math.min(employeeShare, 900.0);
    }

    @Override
    public double computePagIBIG(double grossPay) {
        double rate = grossPay > 1500 ? 0.02 : 0.01;
        double contribution = grossPay * rate;
        return Math.min(contribution, 100.0);
    }

    @Override
    public double computeWithholdingTax(double taxableIncome) {
        if (taxableIncome <= 20833) return 0;
        if (taxableIncome <= 33333) return (taxableIncome - 20833) * 0.15;
        if (taxableIncome <= 66667) return 1875.00 + (taxableIncome - 33333) * 0.20;
        if (taxableIncome <= 166667) return 8541.80 + (taxableIncome - 66667) * 0.25;
        if (taxableIncome <= 666667) return 33541.80 + (taxableIncome - 166667) * 0.30;
        return 183541.80 + (taxableIncome - 666667) * 0.35;
    }
}
