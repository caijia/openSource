package com.caijia.lib;

import java.util.List;

/**
 * Created by yt on 2017/11/17.
 */

public interface ApiService {

    void testQuery(@Query("name") String name,
                   @Query("name1") List<String> name1,
                   @Query("name2") List<? extends String> name2,
                   @Query("name3") List<String>[] name3);
}
