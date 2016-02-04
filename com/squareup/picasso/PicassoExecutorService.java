package com.squareup.picasso;

import android.net.NetworkInfo;
import com.squareup.picasso.Picasso.Priority;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import org.linphone.core.VideoSize;
import org.linphone.mediastream.Version;

class PicassoExecutorService extends ThreadPoolExecutor {
    private static final int DEFAULT_THREAD_COUNT = 3;

    private static final class PicassoFutureTask extends FutureTask<BitmapHunter> implements Comparable<PicassoFutureTask> {
        private final BitmapHunter hunter;

        public PicassoFutureTask(BitmapHunter bitmapHunter) {
            super(bitmapHunter, null);
            this.hunter = bitmapHunter;
        }

        public int compareTo(PicassoFutureTask picassoFutureTask) {
            Priority priority = this.hunter.getPriority();
            Priority priority2 = picassoFutureTask.hunter.getPriority();
            return priority == priority2 ? this.hunter.sequence - picassoFutureTask.hunter.sequence : priority2.ordinal() - priority.ordinal();
        }
    }

    PicassoExecutorService() {
        super(DEFAULT_THREAD_COUNT, DEFAULT_THREAD_COUNT, 0, TimeUnit.MILLISECONDS, new PriorityBlockingQueue(), new PicassoThreadFactory());
    }

    private void setThreadCount(int i) {
        setCorePoolSize(i);
        setMaximumPoolSize(i);
    }

    void adjustThreadCount(NetworkInfo networkInfo) {
        if (networkInfo == null || !networkInfo.isConnectedOrConnecting()) {
            setThreadCount(DEFAULT_THREAD_COUNT);
            return;
        }
        switch (networkInfo.getType()) {
            case VideoSize.QCIF /*0*/:
                switch (networkInfo.getSubtype()) {
                    case VideoSize.CIF /*1*/:
                    case VideoSize.HVGA /*2*/:
                        setThreadCount(1);
                    case DEFAULT_THREAD_COUNT /*3*/:
                    case Version.API04_DONUT_16 /*4*/:
                    case Version.API05_ECLAIR_20 /*5*/:
                    case Version.API06_ECLAIR_201 /*6*/:
                    case Version.API12_HONEYCOMB_MR1_31X /*12*/:
                        setThreadCount(2);
                    case Version.API13_HONEYCOMB_MR2_32 /*13*/:
                    case Version.API14_ICE_CREAM_SANDWICH_40 /*14*/:
                    case Version.API15_ICE_CREAM_SANDWICH_403 /*15*/:
                        setThreadCount(DEFAULT_THREAD_COUNT);
                    default:
                        setThreadCount(DEFAULT_THREAD_COUNT);
                }
            case VideoSize.CIF /*1*/:
            case Version.API06_ECLAIR_201 /*6*/:
            case Version.API09_GINGERBREAD_23 /*9*/:
                setThreadCount(4);
            default:
                setThreadCount(DEFAULT_THREAD_COUNT);
        }
    }

    public Future<?> submit(Runnable runnable) {
        Object picassoFutureTask = new PicassoFutureTask((BitmapHunter) runnable);
        execute(picassoFutureTask);
        return picassoFutureTask;
    }
}
