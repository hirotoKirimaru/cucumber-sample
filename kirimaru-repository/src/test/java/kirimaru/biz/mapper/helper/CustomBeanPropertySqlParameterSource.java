package kirimaru.biz.mapper.helper;

import java.beans.PropertyDescriptor;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.NotReadablePropertyException;
import org.springframework.beans.PropertyAccessor;
import org.springframework.beans.PropertyAccessorFactory;
import org.springframework.lang.Nullable;
import org.springframework.util.StringUtils;

public class CustomBeanPropertySqlParameterSource {
  private final BeanWrapper beanWrapper;

  @Nullable
  private String[] propertyNames;


  /**
   * Create a new BeanPropertySqlParameterSource for the given bean.
   *
   * @param object the bean instance to wrap
   */
  public CustomBeanPropertySqlParameterSource(Object object) {
    this.beanWrapper = PropertyAccessorFactory.forBeanPropertyAccess(object);
  }


  @Nullable
  public Object getValue(String paramName) throws IllegalArgumentException {
    try {
      return this.beanWrapper.getPropertyValue(paramName);
    } catch (NotReadablePropertyException ex) {
      throw new IllegalArgumentException(ex.getMessage());
    }
  }

  /**
   * Provide access to the property names of the wrapped bean.
   * Uses support provided in the {@link PropertyAccessor} interface.
   *
   * @return an array containing all the known property names
   */
  public String[] getReadablePropertyNames() {
    if (this.propertyNames == null) {
      List<String> names = new ArrayList<>();
      PropertyDescriptor[] props = this.beanWrapper.getPropertyDescriptors();
      for (PropertyDescriptor pd : props) {
        if (isMethod(pd)) {
          continue;
        }

        if (this.beanWrapper.isReadableProperty(pd.getName())) {
          names.add(pd.getName());
        }
      }
      this.propertyNames = StringUtils.toStringArray(names);
    }
    return this.propertyNames;
  }


  private boolean isMethod(PropertyDescriptor pd) {
    return pd.getWriteMethod() == null || pd.getReadMethod() == null;
  }

}
