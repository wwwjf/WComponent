package com.common.permission;

public interface Constant {

    /**
     * 权限
     */
    interface Permission {
        int REQUEST_CODE = 1001;
    }

    /**
     * 权限
     */
    interface PermissionTag {
        /**
         * 写数据
         */
        String WRITE_STORAGE = "write_storage";


        /**
         * 相机
         */
        String CAMERA_TAG = "camera_tag";
    }
}
