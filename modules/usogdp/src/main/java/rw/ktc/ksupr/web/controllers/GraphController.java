package rw.ktc.ksupr.web.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import rw.ktc.ksupr.web.dao.entity.vo.OperationVO;
import rw.ktc.ksupr.web.dao.entity.vo.StaDistrict;
import rw.ktc.ksupr.web.service.IServiseData;

import java.util.*;

/**
 * Created by dima on 04.11.2014.
 */
@Controller
@RequestMapping("/graph")
public class GraphController {
    private static final Logger logger = LoggerFactory.getLogger(GraphController.class);
    @Autowired
    IServiseData serviseData;

    @RequestMapping("/do")
    public String graph(@RequestParam("districts[]") List<Integer> districtIds,
                        @RequestParam(value = "forecastId", required = false) Integer forecastId,
                        @RequestParam(value = "showT", required = false) Boolean showTable,
                        Model model) {
        model.addAttribute("forecastId", forecastId != null ? forecastId : 0);
        model.addAttribute("districts", districtIds);
        model.addAttribute("showT", showTable != null ? showTable : false);
        return "graphdistricts";
    }

    @ResponseBody
    @RequestMapping(value = "/operations", method = RequestMethod.GET, produces = "application/json")
    public List<OperationVO> getOperations(@RequestParam(value = "forecastId", required = false) Integer forecastId) {
        List<OperationVO> operations = getServiseData().getOperationByForecast(forecastId);
        return operations;
    }

//    @ResponseBody
//    @RequestMapping(value = "/mapdistrict", method = RequestMethod.GET, produces = "application/json")
//    public Map<Integer, List<StaDistrict>> mapdistrict(@RequestParam("districts[]") List<Integer> districtIds) {
//        Map<Integer, List<StaDistrict>> staDistrMap = new HashMap<>();
//        int districtId;
//        List<StaDistrict> staDistricts = null;
//        for (int i = 0; i < districtIds.size(); i++) {
//            districtId = districtIds.get(i);
//            staDistricts = getServiseData().getStationsByDistrict(districtId);
//            staDistrMap.put(districtId, staDistricts);
//        }
//        return staDistrMap;
//    }


