package com.nllsdfx.utils.property;



import com.nllsdfx.utils.exception.NotImplementedException;

import java.util.Map;

/**
 * Use this class to access properties inside project or,
 * simply saying, your jar/war. Set methods are not used
 * cause we can't change project files at runtime.
 */
public final class ProjectProperty extends AbstractProperty {

    public ProjectProperty(String fileName) {
        super(fileName);
    }

    @Override
    public final boolean set(String key, String value) {
        throw NotImplementedException.exception();
    }

    @Override
    public final boolean set(Map<String, String> map) {
        throw NotImplementedException.exception();
    }

    @Override
    public final boolean clear() {
        throw NotImplementedException.exception();
    }
}
