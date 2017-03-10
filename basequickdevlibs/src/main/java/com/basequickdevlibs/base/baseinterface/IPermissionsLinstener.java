package com.basequickdevlibs.base.baseinterface;

import java.util.List;

/**
 */

public interface IPermissionsLinstener {
    void permissionSuccess();

    void permissionDenied(List<String> deniedPermissions);

}
