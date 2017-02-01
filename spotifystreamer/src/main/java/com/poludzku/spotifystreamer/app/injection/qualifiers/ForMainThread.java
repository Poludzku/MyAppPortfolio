package com.poludzku.spotifystreamer.app.injection.qualifiers;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;

/**
 * Created by Jacek on 23/11/2016.
 */


@Qualifier
@Retention(RetentionPolicy.RUNTIME)
public @interface ForMainThread {

}
