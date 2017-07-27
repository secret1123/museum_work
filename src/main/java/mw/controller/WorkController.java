package mw.controller;

import mw.model.Museum;
import mw.util.Constant;
import mw.util.FileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import mw.model.Work;
import mw.service.WorkService;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("work")
public class WorkController extends BaseController {

    private final WorkService workService;

    @Autowired
    public WorkController(WorkService workService) {
        this.workService = workService;
    }

    @RequestMapping("test")
    @ResponseBody
    private List<Work> test(){
        List<Work> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Museum museum = new Museum(i,"name","logo.png","picture.jpg","address",null);
            Work work = new Work(i,"title","picture","artist",1911,1,museum);
            list.add(work);
        }
        return list;
    }

    @RequestMapping("create")
    private String create(Work work, @RequestParam MultipartFile pictureFile) {
        String photoPath = application.getRealPath(Constant.UPLOAD_PHOTO_PATH);
        work.setPicture(FileUpload.upload(photoPath,pictureFile));
        workService.create(work);
        return "redirect:/work/queryAll";
    }

    @RequestMapping("remove/{id}")
    private String remove(@PathVariable("id") Integer id) {
        workService.remove(id);
        return "redirect:/work/queryAll";
    }

    @RequestMapping("modify")
    private String modify(Work work,@RequestParam MultipartFile pictureFile) {
        if (!pictureFile.isEmpty()){
            String photoPath = application.getRealPath(Constant.UPLOAD_PHOTO_PATH);
            work.setPicture(FileUpload.upload(photoPath,pictureFile));
        }
        workService.modify(work);
        return "redirect:/work/queryAll";
    }

    @RequestMapping("queryAll/{page}")
    private String queryAll(@PathVariable int page) {
        session.setAttribute("pagination", workService.queryAll(page));
        return "redirect:/work/list.jsp";
    }

    @RequestMapping("queryAll")
    private String queryAll() {
        return queryAll(1);
    }

    @RequestMapping("queryById/{id}")
    private String queryById(@PathVariable("id") Integer id) {
        session.setAttribute("work", workService.queryById(id));
        return "redirect:/work/edit.jsp";
    }

    @RequestMapping("queryWorks/{currentPage}")
    private String queryWorks(@PathVariable int currentPage) {
        session.setAttribute("pagination", workService.query("queryWorks", null, currentPage));
        return "redirect:/work/works.jsp";
    }

    @RequestMapping("queryWorks")
    private String queryWorks() {
        return queryWorks(1);
    }
}