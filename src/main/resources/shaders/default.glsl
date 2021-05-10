#type vertex
#version 330 core
layout(location = 0)in vec3 aPos;
layout(location = 1)in vec4 aColor;
layout(location = 2)in vec2 aTexCoords;
layout(location = 3)in float aTexId;

uniform mat4 uView;
uniform mat4 uProjection;
uniform mat4 uModel;

out vec4 fColor;
out vec2 fTexCoords;
out float fTexId;

void main(){
    fColor = aColor;
    fTexCoords = aTexCoords;
    fTexId = aTexId;
    gl_Position = uProjection * uView * vec4(aPos, 1.0);
}

#type fragment
#version 330 core
in vec4 fColor;
in vec2 fTexCoords;
in float fTexId;

out vec4 color;

uniform sampler2D texture_samplers[8];

void main(){
    if(fTexId == -1.0f){
        color = fColor;
    }else{
        int index = int(fTexId);
        color = texture(texture_samplers[index], fTexCoords);
        //color = vec4(fTexId, fTexId, fTexId, 1.0);
    }
}