    @ResponseBody
    @RequestMapping(value = "/stadistrict", method = RequestMethod.GET, produces = "application/json")
    public List<StaDistrict> stadistrict(@RequestParam("districts[]") List<Integer> districtIds) {
        Map<Integer, List<StaDistrict>> staDistrMap = new HashMap<>();
        List<StaDistrict> finalStaDistricts = new ArrayList<>();
        int districtId;
        List<StaDistrict> staDistricts = null;
        for (int i = 0; i < districtIds.size(); i++) {
            districtId = districtIds.get(i);
            staDistricts = getServiseData().getStationsByDistrict(districtId);
            staDistrMap.put(districtId, staDistricts);
        }

        List<StaDistrict> staDistrictsTwo = null;
        List<StaDistrict> finalList = null;
        StaDistrict n1;
        StaDistrict k1;
        StaDistrict n2;
        StaDistrict k2;

        finalList = staDistrMap.get(districtIds.get(0));
        List<Integer> addedDistricts = new ArrayList<>();
        addedDistricts.add(districtIds.get(0));
        for (int i = 1; i < districtIds.size(); i++) {
            districtId = districtIds.get(i);
//            staDistrictsOne = staDistrMap.get(districtIds.get(i));
            staDistrictsTwo = staDistrMap.get(districtId);
            n1 = finalList.get(0);
            k1 = finalList.get(finalList.size() - 1);
            n2 = staDistrictsTwo.get(0);
            k2 = staDistrictsTwo.get(staDistrictsTwo.size() - 1);
            if (n1.esr.trim().equals(n2.esr.trim())) { //начало 1 участка соединяется с началом 2 участка
//                staDistrictsTwo.remove(n2);
                Collections.reverse(staDistrictsTwo);
                List<StaDistrict> tmp = new ArrayList<>(staDistrictsTwo);
                tmp.addAll(finalList);
                finalList = tmp;
                addedDistricts.add(districtId);
                continue;
            }
            if (n1.esr.trim().equals(k2.esr.trim())) { //начало 1 участка соединяется с началом 2 участка
//                staDistrictsTwo.remove(k2);
                List<StaDistrict> tmp = new ArrayList<>(staDistrictsTwo);
                tmp.addAll(finalList);
                finalList = tmp;
                addedDistricts.add(districtId);
                continue;
            }
            if (k1.esr.trim().equals(n2.esr.trim())) { //конец 1 участка соединяется с концом 2 участка
//                staDistrictsTwo.remove(n2);
                finalList.addAll(staDistrictsTwo);
                addedDistricts.add(districtId);
                continue;
            }
            if (k1.esr.trim().equals(k2.esr.trim())) { //конец 1 участка соединяется с концом 2 участка
//                staDistrictsTwo.remove(k2);
                Collections.reverse(staDistrictsTwo);
                finalList.addAll(staDistrictsTwo);
                addedDistricts.add(districtId);
                continue;
            }
        }

        StaDistrict std1; //удаление дубликатов станций (кроме границ , если признак st_pr = 1, 4, 5 - границы)
        StaDistrict std2;
        int i = 0;
        while (i < finalList.size() - 1) {
//            std1 = finalList.get(i);
            int j = i + 1;
            while (j < finalList.size()) {
                std1 = finalList.get(i);
                std2 = finalList.get(j);
                if (std1.distr == std2.distr && std1.esr.trim().equals(std2.esr.trim())) {
                    if ((std1.st_pr == 1 || std1.st_pr == 4 || std1.st_pr == 5) &&
                            (std2.st_pr != 1 || std2.st_pr != 4 || std2.st_pr != 5) ) {
                        finalList.remove(std2);
                        continue;
                    }
                    if ((std2.st_pr == 1 || std2.st_pr == 4 || std2.st_pr == 5) &&
                            (std1.st_pr != 1 || std1.st_pr != 4 || std1.st_pr != 5) ) {
                        finalList.remove(std1);
                        continue;
                    }
//                    if (!(std2.st_pr == 1 || std2.st_pr == 4 || std2.st_pr == 5)) {
//                        finalList.remove(std2);
//                        continue;
//                    }
                    j++;
                    continue;
                } else {
                    break;
                }
//                j++;
            }
            i++;
        }
        return finalList;
    }

//    @ResponseBody
//    @RequestMapping(value = "/stadistrict", method = RequestMethod.GET, produces = "application/json")
//    public String stadistrict(@RequestParam("districts[]") List<Integer> districtIds,
//                              @RequestParam(value = "forecastId", required = false) Integer forecastId, Model model) {
//        ForecastVO forecastVO;
//        if (null == forecastId) {
//            forecastVO = getServiseData().getLastForecast();
//        } else {
//            forecastVO = getServiseData().getForecastById(forecastId);
//        }
//        List<DistrictVO> districts = new ArrayList<>();
//        int index;
//        DistrictVO districtVO;
//        for (int i = 0; i < districtIds.size(); i++) {
//            index = districtIds.get(i);
//            districtVO = getServiseData().getDistrictById(index);
//            districts.add(districtVO);
//        }
//
//        model.addAttribute("forecastVO", forecastVO);
//        model.addAttribute("forecastVO", forecastVO);
//
//        return districts.toString();
//    }

//    @ResponseBody @RequestMapping(value = "/stadistrict", method = RequestMethod.GET, produces = "application/json")
//    public String stadistrict2(@RequestParam("districts[]") List<Integer> districts) {
//        System.out.println(districts);
//        return "" + districts;
//    }

    //    @RequestMapping("/staMod") //project management
//    public String helloWorld(Model model) {
//        model.addAttribute("message", "Hello World!");
//        model.addAttribute("staMod", serviseData.findAllStaModByUser(null));
//        logger.info("getLastForecast logging +model:{}", model);
//        return "test2";
//    }

//    @Autowired
//    IProjectData projectData;

//    @RequestMapping("/staMod") //project management
//    public String listStaMod(Model model) {
////        model.addAttribute("message", "Hello World!");
////        model.addAttribute("staMods", serviseData.findAllStaModByUser(null));
////        logger.info("getLastForecast logging +model:{}", model);
//        return "staModTest";
//    }

    public IServiseData getServiseData() {
        return serviseData;
    }

    public void setServiseData(IServiseData serviseData) {
        this.serviseData = serviseData;
    }
}
