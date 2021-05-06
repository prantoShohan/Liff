import org.lwjgl.*;

import java.nio.*;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.stb.STBEasyFont.*;


public final class EasyFont extends FontDemo {

    private static final int BASE_HEIGHT = 32;

    public EasyFont(String filePath) {
        super(BASE_HEIGHT, filePath);
    }



    @Override
    protected void loop() {
        ByteBuffer charBuffer = BufferUtils.createByteBuffer(text.length() * 270);

        int quads = stb_easy_font_print(0, 0, getText(), null, charBuffer);

        glEnableClientState(GL_VERTEX_ARRAY);
        glVertexPointer(2, GL_FLOAT, 16, charBuffer);

        glClearColor(43f / 255f, 43f / 255f, 200f / 255f, 0f); // BG color
        glColor3f(169f / 255f, 183f / 255f, 198f / 255f); // Text color

        while (!glfwWindowShouldClose(getWindow())) {
            glfwPollEvents();

            glClear(GL_COLOR_BUFFER_BIT);

            float scaleFactor = 1.0f + getScale() * 0.25f;

            glPushMatrix();
            // Zoom
            glScalef(scaleFactor, scaleFactor, 1f);
            // Scroll
            glTranslatef(4.0f, 4.0f - getLineOffset() * getFontHeight(), 0f);

            glDrawArrays(GL_QUADS, 0, quads * 4);

            glPopMatrix();

            glfwSwapBuffers(getWindow());
        }

        glDisableClientState(GL_VERTEX_ARRAY);
    }

}