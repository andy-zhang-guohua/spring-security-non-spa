package andy.backyard.common.model.geo;

import andy.backyard.common.model.BaseJSONObject;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;

/**
 * 地理坐标
 * Created by ZhangGuohua on 2017/5/18.
 */
@Data
public class GeoLocation extends BaseJSONObject {
    /**
     * 经度
     */
    BigDecimal longitude;
    /**
     * 维度
     */
    BigDecimal latitude;

    /**
     * 名称
     */
    String name;

    /**
     * 描述
     */
    String description;

    public GeoLocation() {
    }

    public static GeoLocation of(String longitude, String latitude, String name) {
        GeoLocation nil = new GeoLocation();
        nil.setLatitude(new BigDecimal(latitude));
        nil.setLongitude(new BigDecimal(longitude));
        nil.setName(name);
        nil.setDescription("");
        return nil;
    }

    public static GeoLocation of(BigDecimal longitude, BigDecimal latitude, String name) {
        GeoLocation nil = new GeoLocation();
        nil.setLatitude(latitude);
        nil.setLongitude(longitude);
        nil.setName(name);
        nil.setDescription("");
        return nil;
    }

    public static GeoLocation NIL() {
        GeoLocation nil = new GeoLocation();
        nil.setLatitude(BigDecimal.ZERO);
        nil.setLongitude(BigDecimal.ZERO);
        nil.setName("");
        nil.setDescription("");
        return nil;
    }

    public boolean isValid() {
        if (longitude == null)
            return false;
        if (this.latitude == null)
            return false;

        if (StringUtils.isBlank(name))
            return false;

        return true;
    }
}
