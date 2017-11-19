package com.egen.rules;

/**
 * Created by vkuruval on 11/18/17.
 */
public interface MetricsRule {

    enum RuleType {OVER_WEIGHT, UNDER_WEIGHT}



    int baseWeight = 140;

    boolean when();
    void then();
}
