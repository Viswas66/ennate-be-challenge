package com.ennate.service;

import com.ennate.dao.AlertDAO;
import com.ennate.dao.MetricDAO;
import com.ennate.model.Alert;
import com.ennate.model.Metric;
import com.ennate.rules.MetricsRule;
import com.ennate.rules.RulesFactory;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.easyrules.core.RulesEngineBuilder.aNewRulesEngine;

import com.ennate.MorphiaConfig;
import com.ennate.dao.AlertDAO;
import com.ennate.model.Alert;
import com.ennate.model.Metric;
import com.ennate.service.AlertService;
/**
 * Created by vkuruval on 11/18/17.
 */

@Component
public class AlertService {


    @Autowired
    private AlertDAO alertDAO;

    public AlertService() {
    }

    public ObjectId create (Alert alert) {
        return alertDAO.create(alert);
    }

    public List<Alert> read () {
        return alertDAO.read();
    }

    public List<Alert> readByRange(long startTime, long endTime) {
        return alertDAO.readByRange(startTime, endTime);
    }
}
