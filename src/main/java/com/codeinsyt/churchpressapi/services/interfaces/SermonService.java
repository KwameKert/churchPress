package com.codeinsyt.churchpressapi.services.interfaces;

import com.codeinsyt.churchpressapi.models.Sermon;

import java.util.HashMap;

public interface SermonService {


    HashMap<String, Object> createSermon(Sermon sermon);

    HashMap<String, Object> updateSermon(Sermon sermon);

    HashMap<String, Object>  softDeleteSermon(Long id);

    HashMap<String, Object> hardDeleteSermon(Long id);

    HashMap<String, Object> listSermons();

    HashMap<String, Object> getSermon(Long id);

}
