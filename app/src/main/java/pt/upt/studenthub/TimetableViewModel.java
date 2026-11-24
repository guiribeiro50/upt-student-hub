package pt.upt.studenthub;

import static pt.upt.studenthub.SyncState.RUNNING;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class TimetableViewModel extends ViewModel {
    private static final long DELAY_MS = 800;
    private final ExecutorService executor = Executors.newSingleThreadExecutor();
    private final Handler mainHandler = new Handler(Looper.getMainLooper());
    private final MutableLiveData<SyncState> state = new MutableLiveData<>(SyncState.IDLE);
    private final MutableLiveData<Integer> progress = new MutableLiveData<>(0);
    private final MutableLiveData<List<String>> sessions =
            new MutableLiveData<>(Collections.<String>emptyList());
    private Future<?> task;
    private final List<String> loaded = new ArrayList<>();

    public void startSync(String[] source) {
        if (state.getValue() == RUNNING) {
            return;
        }

        loaded.clear();
        sessions.setValue(Collections.<String>emptyList());
        progress.setValue(0);
        state.setValue(RUNNING);

        final List<String> snapshot =
                Collections.unmodifiableList(new ArrayList<>(Arrays.asList(source)));

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

    public MutableLiveData<SyncState> getState() {
        return state;
    }

    public MutableLiveData<Integer> getProgress() {
        return progress;
    }

    public MutableLiveData<List<String>> getListSessions() {
        return listSessions;
    }
}
