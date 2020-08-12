package f.inaction.darklaunch.v1;

public class DarkDemo {

    public static void main(String[] args) {
            DarkLaunch darkLaunch = new DarkLaunch();
            DarkFeature darkFeature = darkLaunch.getDarkFeature("call_newapi_getUserById");
            System.out.println(darkFeature.enabled());
            System.out.println(darkFeature.dark(893));
    }

}
