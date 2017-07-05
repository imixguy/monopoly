package rw.ktc.ksupr.web.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import rw.ktc.ksupr.web.dao.access.IProjectData;
import rw.ktc.ksupr.web.dao.entity.StaMod;
import rw.ktc.ksupr.web.dao.entity.vo.DistrictVO;
import rw.ktc.ksupr.web.dao.entity.vo.ForecastVO;
import rw.ktc.ksupr.web.dao.entity.vo.StationNsi;
import rw.ktc.ksupr.web.service.IServiseData;

import java.util.List;

/**
 * Created by dima on 10.01.2015.
 */
@Controller
@RequestMapping("/ajax")
public class AjaxController {
    private static final Logger logger = LoggerFactory.getLogger(AjaxController.class);
    @Autowired
    IServiseData serviseData;

    @Autowired
    IProjectData projectData;

    @ResponseBody
    @RequestMapping(value = "/simstations", method = RequestMethod.GET, produces = "application/json")
    public List<StaMod> simstations() {
        return getServiseData().findAllStaMod();
    }

    @ResponseBody
    @RequestMapping(value = "/forecast", method = RequestMethod.GET, produces = "application/json")
    public ForecastVO forecast(@RequestParam("id") Integer id) {
        return getServiseData().getForecastById(id);
    }

    @ResponseBody
    @RequestMapping(value="/forecast/delete/{id}", method=RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public String deleteForecasts(@PathVariable int id) {
        return getServiseData().deleteForecast(id);
    }

    @ResponseBody
    @RequestMapping(value = "/forecasts", method = RequestMethod.GET, produces = "application/json")
    public List<ForecastVO> getForecasts() {
        return getServiseData().getForecasts();
    }

    @ResponseBody
    @RequestMapping(value = "/lastforecast", method = RequestMethod.GET, produces = "application/json")
    public ForecastVO getLastForecast() {
        ForecastVO lastfForecast = getServiseData().getLastForecast();
        return lastfForecast;
    }

    @ResponseBody
    @RequestMapping(value = "/districts", method = RequestMethod.GET, produces = "application/json")
    public List<DistrictVO> getDistricts() {
        List<DistrictVO> list = serviseData.getDistricts();
        return list;
    }

    @ResponseBody
    @RequestMapping(value = "/stations", method = RequestMethod.GET, produces = "application/json")
    public List<StationNsi> getStationsNsi() {
        List<StationNsi> list = serviseData.getStationsNsi();
        return list;
    }
    @ResponseBody
    @RequestMapping(value = "/station", method = RequestMethod.GET, produces = "application/json")
    public StationNsi getStationNsi(@RequestParam("esr") String esr) {
        return getServiseData().getStationNsi(esr);
    }

    // getter and setters
    public IServiseData getServiseData() {
        return serviseData;
    }

    public void setServiseData(IServiseData serviseData) {
        this.serviseData = serviseData;
    }

    public IProjectData getProjectData() {
        return projectData;
    }

    public void setProjectData(IProjectData projectData) {
        this.projectData = projectData;
    }
}
