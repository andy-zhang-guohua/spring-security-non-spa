package andy.backyard.common.utils;

import andy.backyard.common.exception.ApplicationException;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ZhangGuohua on 2017/10/19.
 */
public final class DTOUtils {
    private DTOUtils() {
    }

    /**
     * 构造一个类型为 toDataClass 的对象，并从 fromDataObject 复制属性到该对象
     *
     * @param fromDataObject 来源对象
     * @param toDataClass    目标对象的类
     * @param <D>
     * @return
     */
    public static <D> D to(Object fromDataObject, Class<D> toDataClass) {
        if (fromDataObject == null)
            return null;

        try {
            // 使用反射机制创建新的 Model 对象
            Constructor constructor = toDataClass.getConstructor();
            D toDataObject = (D) constructor.newInstance();

            // 将 fromDataObject 对象 属性复制到 toDataObject 对象 中去
            BeanUtils.copyProperties(toDataObject, fromDataObject);
            return toDataObject;
        } catch (Exception e) {
            throw new ApplicationException(e);
        }
    }

    /**
     * 将对象列表复制成为指定类型的另外一个对象列表
     *
     * @param listFromDataObjects 来源对象列表
     * @param toDataClass         目标对象列表中每个元素的类型
     * @param <M>
     * @return
     */
    public static <M> List<M> to(List listFromDataObjects, Class<M> toDataClass) {
        List<M> listToDataObjects = new ArrayList<>();
        if (listFromDataObjects == null || listFromDataObjects.isEmpty()) {
            return listToDataObjects;
        }
        listFromDataObjects.stream().forEach(e -> listToDataObjects.add(to(e, toDataClass)));
        return listToDataObjects;
    }

    /**
     * 将对象列表复制成为指定类型的另外一个对象列表
     *
     * @param pageFromDataObjects 来源对象列表
     * @param toDataClass         目标对象列表中每个元素的类型
     * @param <M>
     * @return
     */
    public static <M> Page<M> to(Page pageFromDataObjects, Class<M> toDataClass) {
        if (pageFromDataObjects == null || pageFromDataObjects.getNumberOfElements() <= 0) {
            return new PageImpl(null);
        }

        Page<M> pageToDataObjects = pageFromDataObjects.map(new Converter() {
            @Override
            public Object convert(Object source) {
                return to(source, toDataClass);
            }
        });
        return pageToDataObjects;
    }
}
