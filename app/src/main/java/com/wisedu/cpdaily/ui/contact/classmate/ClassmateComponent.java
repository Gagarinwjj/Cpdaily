package com.wisedu.cpdaily.ui.contact.classmate;


import com.wisedu.cpdaily.di.components.NetComponent;
import com.wisedu.cpdaily.di.scope.ScopeFragment;

import dagger.Component;

/**
 * 注入器
 */
@ScopeFragment
@Component(dependencies = NetComponent.class, modules = ClassmateModule.class)
interface ClassmateComponent {
    void inject(ClassmateFragment fragment);
}
