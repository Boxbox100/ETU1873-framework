package ETU1873.framework.servlet;

import java.util.HashMap;

public class ModelView {
String view;
HashMap <String,Object>data;

    public HashMap <String,Object> getData() {
        return data;
    }

    public void setData(HashMap <String,Object> data) {
        this.data = data;
    }

    public String getView() {
        return view;
    }

    public void setView(String view) {
        this.view = view;
    }

    public ModelView(String view) {

        this.view = view;
        setData(new HashMap<>());
    }

    public ModelView(String view, HashMap <String,Object> data) {
        this.view = view;
        this.data = data;
    }


    public void addItem(String key,Object value)
    {

            this.getData().put(key,value);

    }
}
