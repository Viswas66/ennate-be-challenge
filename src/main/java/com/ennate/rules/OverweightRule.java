package com.ennate.rules;


import com.ennate.service.AlertService;
import org.easyrules.annotation.Action;
import org.easyrules.annotation.Condition;
import org.easyrules.annotation.Rule;
import org.mongodb.morphia.Datastore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.ennate.MorphiaConfig;
import com.ennate.dao.AlertDAO;
import com.ennate.model.Alert;
import com.ennate.model.Metric;

/**
 * Created by vkuruval on 11/18/17.
 */

@Rule(name = "Over-Weight")
public class OverweightRule implements MetricsRule{

    private AlertDAO alertDAO = new AlertDAO();

    private Metric metric;

    public OverweightRule(Metric metric) {
        this.metric = metric;
    }

    @Override
    @Condition
    public boolean when() {
        double percent = ((double) metric.getValue()) / baseWeight;

        return percent > 1.1;

    }

    @Override
    @Action
    public void then() {
        Alert alert = new Alert(MetricsRule.RuleType.OVER_WEIGHT.toString(), metric.getTimeStamp(), metric.getValue());

        alertDAO.create(alert);
    }
}
