package sample;

import com.kylecorry.frc.vision.contourFilters.ContourFilter;
import com.kylecorry.frc.vision.filters.TargetFilter;
import com.kylecorry.frc.vision.targetDetection.TargetSpecs;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Kyle on 4/7/2017.
 */
public class Bundle {

    private Map<String, Object> data;

    public Bundle() {
        data = new HashMap<>();
    }

    public void putTargetSpecs(String key, TargetSpecs specs) {
        data.put(key, specs);
    }


    public void putContourFilter(String key, ContourFilter filter){
        data.put(key, filter);
    }

    public void putFilter(String key, TargetFilter filter){
        data.put(key, filter);
    }

    public void putDouble(String key, double value) {
        data.put(key, value);
    }

    public TargetSpecs getTargetSpecs(String key) {
        return (TargetSpecs) data.get(key);
    }

    public ContourFilter getContourFilter(String key){
        return (ContourFilter) data.get(key);
    }

    public TargetFilter getFilter(String key){
        return (TargetFilter) data.get(key);
    }

    public double getDouble(String key) {
        return (Double) data.get(key);
    }

}

