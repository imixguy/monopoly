package rw.ktc.ksupr.web.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import rw.ktc.ksupr.web.service.IServiseData;
/**
 * Created by sedler on 03.12.14.
 */
@Controller
public class MainController {

    final static private Logger logger = LoggerFactory.getLogger(MainController.class);
    @Autowired
    private IServiseData serviseData;
//    ------------------------------------------------------------------------------
    @RequestMapping(value = "/main")
    public String index(@RequestParam(value = "forecastId", required = false) Integer id, Model model) {
        if(id==null){
            model.addAttribute("forecastId", "");
        }else{
            model.addAttribute("forecastId", id);
        }
        return "staMod";
    }
    //----------------------геттеры сеттеры-------------------
    public IServiseData getServiseData() {
        return serviseData;
    }

    public void setServiseData(IServiseData serviseData) {
        this.serviseData = serviseData;
    }

}