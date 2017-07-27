package by.imix.games.gamecore.implDefault.web;

import by.imix.games.gamecore.game.Room;
import by.imix.games.gamecore.game.RoomManager;
import by.imix.games.gamecore.user.UserRoom;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by miha on 16.12.2014.
 */
@Controller("roomManagerController")
public class RoomManagerController{
    private Logger log= LoggerFactory.getLogger(RoomManagerController.class);

    @Autowired
    private RoomManager roomManager;
    public void setRoomManager(RoomManager roomManager) {
        this.roomManager = roomManager;
    }
    public RoomManager getRoomManager() {
        return roomManager;
    }

    @RequestMapping(value = "/games/room/krot.html", method = RequestMethod.GET)
    public ModelAndView getRoomViewer2() {
        ModelAndView mav=new ModelAndView("games/krot/krot");
        return mav;
    }

    @RequestMapping(value = "/games/room/rooms.html", method = RequestMethod.GET)
    public ModelAndView getRoomViewer() {
        log.info("load games/room/rooms.html");
        ModelAndView mav=new ModelAndView("games/monopoly/rooms");
        mav.addObject("userRoom",roomManager.getUser());
        return mav;
    }

    @RequestMapping(value = "/games/room/userdata", method = RequestMethod.GET)
    @ResponseBody
    public UserRoom getUserData() {
        return roomManager.getUser();
    }

    @RequestMapping(value = "/games/room/getAllRoom", method = RequestMethod.GET, produces="application/json")
    @ResponseBody
    public List<Room> getAllRoom() {
        return (List<Room>) roomManager.getAllPermissionRoom();
    }

//@RequestMapping(value = "managercms/dynamiccontent/{idPage}/clonedc.html")
//@PathVariable("idPage")
//@RequestParam("typet")

    @RequestMapping(value = "/games/room/getAllPermissionRoom", method = RequestMethod.GET, produces="application/json")
    @ResponseBody
    public List<Room> getAllPermissionRoom() {
        return (List<Room>) roomManager.getAllPermissionRoom();
    }
}