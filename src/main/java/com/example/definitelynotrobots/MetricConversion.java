package com.example.definitelynotrobots;

/**
 * A converter between different unit types.
 */
public class MetricConversion {
    /**
     * First unit type to use.
     */
    private final String amountTypeA;
    /**
     * Second unit type to use.
     */
    private final String amountTypeB;
    /**
     * Amount to multiply A to reach B.
     */
    private final Double aToBFactor;

    /**
     * Create a metric converter between 2 unit types.
     * @param amountTypeA First unit type to use.
     * @param amountTypeB Second unit type to use.
     * @param aToBFactor Amount to multiply A to reach B.
     */
    public MetricConversion(String amountTypeA, String amountTypeB, Double aToBFactor) {
        this.amountTypeA = amountTypeA;
        this.amountTypeB = amountTypeB;
        this.aToBFactor = aToBFactor;
    }

    /**
     * Check if two unit types are applicable.
     * @param typeA First unit type to query.
     * @param typeB Second unit type to query.
     * @return If both items exist in the converter.
     */
    public Boolean isApplicableTypes(String typeA, String typeB) {
        Boolean hasTypeA = typeA.equals(amountTypeA) | typeA.equals(amountTypeB);
        Boolean hasTypeB = typeB.equals(amountTypeA) | typeB.equals(amountTypeB);
        return hasTypeA & hasTypeB;
    }

    /**
     * Check if two unit types are applicable.
     * @param mainValue First value with the listed returnType.
     * @param secondaryValue Second value with the secondary returnType.
     * @param returnType Type of unit to return.
     * @return The value of the result in the form of returnType.
     */
    public Double addValues(Double mainValue, Double secondaryValue, String returnType) {
        if(returnType.equals(amountTypeA)) return mainValue + secondaryValue / aToBFactor;
        if(returnType.equals(amountTypeB)) return mainValue * aToBFactor + secondaryValue;
        return 0d;
    }
}