package com.dagger.di;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

@Scope  //define as a scope
@Retention(RetentionPolicy.RUNTIME) // retain in the final apk
public @interface ActivityScope { //annotation  = @interface

}
