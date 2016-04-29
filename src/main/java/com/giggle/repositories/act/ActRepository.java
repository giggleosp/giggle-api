package com.giggle.repositories.act;

import com.giggle.models.Act;
import com.giggle.models.ActCategory;
import com.giggle.models.ActUser;
import com.giggle.models.Genre;

import javax.inject.Singleton;
import java.util.List;

/**
 * Created by enda on 01/03/16.
 */
@Singleton
public interface ActRepository {
    List<Act> getActs();
    List<Act> getActsManagedByUser(long id);
    Act getActById(long id);
    List<ActCategory> actCategories();
    Act createAct(Act act);
    void createActUserRelationship(ActUser userAct);
    ActUser getActUserRelationship(long actId, long userId);
    List<Genre> getGenres();
}
