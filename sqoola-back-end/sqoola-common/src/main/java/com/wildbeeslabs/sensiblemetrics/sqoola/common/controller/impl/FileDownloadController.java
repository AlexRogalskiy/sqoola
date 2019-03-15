package com.wildbeeslabs.sensiblemetrics.sqoola.common.controller.impl;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author imssbora
 */
@Controller
public class FileDownloadController {

    private static final String FILE_PATH = "D:/e-Books/jsp_tutorial.pdf";

    @GetMapping("/")
    public String fileUploadForm(Model model) {
        return "fileDownloadView";
    }

    // Using ResponseEntity<InputStreamResource>
    @GetMapping("/download1")
    public ResponseEntity<InputStreamResource> downloadFile1() throws IOException {

        File file = new File(FILE_PATH);
        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

        return ResponseEntity.ok()
            .header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment;filename=" + file.getName())
            .contentType(MediaType.APPLICATION_PDF).contentLength(file.length())
            .body(resource);
    }

    // Using ResponseEntity<ByteArrayResource>
    @GetMapping("/download2")
    public ResponseEntity<ByteArrayResource> downloadFile2() throws IOException {

        Path path = Paths.get(FILE_PATH);
        byte[] data = Files.readAllBytes(path);
        ByteArrayResource resource = new ByteArrayResource(data);

        return ResponseEntity.ok()
            .header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment;filename=" + path.getFileName().toString())
            .contentType(MediaType.APPLICATION_PDF).contentLength(data.length)
            .body(resource);
    }

    // Using HttpServletResponse
    @GetMapping("/download3")
    public void downloadFile3(HttpServletResponse resonse) throws IOException {
        File file = new File(FILE_PATH);

        resonse.setContentType("application/pdf");
        resonse.setHeader("Content-Disposition", "attachment;filename=" + file.getName());
        BufferedInputStream inStrem = new BufferedInputStream(new FileInputStream(file));
        BufferedOutputStream outStream = new BufferedOutputStream(resonse.getOutputStream());

        byte[] buffer = new byte[1024];
        int bytesRead = 0;
        while ((bytesRead = inStrem.read(buffer)) != -1) {
            outStream.write(buffer, 0, bytesRead);
        }
        outStream.flush();
        inStrem.close();
    }
}
