package rw.ktc.gktc.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by miha on 04.12.2014.
 */
@Controller("RedirectViewController")
public class RecodsController{
    @Autowired
    private RecrdsService recrdsService;

    public void setRecrdsService(RecrdsService recrdsService) {
        this.recrdsService = recrdsService;
    }

    @RequestMapping(value = "user/saveRecord.html", method = RequestMethod.POST)
    public Record saveRecord(Record record) {
        recrdsService.saveRecord(record);
        return null;//todo
    }

    @PreAuthorize("hasAuthority('ROLE_READ_BOOK')")
    @RequestMapping(value = "user/allviewRecord.html", method = RequestMethod.GET)
    public ModelAndView getViewAllRecord() {
        ModelAndView mav=new ModelAndView();
        mav.addObject("listRecrds",recrdsService.getAllRecord());
        mav.setViewName("templates/book/bookPage/allview");
        return mav;
    }


    @RequestMapping(value = "user/alldataRecord", method = RequestMethod.GET, produces="application/json")
    public @ResponseBody List<String> getAllRecord() {
        List<String> listS=new ArrayList<>();
        for(Record rec:recrdsService.getAllRecord()){
            listS.add(rec.getTitle());
        }
        return listS;
    }


    public Record getRecordById(Long id) {
        return null;
    }


    public boolean removeRecordById(Long id) {
        return false;
    }


    public boolean removeRecord(Record record) {
        return false;
    }
}
