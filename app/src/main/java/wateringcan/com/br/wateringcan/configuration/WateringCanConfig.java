package wateringcan.com.br.wateringcan.configuration;

/**
 * Created by Bruno on 05/11/2017.
 */

public class WateringCanConfig {

    private static boolean automaticRefresh;

    public static boolean isAutomaticRefresh() {
        return automaticRefresh;
    }

    public static void setAutomaticRefresh(boolean automaticRefresh) {
        WateringCanConfig.automaticRefresh = automaticRefresh;
    }
}
