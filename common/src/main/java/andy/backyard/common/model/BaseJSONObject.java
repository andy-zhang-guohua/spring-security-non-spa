package andy.backyard.common.model;

import andy.backyard.common.exception.ApplicationException;
import andy.backyard.common.utils.GsonUtils;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ZhangGuohua on 2016/8/18.
 */
public class BaseJSONObject extends BaseObject {
    private static final long serialVersionUID = 20171013L;


    public String toJSON() {
        String json = toJSON(this);
        return json;
    }

    protected static String toJSON(Object obj) {
        if (obj == null)
            return "";

        String json = GsonUtils.toString(obj);
        return json;
    }

    protected static <T> List<T> fromJSONArray(String json, Type typeOfT) throws ApplicationException {
        String nonNullJSON = "[]";
        if (!StringUtils.isBlank(json)) {
            nonNullJSON = json;
        }
        try {
            Gson gson = new Gson();
            return gson.fromJson(nonNullJSON, typeOfT);
        } catch (JsonSyntaxException e) {
            throw new ApplicationException("将字符串 " + json + " 转化成 " + typeOfT.getTypeName() + " 对象遇到语法错误 ", e);
        }
    }

    public static <T> T fromJSON(String json, Class<T> claz) {
        String nonNullJSON = "{}";
        if (!StringUtils.isBlank(json)) {
            nonNullJSON = json;
        }
        try {
            Gson gson = new Gson();
            return gson.fromJson(nonNullJSON, claz);
        } catch (JsonSyntaxException e) {
            throw new ApplicationException("将字符串 " + json + " 转化成 " + claz.getName() + " 对象遇到语法错误 ", e);
        }
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.DEFAULT_STYLE, false);
    }


    /**
     * 将对象转换成一个 String Map,如果对象属性为null，转成""
     *
     * @return
     */
    public Map<String, String> toMap() {
        try {
            Map<String, String> map = BeanUtils.describe(this);

            Map mapNoNullValueEntries = new HashMap<String, String>();
            for (String name : map.keySet()) {
                if (name.equals("class"))
                    continue;

                String value = StringUtils.defaultString(map.get(name));
                mapNoNullValueEntries.put(name, value);
            }

            return mapNoNullValueEntries;
        } catch (Exception e) {
            throw new ApplicationException(e);
        }
    }


    /**
     * 将对象转换成 Object map
     *
     * @return
     */
    public Map<String, Object> toObjectMap() {
        try {
            Map<String, Object> result = new HashMap<String, Object>();
            BeanInfo info = Introspector.getBeanInfo(this.getClass());
            for (PropertyDescriptor pd : info.getPropertyDescriptors()) {
                Method reader = pd.getReadMethod();
                if (reader != null)
                    result.put(pd.getName(), reader.invoke(this));
            }
            return result;
        } catch (Exception e) {
            throw new ApplicationException("将Bean对象转换成Map对象时遇到异常", e);
        }
    }
}
