package com.giggle.repositories.act;

import com.giggle.models.Act;

import javax.inject.Singleton;
import java.util.List;

/**
 * Created by enda on 01/03/16.
 */
@Singleton
public interface ActRepository {
    List<Act> getActs();
}
