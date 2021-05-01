package Liff.Renderer;

import Liff.Shapes.Shape;
import org.joml.Vector2f;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class Renderer {

    private static List<DrawData> drawDataList;
    private static List<DrawCall> drawCalls;
    private static Renderer instance = null;
    private static Hashtable<String, Shader> shaderLibrary = new Hashtable<>();
    private static Hashtable<String, Camera> cameraLibrary = new Hashtable<>();

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
                //drawCalls.add(currentDrawCall);
            }else{
                if(currentDrawCall.isSameAs(d)){
                    currentDrawCall.addDrawData(d);
                    //drawCalls.add(currentDrawCall);
                }
                else{
                    drawCalls.add(currentDrawCall);
                    currentDrawCall = new DrawCall(d.shader, d.camera);
                    currentDrawCall.addDrawData(d);

                }
            }
        }
        //TODO position this on appropriate place
        drawCalls.add(currentDrawCall);
    }

    public static void executeDrawCalls(){
        for(DrawCall d: drawCalls){
            d.processDrawData();
            d.render();
        }
    }

    public static void addShader(String name, String path){
        shaderLibrary.put(name, new Shader(path));
        shaderLibrary.get(name).compile();
    }

    public static Shader getShader(String name){
        if(shaderLibrary.containsKey(name)){
            return shaderLibrary.get(name);
        }
        System.out.println(name +" shader not found in shader library.");
        return null;
    }

    public static void addCamera(String name, Vector2f vec){
        cameraLibrary.put(name, new Camera(vec));
    }

    public static Camera getCamera(String name){
        if(cameraLibrary.containsKey(name)){
            return cameraLibrary.get(name);
        }
        System.out.println(name +" camera not found in shader library.");
        return null;
    }

}
