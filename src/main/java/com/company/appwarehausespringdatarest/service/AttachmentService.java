package com.company.appwarehausespringdatarest.service;

import com.company.appwarehausespringdatarest.entity.Attachment;
import com.company.appwarehausespringdatarest.entity.AttachmentContent;
import com.company.appwarehausespringdatarest.payload.Result;
import com.company.appwarehausespringdatarest.repasitory.AttachmentContentRepository;
import com.company.appwarehausespringdatarest.repasitory.AttachmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
@Service
public class AttachmentService {
    @Autowired
    private AttachmentRepository attachmentRepository;
    @Autowired
    private AttachmentContentRepository attachmentContentRepository;

    public Result uploadFile(MultipartHttpServletRequest request) throws IOException {
        Iterator<String> fileNames = request.getFileNames();
        if (!fileNames.hasNext()) return new Result("File not found",false);

        List<MultipartFile> files = request.getFiles(fileNames.next());
        Iterator<MultipartFile> iterator = files.iterator();

        while (iterator.hasNext()){
            MultipartFile file = iterator.next();
            Attachment attachment = new Attachment();
            attachment.setContentType(file.getContentType());
            attachment.setName(file.getOriginalFilename());
            attachment.setSize(file.getSize());
            attachment = attachmentRepository.save(attachment);
            AttachmentContent attachmentContent = new AttachmentContent();
            attachmentContent.setAttachment(attachment);
            attachmentContent.setBytes(file.getBytes());
            attachmentContentRepository.save(attachmentContent);
        }
        return new Result("File seccessfully added",true);
    }

    public void downloadFile(Integer id, HttpServletResponse response) throws IOException {
        Optional<Attachment> optionalAttachment = attachmentRepository.findById(id);
        if (!optionalAttachment.isPresent()) return;

        Optional<AttachmentContent> optionalAttachmentContent = attachmentContentRepository.findByAttachmentId(id);
        if (!optionalAttachment.isPresent()) return;

        response.setContentType(optionalAttachment.get().getContentType());
        response.setHeader("Content-Disposition","attachment; filename=\""+optionalAttachment.get().getName()+"\"");
        FileCopyUtils.copy(optionalAttachmentContent.get().getBytes(),response.getOutputStream());
    }

    public Result editFile(Integer id, MultipartHttpServletRequest request) throws IOException {
        Optional<Attachment> optionalAttachment = attachmentRepository.findById(id);
        if (!optionalAttachment.isPresent()) return new Result("Attachment not found",false);

        Iterator<String> fileNames = request.getFileNames();
        MultipartFile file = request.getFile(fileNames.next());

        Attachment attachment = optionalAttachment.get();
        attachment.setContentType(file.getContentType());
        attachment.setName(file.getOriginalFilename());
        attachment.setSize(file.getSize());
        attachment = attachmentRepository.save(attachment);

        AttachmentContent attachmentContent = new AttachmentContent();
        attachmentContent.setAttachment(attachment);
        attachmentContent.setBytes(file.getBytes());
        attachmentContentRepository.save(attachmentContent);

        return new Result("File seccessfully edited",true);
    }

    public Result deleteFile(Integer id) {
        Optional<Attachment> optionalAttachment = attachmentRepository.findById(id);
        if (!optionalAttachment.isPresent()) return new Result("File not found",false);

        Optional<AttachmentContent> optionalAttachmentContent = attachmentContentRepository.findByAttachmentId(id);

        if (!optionalAttachmentContent.isPresent()) return new Result("Error",false);
        attachmentContentRepository.delete(optionalAttachmentContent.get());

        return new Result("File seccessfully deleted",true);
    }
}
