package rw.gcktc.cms.material;

import rw.gcktc.webcms.controller.ContentNodeService;

import java.util.List;

/**
 * Created by miha on 04.09.2014.
 */
public interface TypeContentService extends ContentNodeService {
    List<TypeContentNode> getAllTypeContent(String type);
}
