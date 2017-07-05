package rw.gcktc.webcms.controller.admincontent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import rw.gcktc.cms.menu.Menu;
import rw.gcktc.cms.menu.MenuNodeService;
import rw.gcktc.cms.usermanager.User;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: miha
 * Date: 02.12.13
 * Time: 10:41
 * To change this template use File | Settings | File Templates.
 */
@Controller("adminContentController")
public class AdminContentController {

    private MenuNodeService menuService;

    @Autowired
    public AdminContentController(MenuNodeService menuService) {
        this.menuService = menuService;
    }

    @RequestMapping(value = "admin/addnode/index.html")
    public ModelAndView index(HttpSession httpSession) throws IOException {
//        uiModel.addAttribute("messageSource", messageSource);

        return new ModelAndView("addnode");
    }

    @RequestMapping(value = "admin/menu/listmenu.html")
    public ModelAndView menu(HttpSession httpSession) throws IOException {
//        uiModel.addAttribute("messageSource", messageSource);
        ModelAndView mv= new ModelAndView("listMenu");
        mv.getModel().put("listMenuI",menuService.getAllMenu());

        return mv;
    }


    @RequestMapping(value = "admin/menu/redactMenu.html")
    public String redactMenu(@RequestParam(value = "menu", required = false, defaultValue = "") Long idMenu, @ModelAttribute("menuForm") @Valid Menu menu, BindingResult bindingResult,Model model,HttpSession httpSession) {
        if(bindingResult.hasErrors()) {
            return "redactMenu";
        }
        if(idMenu!=null){
            menu=menuService.getMenuById(idMenu);
            model.addAttribute("menuForm",menu);
        }else{
            if(menu.getTitle()!=null && !menu.getTitle().equals("")){
                menuService.saveMenu((User)httpSession.getAttribute("userA"),menu);
                return "redirect:/admin/menu/listmenu.html";
            }
        }
        return "redactMenu";
    }

    @RequestMapping(value = "admin/menu/removemenu.html")
    public String removemenu(@RequestParam(value = "menu", required = false) Long idMenu, HttpSession httpSession) throws IOException {
        menuService.removeMenu(idMenu);
        return "redirect:/admin/menu/listmenu.html";
    }
}
