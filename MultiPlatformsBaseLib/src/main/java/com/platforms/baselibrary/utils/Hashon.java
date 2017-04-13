package com.platforms.baselibrary.utils;

import android.text.TextUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by nightq on 2017/4/9.
 */

public class Hashon {
    public Hashon() {
    }

    public <T> HashMap<String, T> fromJson(String jsonStr) {
        if(TextUtils.isEmpty(jsonStr)) {
            return new HashMap();
        } else {
            try {
                if(jsonStr.startsWith("[") && jsonStr.endsWith("]")) {
                    jsonStr = "{\"fakelist\":" + jsonStr + "}";
                }

                JSONObject t = new JSONObject(jsonStr);
                return this.fromJson(t);
            } catch (Throwable var3) {
                return new HashMap();
            }
        }
    }

    private <T> HashMap<String, T> fromJson(JSONObject json) throws JSONException {
        HashMap map = new HashMap();
        Iterator iKey = json.keys();

        while(iKey.hasNext()) {
            String key = (String)iKey.next();
            Object value = json.opt(key);
            if(JSONObject.NULL.equals(value)) {
                value = null;
            }

            if(value != null) {
                if(value instanceof JSONObject) {
                    value = this.fromJson((JSONObject)value);
                } else if(value instanceof JSONArray) {
                    value = this.fromJson((JSONArray)value);
                }

                map.put(key, value);
            }
        }

        return map;
    }

    private ArrayList<Object> fromJson(JSONArray array) throws JSONException {
        ArrayList list = new ArrayList();
        int i = 0;

        for(int size = array.length(); i < size; ++i) {
            Object value = array.opt(i);
            if(value instanceof JSONObject) {
                value = this.fromJson((JSONObject)value);
            } else if(value instanceof JSONArray) {
                value = this.fromJson((JSONArray)value);
            }

            list.add(value);
        }

        return list;
    }

    public <T> String fromHashMap(HashMap<String, T> map) {
        try {
            JSONObject t = this.getJSONObject(map);
            return t == null?"":t.toString();
        } catch (Throwable var3) {
            return "";
        }
    }

    private <T> JSONObject getJSONObject(HashMap<String, T> map) throws JSONException {
        JSONObject json = new JSONObject();

        Map.Entry entry;
        Object value;
        for(Iterator var3 = map.entrySet().iterator(); var3.hasNext(); json.put((String)entry.getKey(), value)) {
            entry = (Map.Entry)var3.next();
            value = entry.getValue();
            if(value instanceof HashMap) {
                value = this.getJSONObject((HashMap)value);
            } else if(value instanceof ArrayList) {
                value = this.getJSONArray((ArrayList)value);
            } else if(this.isBasicArray(value)) {
                value = this.getJSONArray(this.arrayToList(value));
            }
        }

        return json;
    }

    private boolean isBasicArray(Object value) {
        return value instanceof byte[] || value instanceof short[] || value instanceof int[] || value instanceof long[] || value instanceof float[] || value instanceof double[] || value instanceof char[] || value instanceof boolean[] || value instanceof String[];
    }

