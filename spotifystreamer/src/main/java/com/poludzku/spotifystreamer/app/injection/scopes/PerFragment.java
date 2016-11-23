package com.poludzku.spotifystreamer.app.injection.scopes;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by Jacek on 23/11/2016.
 */


@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface PerFragment {

}
