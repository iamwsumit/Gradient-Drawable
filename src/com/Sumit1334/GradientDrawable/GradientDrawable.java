package com.Sumit1334.GradientDrawable;


import android.graphics.drawable.Drawable;
import com.google.appinventor.components.annotations.*;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.*;
import com.google.appinventor.components.runtime.errors.YailRuntimeError;
import com.google.appinventor.components.runtime.util.YailList;

import java.util.ArrayList;
import java.util.HashMap;

@DesignerComponent(version = 1,  description = "This extension creates beautiful drawables with stroke and raddi made by Sumit Kumar",category = ComponentCategory.EXTENSION,
        nonVisible = true,   iconName = "https://community.kodular.io/user_avatar/community.kodular.io/sumit1334/120/82654_2.png")
@SimpleObject(external = true)

public class GradientDrawable extends AndroidNonvisibleComponent implements Component {
    private HashMap<String, android.graphics.drawable.GradientDrawable> drawables=new HashMap<>();
    public GradientDrawable(ComponentContainer container) {
        super(container.$form());
    }

    @SimpleFunction(description = "Set the drawable to any view's background")
    public void SetBackgroundDrawable(String drawableId,AndroidViewComponent component){
        component.getView().setBackgroundColor(16777215);
        component.getView().setBackground((Drawable) drawables.get(drawableId));
    }
    @SimpleEvent(description = "This event raises when drawable created")
    public void DrawableCreated(String id){
        EventDispatcher.dispatchEvent(this,"DrawableCreated",id);
    }
    @SimpleFunction(description = "Creates the background drawable")
    public void CreateDrawable(String id,int backgroundColor){
        android.graphics.drawable.GradientDrawable gdr=new android.graphics.drawable.GradientDrawable();
        gdr.setColor(backgroundColor);
        drawables.put(id,gdr);
        DrawableCreated(id);
    }
    @SimpleFunction(description = "Change the background color of drawable")
    public void SetBackgroundColor(String id,YailList backgroundColorList){
        android.graphics.drawable.GradientDrawable gdr= drawables.get(id);
        String[] arry= backgroundColorList.toStringArray();
        int[] colors=new int[arry.length];
        for (int i=0;i<arry.length;i++){
            colors[i]=Integer.parseInt(arry[i]);
        }
        gdr.setColors(colors);
        drawables.replace(id,gdr);
    }
    @SimpleFunction(description = "Set the shape of drawable")
    public void SetShape(String id,int shape){
        android.graphics.drawable.GradientDrawable gdr= drawables.get(id);
        if (shape==0)
            gdr.setShape(android.graphics.drawable.GradientDrawable.RECTANGLE);
        if (shape==1)
            gdr.setShape(android.graphics.drawable.GradientDrawable.OVAL);
        drawables.replace(id,gdr);
    }
    @SimpleFunction(description = "Set the orentition of drawable")
    public void SetOrientation(String id, int orientation){
        android.graphics.drawable.GradientDrawable gdr= drawables.get(id);
        if (orientation==10)
            gdr.setOrientation(android.graphics.drawable.GradientDrawable.Orientation.LEFT_RIGHT);
        if (orientation==11)
            gdr.setOrientation(android.graphics.drawable.GradientDrawable.Orientation.RIGHT_LEFT);
        if (orientation==12)
            gdr.setOrientation(android.graphics.drawable.GradientDrawable.Orientation.TOP_BOTTOM);
        if (orientation==13)
            gdr.setOrientation(android.graphics.drawable.GradientDrawable.Orientation.BOTTOM_TOP);
        if (orientation==14)
            gdr.setOrientation(android.graphics.drawable.GradientDrawable.Orientation.BL_TR);
        if (orientation==15)
            gdr.setOrientation(android.graphics.drawable.GradientDrawable.Orientation.BR_TL);
        if (orientation==16)
            gdr.setOrientation(android.graphics.drawable.GradientDrawable.Orientation.TL_BR);
        if (orientation==17)
            gdr.setOrientation(android.graphics.drawable.GradientDrawable.Orientation.TR_BL);
    }
    @SimpleProperty(description ="Return the orientation Right Left")
    public int RightToLeft(){
        return 11;
    }
    @SimpleProperty(description ="Return the orientation Left Right")
    public int LeftToRight(){
        return 10;
    }
    @SimpleProperty(description = "Return the Bottom top orientation")
    public int BottomToTop(){
        return 13;
    }
    @SimpleProperty(description = "Returns the orientation top bottom")
    public int TopToBottom(){
        return 12;
    }
    @SimpleProperty(description = "Returns the orientation Bottom left to top right")
    public int BottomLeftToTopRight(){
        return 14;
    }
    @SimpleProperty(description = "Returns the orientation Bottom right to top left")
    public int BottomRightToTopLeft(){
        return 15;
    }
    @SimpleProperty(description = "Returns the orientation Top left to Bottom right")
    public int TopLeftToBottomRight(){
        return 16;
    }
    @SimpleProperty(description = "Returns the orientation Bottom left to top right")
    public int TopRightToBottomLeft(){
        return 17;
    }
    @SimpleFunction(description = "Set the stroke color of drawable")
    public void StrokeColor(String id,int width,int strokeColor){
        if (drawables.containsKey(id)) {
            android.graphics.drawable.GradientDrawable gdr = drawables.get(id);
            gdr.setStroke(width*10, strokeColor);
            drawables.replace(id,gdr);
        }else
            throw new YailRuntimeError("Id not exist","Drawable with this id not found first create drawable with this id");

    }
    @SimpleFunction(description = "Set the corner radii of your drawable")
    public void SetCornerRadius(String id, YailList corners){
            String[] corn=corners.toStringArray();
            if (corn.length==4) {
                float r1 = Float.parseFloat(corn[0])*5;
                float r2 = Float.parseFloat(corn[1])*5;
                float r3 = Float.parseFloat(corn[2])*5;
                float r4 = Float.parseFloat(corn[3])*5;
                float[] arry = new float[8];
                arry[0] = r1;
                arry[1] = r1;
                arry[2] = r2;
                arry[3] = r2;
                arry[4] = r3;
                arry[5] = r3;
                arry[6] = r4;
                arry[7] = r4;
                android.graphics.drawable.GradientDrawable gdr = drawables.get(id);
                gdr.setCornerRadii(arry);
            }
            else
                throw new YailRuntimeError("Lenght must be 4","List length is not 4");
    }
    @SimpleProperty(description = "returns the shape rectangle")
    public int ShapeRectangle(){
        return 0;
    }
    @SimpleProperty(description = "Returns the shape circle")
    public int ShapeCircle(){
        return 1;
    }

}