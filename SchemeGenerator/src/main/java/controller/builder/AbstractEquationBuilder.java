package controller.builder;

import model.AppContext;
import model.IBaseModel;

import java.util.Map;

/**
 * Created by Volodymyr_Kychak on 7/25/14.
 */
public class AbstractEquationBuilder {

    protected  <T> T getModel(Class<T> clazz) {
        Map<String, IBaseModel>  map = AppContext.getInstance().getModels();
        if (map.containsKey(clazz.getName())){
            return (T) AppContext.getInstance().getModels().get(clazz.getName());
        }
        return null;
    }
}
