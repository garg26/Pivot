package simplifii.framework.utility;

import java.util.Collection;

/**
 * Created by robin on 2/28/17.
 */

public class CollectionUtils {

    public static boolean isEmpty(Collection collection){
        return (collection==null||collection.isEmpty());
    }

    public static boolean isNotEmpty(Collection collection){
        return !isEmpty(collection);
    }
}
