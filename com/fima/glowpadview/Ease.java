package com.fima.glowpadview;

import android.animation.TimeInterpolator;

class Ease {
    private static final float DOMAIN = 1.0f;
    private static final float DURATION = 1.0f;
    private static final float START = 0.0f;

    static class Cubic {
        public static final TimeInterpolator easeIn;
        public static final TimeInterpolator easeInOut;
        public static final TimeInterpolator easeOut;

        /* renamed from: com.fima.glowpadview.Ease.Cubic.1 */
        class C00701 implements TimeInterpolator {
            C00701() {
            }

            public float getInterpolation(float f) {
                float f2 = f / Ease.DURATION;
                return (f2 * ((Ease.DURATION * f2) * f2)) + 0.0f;
            }
        }

        /* renamed from: com.fima.glowpadview.Ease.Cubic.2 */
        class C00712 implements TimeInterpolator {
            C00712() {
            }

            public float getInterpolation(float f) {
                float f2 = (f / Ease.DURATION) - Ease.DURATION;
                return (((f2 * (f2 * f2)) + Ease.DURATION) * Ease.DURATION) + 0.0f;
            }
        }

        /* renamed from: com.fima.glowpadview.Ease.Cubic.3 */
        class C00723 implements TimeInterpolator {
            C00723() {
            }

            public float getInterpolation(float f) {
                float f2 = f / 0.5f;
                if (f2 < Ease.DURATION) {
                    return (f2 * ((0.5f * f2) * f2)) + 0.0f;
                }
                f2 -= 2.0f;
                return (((f2 * (f2 * f2)) + 2.0f) * 0.5f) + 0.0f;
            }
        }

        static {
            easeIn = new C00701();
            easeOut = new C00712();
            easeInOut = new C00723();
        }

        Cubic() {
        }
    }

    static class Linear {
        public static final TimeInterpolator easeNone;

        /* renamed from: com.fima.glowpadview.Ease.Linear.1 */
        class C00731 implements TimeInterpolator {
            C00731() {
            }

            public float getInterpolation(float f) {
                return f;
            }
        }

        static {
            easeNone = new C00731();
        }

        Linear() {
        }
    }

    static class Quad {
        public static final TimeInterpolator easeIn;
        public static final TimeInterpolator easeInOut;
        public static final TimeInterpolator easeOut;

        /* renamed from: com.fima.glowpadview.Ease.Quad.1 */
        class C00741 implements TimeInterpolator {
            C00741() {
            }

            public float getInterpolation(float f) {
                float f2 = f / Ease.DURATION;
                return (f2 * (Ease.DURATION * f2)) + 0.0f;
            }
        }

        /* renamed from: com.fima.glowpadview.Ease.Quad.2 */
        class C00752 implements TimeInterpolator {
            C00752() {
            }

            public float getInterpolation(float f) {
                float f2 = f / Ease.DURATION;
                return ((-1.0f * f2) * (f2 - 2.0f)) + 0.0f;
            }
        }

        /* renamed from: com.fima.glowpadview.Ease.Quad.3 */
        class C00763 implements TimeInterpolator {
            C00763() {
            }

            public float getInterpolation(float f) {
                float f2 = f / 0.5f;
                if (f2 < Ease.DURATION) {
                    return (f2 * (0.5f * f2)) + 0.0f;
                }
                f2 -= Ease.DURATION;
                return (((f2 * (f2 - 2.0f)) - Ease.DURATION) * -0.5f) + 0.0f;
            }
        }

        static {
            easeIn = new C00741();
            easeOut = new C00752();
            easeInOut = new C00763();
        }

        Quad() {
        }
    }

    static class Quart {
        public static final TimeInterpolator easeIn;
        public static final TimeInterpolator easeInOut;
        public static final TimeInterpolator easeOut;

        /* renamed from: com.fima.glowpadview.Ease.Quart.1 */
        class C00771 implements TimeInterpolator {
            C00771() {
            }

            public float getInterpolation(float f) {
                float f2 = f / Ease.DURATION;
                return (f2 * (((Ease.DURATION * f2) * f2) * f2)) + 0.0f;
            }
        }

