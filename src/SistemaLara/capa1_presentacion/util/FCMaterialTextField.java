/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SistemaLara.capa1_presentacion.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.util.concurrent.TimeUnit;
import javax.swing.JComponent;
import javax.swing.JTextField;
import javax.swing.text.DefaultCaret;
import org.jdesktop.core.animation.timing.Animator;
import org.jdesktop.core.animation.timing.interpolators.SplineInterpolator;
import org.jdesktop.swing.animation.timing.sources.SwingTimerTimingSource;

/**
 *
 * @author XGamer
 */
public class FCMaterialTextField extends JTextField {

    public static final int HINT_OPACITY_MASK = 0x99000000;
    public static final int LINE_OPACITY_MASK = 0x66000000;

    private FloatingLabel floatingLabel = new FloatingLabel(this);
    private Line line = new Line(this);
    private String hint = "";
    private Color accentColor = FCMaterialColor.CYAN_500;
    Font myFont = new Font("Tahoma", 1, 12);

    public FCMaterialTextField() {
        super();
        setBorder(null);
        setFont(myFont);
        floatingLabel.setText("");
        setOpaque(false);
        setBackground(FCMaterialColor.TRANSPARENT);
        setCaret(new DefaultCaret() {
            @Override
            protected synchronized void damage(Rectangle r) {
                FCMaterialTextField.this.repaint(); //fix caret not being removed completely
            }
        });
        getCaret().setBlinkRate(500);
    }

    public FCMaterialTextField(String text) {
        this();
        setText(text);
    }

    /**
     * Gets the label text. The label will float above any contents input into
     * this text field.
     *
     * @return the text being used in the floating label
     */
    public String getLabel() {
        return floatingLabel.getText();
    }

    public void setLabel(String label) {
        floatingLabel.setText(label);
        repaint();
    }

    /**
     * Gets the hint text. The hint text is displayed when this textfield is
     * empty.
     *
     * @return the text being used as hint
     */
    public String getHint() {
        return hint;
    }

    /**
     * Sets the hint text. The hint text is displayed when this textfield is
     * empty.
     *
     * @param hint the text to use as hint
     */
    public void setHint(String hint) {
        this.hint = hint;
        repaint();
    }

    /**
     * Gets the color the label changes to when this {@code materialTextField}
     * is focused.
     *
     * @return the {@code "Color"} currently in use for accent. The default
     * value is {@link FCMaterialColor#CYAN_300}.
     */
    public Color getAccent() {
        return accentColor;
    }

    /**
     * Sets the color the label changes to when this {@code materialTextField}
     * is focused. The default value is {@link FCMaterialColor#CYAN_300}.
     *
     * @param accentColor the {@code "Color"} that should be used for accent.
     */
    public void setAccent(Color accentColor) {
        this.accentColor = accentColor;
        floatingLabel.setAccent(accentColor);
    }

    @Override
    public void setForeground(Color fg) {
        super.setForeground(fg);
        if (floatingLabel != null) {
            floatingLabel.updateForeground();
        }
    }

    @Override
    public void setText(String s) {
        super.setText(s);
        floatingLabel.update();
        line.update();
    }

    @Override
    protected void processFocusEvent(FocusEvent e) {
        super.processFocusEvent(e);
        floatingLabel.update();
        line.update();
    }

    @Override
    protected void processKeyEvent(KeyEvent e) {
        super.processKeyEvent(e);
        floatingLabel.update();
        line.update();
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        g2.setColor(getBackground());
        g2.fillRect(0, 0, getWidth(), getHeight());

        g2.translate(0, 9);
        super.paintComponent(g);
        g2.translate(0, -9);

        if (!getHint().isEmpty() && getText().isEmpty() && (getLabel().isEmpty() || isFocusOwner()) && floatingLabel.isFloatingAbove()) {
            g.setFont(myFont);
            g2.setColor(FCUtilsMaterial.applyAlphaMask(getForeground(), HINT_OPACITY_MASK));
            FontMetrics metrics = g.getFontMetrics(g.getFont());
            g.drawString(getHint(), 0, metrics.getAscent() + 36);
        }

        floatingLabel.paint(g2);

        g2.setColor(FCUtilsMaterial.applyAlphaMask(getForeground(), LINE_OPACITY_MASK));
        g2.fillRect(0, getHeight() - 9, getWidth(), 1);

        g2.setColor(accentColor);
        g2.fillRect((int) ((getWidth() - line.getWidth()) / 2), getHeight() - 10, (int) line.getWidth(), 2);
    }

