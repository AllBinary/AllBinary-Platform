/*
* AllBinary Open License Version 1
* Copyright (c) 2011 AllBinary
* 
* By agreeing to this license you and any business entity you represent are
* legally bound to the AllBinary Open License Version 1 legal agreement.
* 
* You may obtain the AllBinary Open License Version 1 legal agreement from
* AllBinary or the root directory of AllBinary's AllBinary Platform repository.
* 
* Created By: Travis Berthelot
* 
*/
package org.allbinary.animation;

import java.util.Enumeration;

import org.allbinary.logic.math.PrimitiveIntUtil;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.math.PositionStrings;
import org.allbinary.string.CommonSeps;
import org.allbinary.util.BasicArrayList;
import org.allbinary.util.BasicArrayListD;

import org.json.me.JSONArray;
import org.json.me.JSONException;
import org.json.me.JSONObject;

public class SVGAsJSONSimpleLoader
{
    private static final SVGAsJSONSimpleLoader instance = new SVGAsJSONSimpleLoader();

    /**
     * @return the instance
     */
    public static SVGAsJSONSimpleLoader getInstance() {
        return instance;
    }
    
    private final ShapeTypeFactory shapeTypeFactory = ShapeTypeFactory.getInstance();
    
    private final String KEY_SVG = "svg";
    private final String KEY_X1 = "x1";
    private final String KEY_Y1 = "y1";
    private final String KEY_X2 = "x2";
    private final String KEY_Y2 = "y2";
    private final String KEY_CX = "cx";
    private final String KEY_CY = "cy";
    private final String KEY_R = "r";
    private final String KEY_X = PositionStrings.getInstance().X;
    private final String KEY_Y = PositionStrings.getInstance().Y;
    private final String KEY_WIDTH = "width";
    private final String KEY_HEIGHT = "height";

    private final String ERROR_PARSE_SVG_JSON = "Unable to parse SVG JSON";
    private final String ERROR_MISSING_KEY_PREFIX = "Missing key: ";
    private final String ERROR_INVALID_NUMBER_PREFIX = "Invalid number for key: ";
    private final String ERROR_EXPECTED_OBJECT_PREFIX = "Expected object for: ";

    private final int[][] EMPTY_POINTS = PrimitiveIntUtil.getTwoDimensionalArrayInstance();

    private final int[] SHAPE_SEPARATOR = {1000, 1000};

    private final int CIRCLE_SEGMENTS = 16;

    public int[][] load(final String svgAsJson) throws Exception
    {
        if(svgAsJson == null)
        {
            return EMPTY_POINTS;
        }

        try
        {
            final JSONObject root = new JSONObject(svgAsJson);
            final Object svgValue = root.opt(KEY_SVG);
            final JSONObject svg = svgValue == null ? root : this.asObject(svgValue, KEY_SVG);

            final BasicArrayList pointVector = new BasicArrayListD();
            final Enumeration keys = svg.keys();
            while(keys.hasMoreElements())
            {
                final String key = (String) keys.nextElement();
                final Object value = svg.opt(key);
                if(this.shapeTypeFactory.KEY_LINE.equals(key))
                {
                    this.addShapes(pointVector, value, this.shapeTypeFactory.LINE);
                }
                else if(this.shapeTypeFactory.equals(key))
                {
                    this.addShapes(pointVector, value, this.shapeTypeFactory.CIRCLE);
                }
                else if(this.shapeTypeFactory.KEY_RECT.equals(key) || this.shapeTypeFactory.KEY_RECTANGLE.equals(key))
                {
                    this.addShapes(pointVector, value, this.shapeTypeFactory.RECTANGLE);
                }
            }

            return this.toPoints(pointVector);
        }
        catch(final JSONException e)
        {
            throw new IllegalArgumentException(ERROR_PARSE_SVG_JSON, e);
        }
    }

    private void addShapes(final BasicArrayList pointVector, final Object value, final ShapeType shapeType) throws Exception
    {
        if(value instanceof JSONArray)
        {
            final JSONArray array = (JSONArray) value;
            final int size = array.length();
            for(int index = 0; index < size; index++)
            {
                this.addShape(pointVector, array.opt(index), shapeType);
            }
        }
        else
        {
            this.addShape(pointVector, value, shapeType);
        }
    }

    private void addShape(final BasicArrayList pointVector, final Object value, final ShapeType shapeType) throws Exception
    {
        final JSONObject shape = this.asObject(value, shapeType.getName());

        if(shapeType == this.shapeTypeFactory.LINE)
        {
            this.addLine(pointVector, shape);
        }
        else if(shapeType == this.shapeTypeFactory.CIRCLE)
        {
            this.addCircle(pointVector, shape);
        }
        else
        {
            this.addRectangle(pointVector, shape);
        }
    }

