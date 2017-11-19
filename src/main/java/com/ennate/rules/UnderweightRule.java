package com.ennate.rules;


import org.easyrules.annotation.Action;
import org.easyrules.annotation.Condition;
import org.easyrules.annotation.Rule;
import org.mongodb.morphia.Datastore;
import org.springframework.beans.factory.annotation.Autowired;
import com.ennate.MorphiaConfig;
import com.ennate.dao.AlertDAO;
import com.ennate.model.Alert;
import com.ennate.model.Metric;
import com.ennate.service.AlertService;

/**
 * Created by vkuruval on 11/18/17.
 */

@Rule (name = "Under-Weight")
public class UnderweightRule implements MetricsRule {

    private AlertDAO alertDAO = new AlertDAO();

    private Metric metric;

    public UnderweightRule(Metric metric) {
        this.metric = metric;
    }

    @Override
    @Condition
    public boolean when() {
        double percent = ((double) metric.getValue()) / baseWeight;

        return percent < 0.9;

    }

    @Override
    @Action
    public void then() {
        Alert alert = new Alert(MetricsRule.RuleType.UNDER_WEIGHT.toString(), metric.getTimeStamp(), metric.getValue());

        alertDAO.create(alert);
    }
}