    @Override
    protected void paintBorder(Graphics g) {
        //intentionally left blank
    }

    /**
     * An animated line that appears below a component when it is focused.
     */
    public static class Line {

        private final SwingTimerTimingSource timer;
        private final JComponent target;
        private Animator animator;
        private FCSafePropertySetter.Property<Double> width;

        public Line(JComponent target) {
            this.target = target;
            this.timer = new SwingTimerTimingSource();
            timer.init();
            width = FCSafePropertySetter.animatableProperty(target, 0d);
        }

        public void update() {
            if (animator != null) {
                animator.stop();
            }
            animator = new Animator.Builder(timer)
                    .setDuration(200, TimeUnit.MILLISECONDS)
                    .setEndBehavior(Animator.EndBehavior.HOLD)
                    .setInterpolator(new SplineInterpolator(0.4, 0, 0.2, 1))
                    .addTarget(FCSafePropertySetter.getTarget(width, width.getValue(), target.isFocusOwner() ? (double) target.getWidth() + 1 : 0d))
                    .build();
            animator.start();
        }
        public double getWidth() {
            return width.getValue();
        }
    }

    /**
     * A floating label of a text field.
     */
    public static class FloatingLabel {
        private final SwingTimerTimingSource timer;
        private final JTextField target;
        private Animator animator;
        private final FCSafePropertySetter.Property<Double> y;
        private final FCSafePropertySetter.Property<Double> fontSize;
        private final FCSafePropertySetter.Property<Color> color;
        private String text;
        private Color accentColor = FCMaterialColor.CYAN_500;

        FloatingLabel(JTextField target) {
            this.target = target;
            this.timer = new SwingTimerTimingSource();
            timer.init();
            y = FCSafePropertySetter.animatableProperty(target, 36d);
            fontSize = FCSafePropertySetter.animatableProperty(target, 16d);
            color = FCSafePropertySetter.animatableProperty(target, FCMaterialColor.MIN_BLACK);
            updateForeground();
        }

        public void updateForeground() {
            color.setValue(FCUtilsMaterial.applyAlphaMask(target.getForeground(), HINT_OPACITY_MASK));
        }

        public Color getAccent() {
            return accentColor;
        }

        public void setAccent(Color accentColor) {
            this.accentColor = accentColor;
        }

        void update() {
            if (animator != null) {
                animator.stop();
            }
            Animator.Builder builder = new Animator.Builder(timer)
                    .setDuration(200, TimeUnit.MILLISECONDS)
                    .setEndBehavior(Animator.EndBehavior.HOLD)
                    .setInterpolator(new SplineInterpolator(0.4, 0, 0.2, 1));
            double targetFontSize = (target.isFocusOwner() || !target.getText().isEmpty()) ? 12d : 16d;
            if (fontSize.getValue() != targetFontSize) {
                builder.addTarget(FCSafePropertySetter.getTarget(fontSize, fontSize.getValue(), targetFontSize));
            }
            double targetY = target.isFocusOwner() || !target.getText().isEmpty() ? 16d : 36d;
            if (Math.abs(targetY - y.getValue()) > 0.1) {
                builder.addTarget(FCSafePropertySetter.getTarget(y, y.getValue(), targetY));
            }
            Color targetColor;
            if (target.isFocusOwner()) {
                targetColor = accentColor;
            } else {
                targetColor = FCUtilsMaterial.applyAlphaMask(target.getForeground(), HINT_OPACITY_MASK);
            }
            if (!targetColor.equals(color.getValue())) {
                builder.addTarget(FCSafePropertySetter.getTarget(color, color.getValue(), targetColor));
            }
            animator = builder.build();
            animator.start();
        }

        String getText() {
            return text;
        }

        void setText(String text) {
            this.text = text;
        }

        void paint(Graphics2D g) {
            Font font = new Font("Tahoma", 1, 12);
            g.setFont(font);
            g.setColor(color.getValue());
            FontMetrics metrics = g.getFontMetrics(g.getFont());
            g.drawString(getText(), 0, metrics.getAscent() + y.getValue().intValue());
        }

        boolean isFloatingAbove() {
            return y.getValue() < 17d;
        }
    }
}
