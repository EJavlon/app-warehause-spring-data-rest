package com.company.appwarehausespringdatarest.controller;

import com.company.appwarehausespringdatarest.payload.Result;
import com.company.appwarehausespringdatarest.service.AttachmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/attachment")
public class AttachmentController {
    @Autowired
    private AttachmentService attachmentService;

    @PostMapping
    public Result uploadFile(MultipartHttpServletRequest request) throws IOException {
        return attachmentService.uploadFile(request);
    }

    @GetMapping("/{id}")
    public void downloadFile(@PathVariable Integer id, HttpServletResponse response) throws IOException {
        attachmentService.downloadFile(id,response);
    }

    @PutMapping("/{id}")
    public Result editFile(@PathVariable Integer id, MultipartHttpServletRequest request) throws IOException {
        return attachmentService.editFile(id,request);
    }

    @DeleteMapping("/{id}")
    public Result deleteFile(@PathVariable Integer id){
        return attachmentService.deleteFile(id);
    }
}