    private void addLine(final BasicArrayList pointVector, final JSONObject line) throws Exception
    {
        this.addPoint(pointVector, this.readInt(line, KEY_X1), this.readInt(line, KEY_Y1));
        this.addPoint(pointVector, this.readInt(line, KEY_X2), this.readInt(line, KEY_Y2));
        this.addSeparator(pointVector);
    }

    private void addCircle(final BasicArrayList pointVector, final JSONObject circle) throws Exception
    {
        final int cx = this.readInt(circle, KEY_CX);
        final int cy = this.readInt(circle, KEY_CY);
        final int radius = this.readInt(circle, KEY_R);

        for(int index = 0; index <= CIRCLE_SEGMENTS; index++)
        {
            final double angle = (Math.PI * 2.0d * index) / CIRCLE_SEGMENTS;
            final int x = (int) Math.round(cx + (Math.cos(angle) * radius));
            final int y = (int) Math.round(cy + (Math.sin(angle) * radius));
            this.addPoint(pointVector, x, y);
        }

        this.addSeparator(pointVector);
    }

    private void addRectangle(final BasicArrayList pointVector, final JSONObject rectangle) throws Exception
    {
        final int x = this.readInt(rectangle, KEY_X, 0);
        final int y = this.readInt(rectangle, KEY_Y, 0);
        final int width = this.readInt(rectangle, KEY_WIDTH);
        final int height = this.readInt(rectangle, KEY_HEIGHT);

        this.addPoint(pointVector, x, y);
        this.addPoint(pointVector, x + width, y);
        this.addPoint(pointVector, x + width, y + height);
        this.addPoint(pointVector, x, y + height);
        this.addPoint(pointVector, x, y);
        this.addSeparator(pointVector);
    }

    private void addPoint(final BasicArrayList pointVector, final int x, final int y)
    {
        pointVector.add(new int[] {x, y});
    }

    private void addSeparator(final BasicArrayList pointVector)
    {
        pointVector.add(new int[] {SHAPE_SEPARATOR[0], SHAPE_SEPARATOR[1]});
    }

    private int readInt(final JSONObject object, final String key) throws Exception
    {
        final Object value = object.get(key);
        if(value == null)
        {
            throw new IllegalArgumentException(ERROR_MISSING_KEY_PREFIX + key);
        }
        return this.toInt(value, key);
    }

    private int readInt(final JSONObject object, final String key, final int defaultValue)
    {
        try {
            if (object.has(key)) {
                final Object value = object.get(key);
                return this.toInt(value, key);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return defaultValue;
    }

    private int toInt(final Object value, final String key)
    {
        if(value instanceof Number)
        {
            return (int) Math.round(((Number) value).doubleValue());
        }
        if(value instanceof String)
        {
            return (int) Math.round(Double.parseDouble((String) value));
        }
        throw new IllegalArgumentException(ERROR_INVALID_NUMBER_PREFIX + key);
    }

    private JSONObject asObject(final Object value, final String name)
    {
        if(value instanceof JSONObject)
        {
            return (JSONObject) value;
        }
        throw new IllegalArgumentException(ERROR_EXPECTED_OBJECT_PREFIX + name);
    }

    private int[][] toPoints(final BasicArrayList pointVector)
    {
        if(pointVector.size() == 0)
        {
            return EMPTY_POINTS;
        }

        final int[][] points = new int[pointVector.size()][2];
        for(int index = 0; index < pointVector.size(); index++)
        {
            final int[] point = (int[]) pointVector.get(index);
            points[index] = point;
        }
        return points;
    }
    
    public static void main(final String[] args) throws Exception {
        final String svgAsJson = "{\"svg\": {\n" +
"    \"rect\": {\n" +
"        \"width\": 20,\n" +
"        \"fill\": \"#008c00\",\n" +
"        \"height\": 20\n" +
"    },\n" +
"    \"xmlns\": \"http://www.w3.org/2000/svg\",\n" +
"    \"width\": 20,\n" +
"    \"height\": 20\n" +
"}}\n";
//        "{\"svg\": {\n" +
//"    \"xmlns\": \"http://www.w3.org/2000/svg\",\n" +
//"    \"width\": 20,\n" +
//"    \"circle\": {\n" +
//"        \"r\": 8,\n" +
//"        \"cx\": 10,\n" +
//"        \"cy\": 10,\n" +
//"        \"fill\": \"#ff2020\"\n" +
//"    },\n" +
//"    \"height\": 20\n" +
//"}}\n";

        final int[][] points = SVGAsJSONSimpleLoader.getInstance().load(svgAsJson);
        
        final StringMaker stringBuilder = new StringMaker();
        final int size = points.length;
        for(int index = 0; index < size; index++) {
            final int size2 = points[index].length;
            for(int index2 = 0; index2 < size2; index2++) {
                stringBuilder.appendint(points[index][index2]);
                stringBuilder.append(CommonSeps.getInstance().COMMA);
            }
        }
        System.out.println(stringBuilder.toString());
    }
    
}