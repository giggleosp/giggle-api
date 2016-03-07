package com.giggle.controllers;

import com.giggle.exceptions.NotFoundException;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;

/**
 * Created by enda on 06/03/16.
 */
@RestController
public class ResourceController {

    private final ResourceLoader ctx;

    @Inject public ResourceController(ResourceLoader ctx) {
        this.ctx = ctx;
    }

    @RequestMapping(method = RequestMethod.GET, path = "images/{dir}/{imageName}", produces = MediaType.IMAGE_PNG_VALUE)
    public Resource getImage(@PathVariable String dir, @PathVariable String imageName) {
        String imagePath = String.format("classpath:images/%s/%s", dir, imageName + ".png");
        Resource resource = ctx.getResource(imagePath);

        if (!resource.exists())
            throw new NotFoundException();

        return resource;
    }
}
