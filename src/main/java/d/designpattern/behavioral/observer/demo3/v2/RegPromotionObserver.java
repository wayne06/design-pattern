package d.designpattern.behavioral.observer.demo3.v2;

import d.designpattern.behavioral.observer.demo3.PromotionService;
import d.designpattern.behavioral.observer.demo3.RegObserver;

public class RegPromotionObserver implements RegObserver {

    private PromotionService promotionService;

    @Override
    public void handleRegSuccess(long userId) {
        promotionService.issueNewUserExperienceCash(userId);
    }
}