        /* renamed from: com.fima.glowpadview.Ease.Quart.2 */
        class C00782 implements TimeInterpolator {
            C00782() {
            }

            public float getInterpolation(float f) {
                float f2 = (f / Ease.DURATION) - Ease.DURATION;
                return (-1.0f * ((f2 * ((f2 * f2) * f2)) - Ease.DURATION)) + 0.0f;
            }
        }

        /* renamed from: com.fima.glowpadview.Ease.Quart.3 */
        class C00793 implements TimeInterpolator {
            C00793() {
            }

            public float getInterpolation(float f) {
                float f2 = f / 0.5f;
                if (f2 < Ease.DURATION) {
                    return (f2 * (((0.5f * f2) * f2) * f2)) + 0.0f;
                }
                f2 -= 2.0f;
                return (((f2 * ((f2 * f2) * f2)) - 2.0f) * -0.5f) + 0.0f;
            }
        }

        static {
            easeIn = new C00771();
            easeOut = new C00782();
            easeInOut = new C00793();
        }

        Quart() {
        }
    }

    static class Quint {
        public static final TimeInterpolator easeIn;
        public static final TimeInterpolator easeInOut;
        public static final TimeInterpolator easeOut;

        /* renamed from: com.fima.glowpadview.Ease.Quint.1 */
        class C00801 implements TimeInterpolator {
            C00801() {
            }

            public float getInterpolation(float f) {
                float f2 = f / Ease.DURATION;
                return (f2 * ((((Ease.DURATION * f2) * f2) * f2) * f2)) + 0.0f;
            }
        }

        /* renamed from: com.fima.glowpadview.Ease.Quint.2 */
        class C00812 implements TimeInterpolator {
            C00812() {
            }

            public float getInterpolation(float f) {
                float f2 = (f / Ease.DURATION) - Ease.DURATION;
                return (((f2 * (((f2 * f2) * f2) * f2)) + Ease.DURATION) * Ease.DURATION) + 0.0f;
            }
        }

        /* renamed from: com.fima.glowpadview.Ease.Quint.3 */
        class C00823 implements TimeInterpolator {
            C00823() {
            }

            public float getInterpolation(float f) {
                float f2 = f / 0.5f;
                if (f2 < Ease.DURATION) {
                    return (f2 * ((((0.5f * f2) * f2) * f2) * f2)) + 0.0f;
                }
                f2 -= 2.0f;
                return (((f2 * (((f2 * f2) * f2) * f2)) + 2.0f) * 0.5f) + 0.0f;
            }
        }

        static {
            easeIn = new C00801();
            easeOut = new C00812();
            easeInOut = new C00823();
        }

        Quint() {
        }
    }

    static class Sine {
        public static final TimeInterpolator easeIn;
        public static final TimeInterpolator easeInOut;
        public static final TimeInterpolator easeOut;

        /* renamed from: com.fima.glowpadview.Ease.Sine.1 */
        class C00831 implements TimeInterpolator {
            C00831() {
            }

            public float getInterpolation(float f) {
                return ((-1.0f * ((float) Math.cos(((double) (f / Ease.DURATION)) * 1.5707963267948966d))) + Ease.DURATION) + 0.0f;
            }
        }

        /* renamed from: com.fima.glowpadview.Ease.Sine.2 */
        class C00842 implements TimeInterpolator {
            C00842() {
            }

            public float getInterpolation(float f) {
                return (((float) Math.sin(((double) (f / Ease.DURATION)) * 1.5707963267948966d)) * Ease.DURATION) + 0.0f;
            }
        }

        /* renamed from: com.fima.glowpadview.Ease.Sine.3 */
        class C00853 implements TimeInterpolator {
            C00853() {
            }

            public float getInterpolation(float f) {
                return (-0.5f * (((float) Math.cos((3.141592653589793d * ((double) f)) / 1.0d)) - Ease.DURATION)) + 0.0f;
            }
        }

        static {
            easeIn = new C00831();
            easeOut = new C00842();
            easeInOut = new C00853();
        }

        Sine() {
        }
    }

    Ease() {
    }
}
