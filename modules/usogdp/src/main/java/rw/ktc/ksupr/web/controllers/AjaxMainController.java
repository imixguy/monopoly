package rw.ktc.ksupr.web.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import rw.ktc.ksupr.web.dao.entity.vo.*;
import rw.ktc.ksupr.web.service.IServiseData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sedler on 03.12.14.
 */
@Controller
@SessionAttributes
@RequestMapping("/ajax")
public class AjaxMainController {

    private final static Logger logger = LoggerFactory.getLogger(AjaxMainController.class);
    @Autowired
    private IServiseData serviseData;
//    ------------------------------------------------------------------------------
    //-------- направления -----------
    @ResponseBody
    @RequestMapping(value = "/directions", method = RequestMethod.GET, produces = "application/json")
    public List<DirectionVO> getDirection(@RequestParam("esr") String esr) {
        return getServiseData().getDirectionByESR(esr);
    }

    //---------- Поезда --------------
    @ResponseBody
    @RequestMapping(value = "/trains", method = RequestMethod.GET, produces = "application/json")
    public List<TrainVO> getTrains(@RequestParam("esr") String esr,
                                   @RequestParam("forecastId") Integer forecastId) {
        return getServiseData().getTrainsOnStationForDirection(esr, forecastId);
    }

    //------ состав поезда ------
    @ResponseBody
    @RequestMapping(value = "/gettrainconsist", method = RequestMethod.GET, produces = "application/json")
    public List<TrainConsistVO> getTrainConsist(@RequestParam("esr") String esr,
                                                @RequestParam("trId") String trId,
                                                @RequestParam("idTrainHistVag") Long idTrainHistVag,
                                                @RequestParam("forecastId") Integer forecastId) {
        return getServiseData().getTrainConsistency(trId, idTrainHistVag, forecastId, esr);
    }

    //----- расписание поезда ------
    @ResponseBody
    @RequestMapping(value = "/gettrainschedule", method = RequestMethod.GET, produces = "application/json")
    public List<TrainScheduleVO> getTrainSchedule(@RequestParam("trId") String trId,
                                                  @RequestParam("forecastId") Integer forecastId) {
        return getServiseData().getTrainSchedule(trId, forecastId);
    }
    //----- график работы станции по бригадам------
    @ResponseBody
    @RequestMapping(value = "/gettrainprocessing", method = RequestMethod.GET, produces = "application/json")
    public List<TrainProcessingVO> getTrainProcessing(@RequestParam("esr") String esr,
                                                      @RequestParam("forecastId") Integer forecastId) {
        return getServiseData().getTrainProcessing(forecastId, esr);
    }

    //----------------------геттеры сеттеры-------------------
    public IServiseData getServiseData() {
        return serviseData;
    }

    public void setServiseData(IServiseData serviseData) {
        this.serviseData = serviseData;
    }

}
