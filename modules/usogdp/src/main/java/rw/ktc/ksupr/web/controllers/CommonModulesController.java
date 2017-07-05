package rw.ktc.ksupr.web.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import rw.ktc.ksupr.web.dao.entity.vo.StationNsi;
import rw.ktc.ksupr.web.service.IServiseData;
import rw.ktc.usogdp.filters.raspr.FiltrRasprPorVagon;
import rw.ktc.usogdp.filters.raspr.VagonNazn;

import java.util.List;

/**
 * Created by dima on 04.11.2014.
 */
@Controller
@RequestMapping("/modules/ajax")
public class CommonModulesController {
    private static final Logger logger = LoggerFactory.getLogger(CommonModulesController.class);
    @Autowired
    IServiseData serviseData;

    @ResponseBody
    @RequestMapping(value = "/rasprvag", method = RequestMethod.GET, produces = "application/json")
    public FiltrRasprPorVagon getStationNsi(@RequestParam("esr") String esr) {
        return getServiseData().getFiltersRasp(esr);
    }


    @ResponseBody
    @RequestMapping(value = "/stavagons", method = RequestMethod.GET, produces = "application/json")
    public List<StationNsi> getStationsNsi() {
        List<StationNsi> list = serviseData.getStaFilterVagon();
        return list;
    }

    @ResponseBody
    @RequestMapping(value = "/allraspvagons", method = RequestMethod.GET, produces = "application/json")
    public List<VagonNazn> getAllVagonNazn() {
        List<VagonNazn> list = serviseData.getAllVagonNazn();
        return list;
    }

    @ResponseBody
    @RequestMapping(value = "/savevagons", method = RequestMethod.POST, produces = "application/json")
    public boolean saveVagonsNazn(@RequestBody List<VagonNazn> list) {
        logger.debug("обвновление порожних вагонов:{}", list);
        boolean b = serviseData.saveVagonNazn(list);
        return b;
    }

    public IServiseData getServiseData() {
        return serviseData;
    }

    public void setServiseData(IServiseData serviseData) {
        this.serviseData = serviseData;
    }
}
