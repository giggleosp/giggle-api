package com.giggle.repositories.act;

import com.giggle.JinqSource;
import com.giggle.models.Act;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by enda on 01/03/16.
 */
@Repository
public class ActRepositoryImpl implements ActRepository {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private JinqSource source;

    @Override
    public List<Act> getActs() {
        List<Act> acts = source.acts(em)
                .filter(Act::isEnabled)
                .collect(Collectors.toList());
        return acts.isEmpty() ? null : acts;
    }
}