    private ArrayList<Object> arrayToList(Object value) {
        ArrayList list;
        int var4;
        int var5;
        if(value instanceof byte[]) {
            list = new ArrayList();
            byte[] var15 = (byte[])((byte[])value);
            var4 = var15.length;

            for(var5 = 0; var5 < var4; ++var5) {
                byte var23 = var15[var5];
                list.add(Byte.valueOf(var23));
            }

            return list;
        } else if(value instanceof short[]) {
            list = new ArrayList();
            short[] var14 = (short[])((short[])value);
            var4 = var14.length;

            for(var5 = 0; var5 < var4; ++var5) {
                short var22 = var14[var5];
                list.add(Short.valueOf(var22));
            }

            return list;
        } else if(value instanceof int[]) {
            list = new ArrayList();
            int[] var13 = (int[])((int[])value);
            var4 = var13.length;

            for(var5 = 0; var5 < var4; ++var5) {
                int var21 = var13[var5];
                list.add(Integer.valueOf(var21));
            }

            return list;
        } else if(value instanceof long[]) {
            list = new ArrayList();
            long[] var12 = (long[])((long[])value);
            var4 = var12.length;

            for(var5 = 0; var5 < var4; ++var5) {
                long var20 = var12[var5];
                list.add(Long.valueOf(var20));
            }

            return list;
        } else if(value instanceof float[]) {
            list = new ArrayList();
            float[] var11 = (float[])((float[])value);
            var4 = var11.length;

            for(var5 = 0; var5 < var4; ++var5) {
                float var19 = var11[var5];
                list.add(Float.valueOf(var19));
            }

            return list;
        } else if(value instanceof double[]) {
            list = new ArrayList();
            double[] var10 = (double[])((double[])value);
            var4 = var10.length;

            for(var5 = 0; var5 < var4; ++var5) {
                double var18 = var10[var5];
                list.add(Double.valueOf(var18));
            }

            return list;
        } else if(value instanceof char[]) {
            list = new ArrayList();
            char[] var9 = (char[])((char[])value);
            var4 = var9.length;

            for(var5 = 0; var5 < var4; ++var5) {
                char var17 = var9[var5];
                list.add(Character.valueOf(var17));
            }

            return list;
        } else if(value instanceof boolean[]) {
            list = new ArrayList();
            boolean[] var8 = (boolean[])((boolean[])value);
            var4 = var8.length;

            for(var5 = 0; var5 < var4; ++var5) {
                boolean var16 = var8[var5];
                list.add(Boolean.valueOf(var16));
            }

            return list;
        } else if(!(value instanceof String[])) {
            return null;
        } else {
            list = new ArrayList();
            String[] var3 = (String[])((String[])value);
            var4 = var3.length;

            for(var5 = 0; var5 < var4; ++var5) {
                String item = var3[var5];
                list.add(item);
            }

            return list;
        }
    }

    private JSONArray getJSONArray(ArrayList<Object> list) throws JSONException {
        JSONArray array = new JSONArray();

        Object value;
        for(Iterator var3 = list.iterator(); var3.hasNext(); array.put(value)) {
            value = var3.next();
            if(value instanceof HashMap) {
                value = this.getJSONObject((HashMap)value);
            } else if(value instanceof ArrayList) {
                value = this.getJSONArray((ArrayList)value);
            }
        }

        return array;
    }

    public String format(String jsonStr) {
        try {
            return this.format("", this.fromJson(jsonStr));
        } catch (Throwable var3) {
            return "";
        }
    }

    private String format(String sepStr, HashMap<String, Object> map) {
        StringBuffer sb = new StringBuffer();
        sb.append("{\n");
        String mySepStr = sepStr + "\t";
        int i = 0;

        for(Iterator var6 = map.entrySet().iterator(); var6.hasNext(); ++i) {
            Map.Entry entry = (Map.Entry)var6.next();
            if(i > 0) {
                sb.append(",\n");
            }

            sb.append(mySepStr).append('\"').append((String)entry.getKey()).append("\":");
            Object value = entry.getValue();
            if(value instanceof HashMap) {
                sb.append(this.format(mySepStr, (HashMap)value));
            } else if(value instanceof ArrayList) {
                sb.append(this.format(mySepStr, (ArrayList)value));
            } else if(value instanceof String) {
                sb.append('\"').append(value).append('\"');
            } else {
                sb.append(value);
            }
        }

        sb.append('\n').append(sepStr).append('}');
        return sb.toString();
    }

    private String format(String sepStr, ArrayList<Object> list) {
        StringBuffer sb = new StringBuffer();
        sb.append("[\n");
        String mySepStr = sepStr + "\t";
        int i = 0;

        for(Iterator var6 = list.iterator(); var6.hasNext(); ++i) {
            Object value = var6.next();
            if(i > 0) {
                sb.append(",\n");
            }

            sb.append(mySepStr);
            if(value instanceof HashMap) {
                sb.append(this.format(mySepStr, (HashMap)value));
            } else if(value instanceof ArrayList) {
                sb.append(this.format(mySepStr, (ArrayList)value));
            } else if(value instanceof String) {
                sb.append('\"').append(value).append('\"');
            } else {
                sb.append(value);
            }
        }

        sb.append('\n').append(sepStr).append(']');
        return sb.toString();
    }
}
