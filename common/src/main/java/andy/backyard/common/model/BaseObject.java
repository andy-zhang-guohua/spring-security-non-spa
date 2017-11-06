/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package andy.backyard.common.model;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

/**
 * Created by ZhangGuohua on 2016/8/18.
 */
public abstract class BaseObject implements Serializable {

    private static final long serialVersionUID = 20171013L;

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.MULTI_LINE_STYLE, false);
    }
}
