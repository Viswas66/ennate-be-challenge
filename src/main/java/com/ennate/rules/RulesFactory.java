package com.ennate.rules;

import com.ennate.model.Metric;
import com.ennate.rules.MetricsRule.RuleType;

/**
 * Created by vkuruval on 11/18/17.
 */
public class RulesFactory {

    public static MetricsRule getRule (RuleType type, Metric metric) {

        if (type == RuleType.OVER_WEIGHT)
            return new OverweightRule(metric);
        else if (type == RuleType.UNDER_WEIGHT)
            return new UnderweightRule(metric);

        return null;
    }
}
