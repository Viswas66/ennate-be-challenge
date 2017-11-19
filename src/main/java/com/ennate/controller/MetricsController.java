package com.ennate.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.ennate.MorphiaConfig;
import com.ennate.model.Metric;
import com.ennate.rules.MetricsRule;
import com.ennate.rules.RulesFactory;
import com.ennate.service.MetricService;
import org.easyrules.api.RulesEngine;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;

import static org.easyrules.core.RulesEngineBuilder.aNewRulesEngine;

import java.util.ArrayList;
import java.util.List;
import com.ennate.rules.MetricsRule.RuleType;

/**
 * Created by vkuruval on 11/17/17.
 */

@RequestMapping("/metrics")
@RestController
public class MetricsController {

    @Autowired
    private MetricService metricService;

    MetricsController() {
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<Metric> create(@RequestBody Metric metric) {

        if (metric == null)
            return new ResponseEntity<Metric>(metric, HttpStatus.BAD_REQUEST);

        metricService.createMetric(metric);

        return new ResponseEntity<Metric>(metric, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/read")
    public ResponseEntity<List<Metric>> read() {

        List<Metric> metricList = metricService.read();

        if (metricList.size() == 0)
            return new ResponseEntity<List<Metric>>(metricList, HttpStatus.NO_CONTENT);

        return new ResponseEntity<List<Metric>>(metricList, HttpStatus.OK);
    }

    @RequestMapping(value = "/readRange/{startTime}/{endTime}")
    public ResponseEntity<List<Metric>> readByTimeRange(@PathVariable Long startTime, @PathVariable Long endTime) {

        if (startTime == null || endTime == null)
            return new ResponseEntity<List<Metric>>(new ArrayList<Metric>(), HttpStatus.BAD_REQUEST);

        List<Metric> metricList = metricService.readByRange(startTime, endTime);

        if (metricList.size() == 0)
            return new ResponseEntity<List<Metric>>(metricList, HttpStatus.NO_CONTENT);

        return new ResponseEntity<List<Metric>>(metricList, HttpStatus.OK);
    }
}
