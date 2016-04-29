package com.giggle.repositories.act;

import com.giggle.JinqSource;
import com.giggle.models.Act;
import com.giggle.models.ActCategory;
import com.giggle.models.ActUser;
import com.giggle.models.Genre;
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

    @Override
    public List<Act> getActsManagedByUser(long id) {
        List<Act> acts = source.actUsers(em)
                .filter(uv -> uv.getUser().getId() == id)
                .filter(ActUser::isAdmin)
                .map(ActUser::getAct).collect(Collectors.toList());

        return acts.isEmpty() ? null : acts;

    }

    @Override
    public Act getActById(long id) {
        List<Act> acts = source.acts(em)
                .where(v -> v.getId() == id)
                .toList();
        return acts.isEmpty() ? null : acts.get(0);
    }

    @Override
    public List<ActCategory> actCategories() {
        List<ActCategory> categories = source.actCategories(em)
                .toList();
        return categories.isEmpty() ? null : categories;
    }

    @Override
    public Act createAct(Act act) {
        em.persist(act);
        return act;
    }

    @Override
    public void createActUserRelationship(ActUser actUser) {
        em.persist(actUser);
    }

    @Override
    public ActUser getActUserRelationship(long actId, long userId) {
        List<ActUser> actUsers = source.actUsers(em)
                .where(v -> v.getAct().getId() == actId)
                .where(u -> u.getUser().getId() == userId)
                .toList();

        return actUsers.isEmpty() ? null : actUsers.get(0);
    }

    @Override
    public List<Genre> getGenres() {
        List<Genre> genres = source.genres(em)
                .toList();
        return genres.isEmpty() ? null : genres;
    }
}
