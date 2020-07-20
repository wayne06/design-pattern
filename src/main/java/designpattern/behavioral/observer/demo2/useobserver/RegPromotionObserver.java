package designpattern.behavioral.observer.demo2.useobserver;

import designpattern.behavioral.observer.demo2.PromotionService;

public class RegPromotionObserver implements RegObserver {

    private PromotionService promotionService;

    @Override
    public void handleRegSuccess(long userId) {
        promotionService.issueNewUserExperienceCash(userId);
    }
}
