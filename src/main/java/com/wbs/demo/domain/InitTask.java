package com.wbs.demo.domain;

public enum InitTask {

	PLAN("기획"),
    DESIGN("설계"),
    DEVELOP("개발"),
    TEST("테스트"),
    DEPLOY("배포 및 적용");
    

    private final String displayName;

    InitTask(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
