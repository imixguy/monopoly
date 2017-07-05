package rw.gcktc.webcms.controller.other;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import rw.gcktc.image.FileUploaderIface;
import rw.gcktc.image.ImageException;

import javax.servlet.http.HttpSession;
import java.io.File;

/**
 * Created with IntelliJ IDEA.
 * User: miha
 * Date: 02.12.13
 * Time: 17:02
 * To change this template use File | Settings | File Templates.
 */
@Controller("FileUploadController")
@SessionAttributes
public class FileUploadController {
    private static Logger logger = Logger.getLogger(FileUploadController.class);


    private FileUploaderIface fileUploaderIface;
    @Qualifier("imageUploadForUser")
    @Autowired
    public void setFileUploaderIface(FileUploaderIface fileUploaderIface) {
        this.fileUploaderIface = fileUploaderIface;
    }

    private ApplicationContext context;
    @Autowired
    public void setContext(ApplicationContext context) {
        this.context = context;
    }

    @RequestMapping(value = "fileupload/upload.html",method= RequestMethod.POST) // Прием файла
    @ResponseBody
    public String upload(@RequestParam(value="upload", required=false) MultipartFile image,@RequestParam(value="CKEditorFuncNum") String cKEditorFuncNum, HttpSession httpSession) {
        try {
            String webRootPath=httpSession.getServletContext().getRealPath("");
            fileUploaderIface.validateImage(image); // Проверить изображение на размер и формат

//            String userName;
//            try {
//                userName=((ConteinerForSession)httpSession.getAttribute("contS")).getUser().getName();
//            } catch (Exception e) {
//                throw new ImageException("Yours not authorization");
//            }


            String fileName= image.getOriginalFilename();

            String folder=webRootPath+File.separator+"resources"+File.separator+"userfiles"+File.separator;//+userName+File.separator;

            fileName=fileUploaderIface.isFileIssue(folder, fileName);

            fileUploaderIface.saveFile(folder, fileName, image); // Сохранить файл
//                return "<html><head><script>parent.usF.imageLoad(true,'"+fileName+"');</script></head><body>Дарова</body></html>";
            String errorMessage="";
            String fileN="/resources/userfiles/"/*+userName+"/"*/ + fileName;
            return "<html><head><script>window.parent.CKEDITOR.tools.callFunction(\""+cKEditorFuncNum+"\", \""+fileN+"\", \""+errorMessage+"\" );</script></head><body>Дарова</body></html>";

        } catch (ImageException e) {
            //bindingResult.reject(e.getMessage());
            return "<html><head><script>window.parent.CKEDITOR.tools.callFunction(\""+cKEditorFuncNum+"\", \""+""+"\", \""+e.getMessage()+"\" );</script></head><body>Дарова</body></html>";
        }
    }
}
