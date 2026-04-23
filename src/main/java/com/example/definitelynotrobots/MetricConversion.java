package com.example.definitelynotrobots;

class MetricConversion {
    private final String amountTypeA;
    private final String amountTypeB;
    private final Double aToBFactor;

    public MetricConversion(String amountTypeA, String amountTypeB, Double aToBFactor) {
        this.amountTypeA = amountTypeA;
        this.amountTypeB = amountTypeB;
        this.aToBFactor = aToBFactor;
    }

    public Boolean isApplicableTypes(String typeA, String typeB) {
        Boolean hasTypeA = typeA.equals(amountTypeA) | typeA.equals(amountTypeB);
        Boolean hasTypeB = typeB.equals(amountTypeA) | typeB.equals(amountTypeB);
        return hasTypeA & hasTypeB;
    }

    public Double addValues(Double mainValue, Double secondaryValue, String returnType) {
        if(returnType.equals(amountTypeA)) return mainValue + secondaryValue / aToBFactor;
        if(returnType.equals(amountTypeB)) return mainValue + secondaryValue * aToBFactor;
        return 0d;
    }
}