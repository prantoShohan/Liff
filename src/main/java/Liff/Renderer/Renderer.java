package Liff.Renderer;

import Liff.Shapes.Shape;

import java.util.ArrayList;
import java.util.List;

public class Renderer {

    private static List<DrawData> drawDataList;
    private static List<DrawCall> drawCalls;
    private static Renderer instance = null;

    private Renderer(){
        drawDataList = new ArrayList<>();
        drawCalls = new ArrayList<>();
    }
    public static Renderer get(){
        if(instance == null){
            instance = new Renderer();
        }
        return instance;
    }

    public static void submit(Shape shape, Shader shader, Camera camera){
        drawDataList.add(new DrawData(shape, shader, camera));
    }

    public static void processDrawCall(){
        DrawCall currentDrawCall = null;
        for(DrawData d : drawDataList){
            if(currentDrawCall == null){
                currentDrawCall = new DrawCall(d.shader, d.camera);
                currentDrawCall.addDrawData(d);
                drawCalls.add(currentDrawCall);
            }else{
                if(currentDrawCall.isSameAs(d)){
                    currentDrawCall.addDrawData(d);
                    drawCalls.add(currentDrawCall);
                }
                else{
                    drawCalls.add(currentDrawCall);
                    currentDrawCall = new DrawCall(d.shader, d.camera);
                    currentDrawCall.addDrawData(d);
                }
            }
        }
    }

    public static void executeDrawCalls(){
        for(DrawCall d: drawCalls){
            d.processDrawData();
            d.render();
        }
    }

}
