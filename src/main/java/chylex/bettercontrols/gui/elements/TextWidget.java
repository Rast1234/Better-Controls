package chylex.bettercontrols.gui.elements;

import chylex.bettercontrols.gui.OptionListWidget.OptionWidget;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.util.FormattedCharSequence;
import org.jetbrains.annotations.NotNull;
import java.util.List;

public final class TextWidget implements OptionWidget {
	public static final int LEFT = 0;
	public static final int CENTER = 1;
	
	public static final int WHITE = 0xFF_FF_FF;
	
	private final Component text;
	private int x;
	private int y;
	private final int width;
	private final int height;
	private final int align;
	
	public TextWidget(final int x, final int y, final int width, final int height, final Component text, final int align) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.text = text;
		this.align = align;
	}
	
	public TextWidget(final int x, final int y, final int width, final Component text, final int align) {
		this(x, y, width, 20, text, align);
	}
	
	public TextWidget(final int x, final int y, final int width, final Component text) {
		this(x, y, width, 20, text, LEFT);
	}
	
	@Override
	public int getX() {
		return x;
	}
	
	@Override
	public int getY() {
		return y;
	}
	
	@Override
	public void setX(final int x) {
		this.x = x;
	}
	
	@Override
	public void setY(final int y) {
		this.y = y;
	}
	
	@Override
	public void setFocused(final boolean focused) {}
	
	@Override
	public boolean isFocused() {
		return false;
	}
	
	@Override
	public void render(final @NotNull GuiGraphics graphics, final int mouseX, final int mouseY, final float delta) {
		final Font textRenderer = Minecraft.getInstance().font;
		final List<FormattedCharSequence> lines = textRenderer.split(text, width);
		final int lineHeight = textRenderer.lineHeight + 1;
		
		final int finalX = align == CENTER ? x + (width / 2) - (lines.stream().mapToInt(textRenderer::width).max().orElse(0) / 2) : x;
		final int finalY = y + (height / 2) - (lineHeight * lines.size() / 2) + 1;
		
		for (int i = 0; i < lines.size(); i++) {
			graphics.drawString(textRenderer, lines.get(i), finalX, finalY + (i * lineHeight), WHITE);
		}
	}
}
