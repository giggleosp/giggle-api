package com.giggle.controllers;

import com.giggle.Application;
import com.giggle.exceptions.NotFoundException;
import com.giggle.util.StringGenerator;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.inject.Inject;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

/**
 * Created by enda on 06/03/16.
 */
@RestController
public class ResourceController {

    private final ResourceLoader ctx;

    @Inject
    public ResourceController(ResourceLoader ctx) {
        this.ctx = ctx;
    }

    // http://www.journaldev.com/2573/spring-mvc-file-upload-example-tutorial-single-and-multiple-files
    public static String uploadPhoto(MultipartFile photo, String location) {
        String fileName = StringGenerator.generateString();
        fileName += ".png";

        if (!photo.isEmpty()) {
            try {
                byte[] bytes = photo.getBytes();

                File dir = new File(Application.IMAGES_DIRECTORY + File.separator + location);

                File file = new File(dir.getAbsolutePath()
                        + File.separator + fileName);
                while (file.exists()) {
                    // regenerate file name if there is a conflict
                    fileName = StringGenerator.generateString();
                }
                BufferedOutputStream stream =
                        new BufferedOutputStream(new FileOutputStream(file));
                stream.write(bytes);
                stream.close();
            } catch (Exception e) {
            }
        }
        return "images/" + location + "/" + fileName;
    }

    @RequestMapping(method = RequestMethod.GET, path = "images/{dir}/{imageName}", produces = MediaType.IMAGE_PNG_VALUE)
    public Resource getImage(@PathVariable String dir, @PathVariable String imageName) {
        String imagePath = String.format("classpath:/images/%s/%s", dir, imageName + ".png");
        Resource resource = ctx.getResource(imagePath);

        if (!resource.exists())
            throw new NotFoundException();

        return resource;
    }
}
