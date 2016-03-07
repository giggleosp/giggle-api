package com.giggle.controllers;

import com.giggle.exceptions.NotFoundException;
import com.giggle.models.Act;
import com.giggle.repositories.act.ActRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by enda on 01/03/16.
 */
@RestController
@RequestMapping("/acts")
public class ActController {

    private final ActRepository repo;

    @Inject public ActController(ActRepository repo) {
        this.repo = repo;
    }

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody List<Act> getActs() {
        List<Act> acts = repo.getActs();

        if (acts == null)
            throw new NotFoundException();
        else
            return acts;

    }
}
