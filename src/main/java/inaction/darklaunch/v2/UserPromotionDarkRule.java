package inaction.darklaunch.v2;

public class UserPromotionDarkRule implements IDarkFeature {
    @Override
    public boolean enabled() {
        return true;
    }

    @Override
    public boolean dark(long darkTarget) {
        // 编写自己的灰度规则
        return true;
    }

    @Override
    public boolean dark(String darkTarget) {
        // 编写自己的灰度规则
        return false;
    }
}
