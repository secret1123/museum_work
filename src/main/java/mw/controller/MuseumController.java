package mw.controller;

import mw.model.Work;
import mw.service.WorkService;
import mw.util.Constant;
import mw.util.FileUpload;
import mw.util.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import mw.model.Museum;
import mw.service.MuseumService;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequestMapping("museum")
public class MuseumController extends BaseController {

    private final MuseumService museumService;
    private final WorkService workService;

    @Autowired
    public MuseumController(MuseumService museumService,WorkService workService) {
        this.museumService = museumService;
        this.workService = workService;
    }

    @RequestMapping("create")
    private String create(Museum museum, @RequestParam MultipartFile logoFile,@RequestParam MultipartFile pictureFile) {
        String photoPath = application.getRealPath(Constant.UPLOAD_PHOTO_PATH);
        museum.setLogo(FileUpload.upload(photoPath,logoFile));
        museum.setPicture(FileUpload.upload(photoPath,pictureFile));
        museumService.create(museum);
        return "redirect:/museum/queryAll";
    }

    @RequestMapping("remove/{id}")
    private String remove(@PathVariable("id") Integer id) {
        museumService.remove(id);
        return "redirect:/museum/queryAll";
    }

    @RequestMapping("modify")
    private String modify(Museum museum) {
        museumService.modify(museum);
        return "redirect:/museum/queryAll";
    }

    @RequestMapping("queryAll/{page}")
    private String queryAll(@PathVariable int page) {
        session.setAttribute("pagination", museumService.queryAll(page));
        return "redirect:/museum/list.jsp";
    }

    @RequestMapping("queryAll")
    private String queryAll() {
        return queryAll(1);
    }

    @RequestMapping("queryById/{id}")
    private String queryById(@PathVariable("id") Integer id) {
        session.setAttribute("museum", museumService.queryById(id));
        return "redirect:/museum/edit.jsp";
    }

    @RequestMapping("queryMuseums/{currentPage}")
    private String queryMuseums(@PathVariable int currentPage) {
        Pagination<Museum> pagination = museumService.query("queryMuseums",null,currentPage);
        for (int i = 0; i < pagination.getList().size(); i++) {
            int museumId = pagination.getList().get(i).getId();
            List<Work> works = workService.queryList("queryWorksByMuseumId",museumId);
            pagination.getList().get(i).setWorks(works);
        }
        session.setAttribute("pagination", pagination);
        return "redirect:/museum/museums.jsp";
    }

    @RequestMapping("queryMuseums")
    private String queryMuseums() {
        return queryMuseums(1);
    }
}