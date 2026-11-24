package pt.upt.studenthub;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class TimetableViewModel extends ViewModel {
    private static final long DELAY_MS = 800;
    private final ExecutorService executor = Executors.newSingleThreadExecutor();
    private final Handler mainHandler = new Handler(Looper.getMainLooper());
    private Future<?> task;

    public void startSync() {
        task = executor.submit(() -> {
           try {
               Thread.sleep(DELAY_MS);
               mainHandler.post(() ->
                       Log.d("Sync", "a trabalhar na thread: " + Thread.currentThread().getName()));
           } catch (InterruptedException e) {
               Thread.currentThread().interrupt();
           }
        });
    }
}
