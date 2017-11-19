package com.ennate.service;

import com.ennate.dao.MetricDAO;
import com.ennate.model.Metric;
import com.ennate.rules.MetricsRule;
import com.ennate.rules.RulesFactory;
import org.bson.types.ObjectId;
import org.easyrules.api.RulesEngine;
import org.mongodb.morphia.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.easyrules.core.RulesEngineBuilder.aNewRulesEngine;

/**
 * Created by vkuruval on 11/18/17.
 */

@Component
public class MetricService {

    private RulesEngine rulesEngine;
    private MetricsRule rule;

    @Autowired
    private MetricDAO metricDAO;

    MetricService() {
        rulesEngine = aNewRulesEngine().build();
    }

    public ObjectId createMetric(Metric metric) {
        rule = RulesFactory.getRule(MetricsRule.RuleType.UNDER_WEIGHT, metric);
        rulesEngine.registerRule(rule);
        rule = RulesFactory.getRule(MetricsRule.RuleType.OVER_WEIGHT, metric);
        rulesEngine.registerRule(rule);

        rulesEngine.fireRules();
        rulesEngine.clearRules();

        return metricDAO.createMetric(metric);
    }

    public List<Metric> read () {
        return metricDAO.read();
    }

    public List<Metric> readByRange(long startTime, long endTime) {
        return metricDAO.readByRange(startTime, endTime);
    }
}
