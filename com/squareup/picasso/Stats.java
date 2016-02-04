package com.squareup.picasso;

import android.graphics.Bitmap;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;

class Stats {
    private static final int BITMAP_DECODE_FINISHED = 2;
    private static final int BITMAP_TRANSFORMED_FINISHED = 3;
    private static final int CACHE_HIT = 0;
    private static final int CACHE_MISS = 1;
    private static final int DOWNLOAD_FINISHED = 4;
    private static final String STATS_THREAD_NAME = "Picasso-Stats";
    long averageDownloadSize;
    long averageOriginalBitmapSize;
    long averageTransformedBitmapSize;
    final Cache cache;
    long cacheHits;
    long cacheMisses;
    int downloadCount;
    final Handler handler;
    int originalBitmapCount;
    final HandlerThread statsThread;
    long totalDownloadSize;
    long totalOriginalBitmapSize;
    long totalTransformedBitmapSize;
    int transformedBitmapCount;

    private static class StatsHandler extends Handler {
        private final Stats stats;

        /* renamed from: com.squareup.picasso.Stats.StatsHandler.1 */
        class C01271 implements Runnable {
            final /* synthetic */ Message val$msg;

            C01271(Message message) {
                this.val$msg = message;
            }

            public void run() {
                throw new AssertionError("Unhandled stats message." + this.val$msg.what);
            }
        }

        public StatsHandler(Looper looper, Stats stats) {
            super(looper);
            this.stats = stats;
        }

        public void handleMessage(Message message) {
            switch (message.what) {
                case Stats.CACHE_HIT /*0*/:
                    this.stats.performCacheHit();
                case Stats.CACHE_MISS /*1*/:
                    this.stats.performCacheMiss();
                case Stats.BITMAP_DECODE_FINISHED /*2*/:
                    this.stats.performBitmapDecoded((long) message.arg1);
                case Stats.BITMAP_TRANSFORMED_FINISHED /*3*/:
                    this.stats.performBitmapTransformed((long) message.arg1);
                case Stats.DOWNLOAD_FINISHED /*4*/:
                    this.stats.performDownloadFinished((Long) message.obj);
                default:
                    Picasso.HANDLER.post(new C01271(message));
            }
        }
    }

    Stats(Cache cache) {
        this.cache = cache;
        this.statsThread = new HandlerThread(STATS_THREAD_NAME, 10);
        this.statsThread.start();
        this.handler = new StatsHandler(this.statsThread.getLooper(), this);
    }

    private static long getAverage(int i, long j) {
        return j / ((long) i);
    }

    private void processBitmap(Bitmap bitmap, int i) {
        this.handler.sendMessage(this.handler.obtainMessage(i, Utils.getBitmapBytes(bitmap), CACHE_HIT));
    }

    StatsSnapshot createSnapshot() {
        return new StatsSnapshot(this.cache.maxSize(), this.cache.size(), this.cacheHits, this.cacheMisses, this.totalDownloadSize, this.totalOriginalBitmapSize, this.totalTransformedBitmapSize, this.averageDownloadSize, this.averageOriginalBitmapSize, this.averageTransformedBitmapSize, this.downloadCount, this.originalBitmapCount, this.transformedBitmapCount, System.currentTimeMillis());
    }

    void dispatchBitmapDecoded(Bitmap bitmap) {
        processBitmap(bitmap, BITMAP_DECODE_FINISHED);
    }

    void dispatchBitmapTransformed(Bitmap bitmap) {
        processBitmap(bitmap, BITMAP_TRANSFORMED_FINISHED);
    }

    void dispatchCacheHit() {
        this.handler.sendEmptyMessage(CACHE_HIT);
    }

    void dispatchCacheMiss() {
        this.handler.sendEmptyMessage(CACHE_MISS);
    }

    void dispatchDownloadFinished(long j) {
        this.handler.sendMessage(this.handler.obtainMessage(DOWNLOAD_FINISHED, Long.valueOf(j)));
    }

    void performBitmapDecoded(long j) {
        this.originalBitmapCount += CACHE_MISS;
        this.totalOriginalBitmapSize += j;
        this.averageOriginalBitmapSize = getAverage(this.originalBitmapCount, this.totalOriginalBitmapSize);
    }

    void performBitmapTransformed(long j) {
        this.transformedBitmapCount += CACHE_MISS;
        this.totalTransformedBitmapSize += j;
        this.averageTransformedBitmapSize = getAverage(this.originalBitmapCount, this.totalTransformedBitmapSize);
    }

    void performCacheHit() {
        this.cacheHits++;
    }

    void performCacheMiss() {
        this.cacheMisses++;
    }

    void performDownloadFinished(Long l) {
        this.downloadCount += CACHE_MISS;
        this.totalDownloadSize += l.longValue();
        this.averageDownloadSize = getAverage(this.downloadCount, this.totalDownloadSize);
    }

    void shutdown() {
        this.statsThread.quit();
    }
}
